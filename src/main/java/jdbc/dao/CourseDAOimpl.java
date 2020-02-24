package jdbc.dao;

import jdbc.bl.Config;
import jdbc.entity.Course;
import jdbc.entity.Entity;
import jdbc.entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOimpl extends Config implements DAO<Course> {

    @Override
    public String add(Course course) {
        Connection connection = getConnection();
        String sql = "INSERT INTO courses ( id, title, start_datetime , " +
                "end_datetime, teacher_id, created_at ,status)" +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?::course_status);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.setDate(3, course.getStartDateTime());
            preparedStatement.setDate(4, course.getEndDateTime());
            preparedStatement.setInt(5, course.getTeacherId());
            preparedStatement.setString(6, course.getCreatedAt());
            preparedStatement.setString(7, course.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "course "+course.getId()+" added";
    }

    public static Course createCourse(ResultSet resultSet) throws SQLException {
        Status status = Status.valueOf(resultSet.getString("status"));
        return Course.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .startDateTime(resultSet.getDate("start_datetime"))
                .endDateTime(resultSet.getDate("end_datetime"))
                .teacherId(resultSet.getInt("teacher_id"))
                .createdAt(resultSet.getString("created_at"))
                .status(status)
                .build();
    }

    private void addCourseToList(ResultSet resultSet, List<Entity> courses) throws SQLException {
        while (resultSet.next()) {
            courses.add(createCourse(resultSet));
        }
    }

    @Override
    public List<Entity> getALL() {
        Connection connection = getConnection();
        List<Entity> courses = new ArrayList<>();
        String sql = "SELECT id, title, start_datetime, end_datetime, status, " +
                "  teacher_id, created_at, status FROM courses;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            addCourseToList(resultSet, courses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public Entity getByID(Course course) {
        int id = course.getId();
        Connection connection = getConnection();
        Course courseFromTbl = null;
        String sql = "SELECT id, title, start_datetime, end_datetime, status, " +
                "teacher_id,created_at  FROM courses WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                courseFromTbl = createCourse(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseFromTbl;
    }

    @Override
    public String update(Course course) {
        Connection connection = getConnection();
        String sql = "UPDATE courses SET title=? ,start_datetime=?, end_datetime=?, " +
                "teacher_id=?, created_at=?, status=?::course_status WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setDate(2, course.getStartDateTime());
            preparedStatement.setDate(3, course.getEndDateTime());
            preparedStatement.setInt(4, course.getTeacherId());
            preparedStatement.setString(5, course.getCreatedAt());
            preparedStatement.setString(6, course.getStatus().name());
            preparedStatement.setInt(7, course.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "course "+course.getId()+" updated";
    }

    @Override
    public String remove(Course course) {
        int id = course.getId();
        Connection connection = getConnection();
        String sql = "DELETE FROM courses WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "course "+id+" removed";
    }
}
