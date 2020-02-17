package jdbc.service;

import jdbc.bl.Util;
import jdbc.dao.StudentCourseDAO;
import jdbc.entity.Student;
import jdbc.entity.StudentCourse;
import jdbc.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseService extends Util implements StudentCourseDAO {
    Connection connection = getConnection();

    @Override
    public void add(StudentCourse studentCourse) throws SQLException {
        String sql = "INSERT INTO students_courses ( id, student_id, course_id ) VALUES ( ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentCourse.getId());
            preparedStatement.setInt(2, studentCourse.getStudentId());
            preparedStatement.setInt(3, studentCourse.getCourseId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void addStudentCourseToList(ResultSet resultSet, List<StudentCourse> students) throws SQLException {
        while (resultSet.next()) {
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setId(resultSet.getInt("id"));
            studentCourse.setStudentId(resultSet.getInt("student_id"));
            studentCourse.setCourseId(resultSet.getInt("course_id"));
            students.add(studentCourse);
        }
    }

    @Override
    public List<StudentCourse> getALL() throws SQLException {
        List<StudentCourse> studentsCourses = new ArrayList<>();
        String sql = "SELECT id, student_id, course_id FROM students_courses;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            addStudentCourseToList(resultSet, studentsCourses);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return studentsCourses;
    }

    @Override
    public StudentCourse getByID(int id) throws SQLException {
        StudentCourse studentCourse = new StudentCourse();
        String sql = "SELECT id, student_id, course_id FROM students_courses WHERE id=?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                studentCourse.setId(resultSet.getInt("id"));
                studentCourse.setStudentId(resultSet.getInt("student_id"));
                studentCourse.setCourseId(resultSet.getInt("course_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return studentCourse;
    }

    @Override
    public void update(StudentCourse studentCourse) throws SQLException {
        String sql = "UPDATE students_courses SET student_id = ?, course_id=? WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentCourse.getStudentId());
            preparedStatement.setInt(2, studentCourse.getCourseId());
            preparedStatement.setInt(3, studentCourse.getId());
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
    public void remove(StudentCourse studentCourse) throws SQLException {
        String sql = "DELETE FROM students_courses WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, studentCourse.getId());
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
