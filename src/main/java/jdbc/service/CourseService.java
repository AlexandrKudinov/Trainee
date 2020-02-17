package jdbc.service;

import jdbc.bl.Util;
import jdbc.dao.*;
import jdbc.entity.Course;
import jdbc.entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService extends Util implements CourseDAO {
    Connection connection = getConnection();

    @Override
    public void add(Course course) throws SQLException {
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
            preparedStatement.setString(7, course.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void addCourseToList(ResultSet resultSet, List<Course> courses) throws SQLException {
        while (resultSet.next()) {
            Course course = new Course();
            course.setId(resultSet.getInt("id"));
            course.setTitle(resultSet.getString("title"));
            course.setStartDatetime(resultSet.getDate("start_datetime"));
            course.setEndDatetime(resultSet.getDate("end_datetime"));
            course.setTeacherId(resultSet.getInt("teacher_id"));
            course.setCreatedAt(resultSet.getString("created_at"));
            Status status = Status.valueOf(resultSet.getString("status"));
            course.setStatus(status);
            courses.add(course);
        }
    }

    @Override
    public List<Course> getALL() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, title, start_datetime, end_datetime, status, " +
                "  teacher_id, created_at, status FROM courses;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            addCourseToList(resultSet, courses);
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
    public Course getByID(int id) throws SQLException {
        Course course = new Course();
        String sql = "SELECT id, title, start_datetime, end_datetime, status, " +
                "teacher_id,created_at  FROM courses WHERE id=?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course.setId(resultSet.getInt("id"));
                course.setTitle(resultSet.getString("title"));
                course.setStartDatetime(resultSet.getDate("start_datetime"));
                course.setEndDatetime(resultSet.getDate("end_datetime"));
                course.setTeacherId(resultSet.getInt("teacher_id"));
                course.setCreatedAt(resultSet.getString("created_at"));
                Status status = Status.valueOf(resultSet.getString("status"));
                course.setStatus(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return course;
    }

    @Override
    public void update(Course course) throws SQLException {
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
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Course course) throws SQLException {
        String sql = "DELETE FROM courses WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
