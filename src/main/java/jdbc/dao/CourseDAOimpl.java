package jdbc.dao;

import jdbc.Domain;
import jdbc.bl.AbstractDAO;
import jdbc.dao.*;
import jdbc.entity.Course;
import jdbc.entity.Person;
import jdbc.entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOimpl extends AbstractDAO implements DAO<Course> {

    private static Connection connection = getConnection();

    @Override
    public void add(Course course) {
        String sql = "INSERT INTO courses ( id, title, start_datetime , " +
                "end_datetime, teacher_id, created_at ,status)" +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?::course_status);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.setDate(3, course.getStartDatetime());
            preparedStatement.setDate(4, course.getEndDatetime());
            preparedStatement.setInt(5, course.getTeacherId());
            preparedStatement.setString(6, course.getCreatedAt());
            preparedStatement.setString(7, course.getStatus().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Course createCourse(ResultSet resultSet) throws SQLException {
        Status status = Status.valueOf(resultSet.getString("status"));
        Course course = Course.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .startDatetime(resultSet.getDate("start_datetime"))
                .endDatetime(resultSet.getDate("end_datetime"))
                .teacherId(resultSet.getInt("teacher_id"))
                .createdAt(resultSet.getString("created_at"))
                .status(status)
                .build();
        return course;
    }

    private void addCourseToList(ResultSet resultSet, List<Course> courses) throws SQLException {
        while (resultSet.next()) {
            courses.add(createCourse(resultSet));
        }
    }


    @Override
    public List<Course> getALL() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, title, start_datetime, end_datetime, status, " +
                "  teacher_id, created_at, status FROM courses;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
           addCourseToList(resultSet,courses);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return courses;
    }

    @Override
    public Course getByID(int id) {
        Course course = null;
        String sql = "SELECT id, title, start_datetime, end_datetime, status, " +
                "teacher_id,created_at  FROM courses WHERE id=?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course = createCourse(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public void update(Course course) {
        String sql = "UPDATE courses SET title=? start_datetim=? end_datetime=? " +
                "teacher_id=? created_at=? WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setDate(2, course.getStartDatetime());
            preparedStatement.setDate(3, course.getEndDatetime());
            preparedStatement.setInt(4, course.getTeacherId());
            preparedStatement.setString(5, course.getCreatedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Course course) {
        String sql = "DELETE FROM courses WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
