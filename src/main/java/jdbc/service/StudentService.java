package jdbc.service;

import jdbc.bl.Util;
import jdbc.dao.StudentDAO;
import jdbc.entity.Student;
import jdbc.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService extends Util implements StudentDAO {

    Connection connection = getConnection();

    @Override
    public void add(Student student) throws SQLException {
        String sql = "INSERT INTO students ( id, name ) VALUES ( ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    private void addStudentToList(ResultSet resultSet, List<Student> students) throws SQLException {
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            students.add(student);
        }
    }

    public List<String> getAllTeachedBy(Teacher teacher) throws SQLException {
        String sql = "SELECT DISTINCT students.name FROM students " +
                "INNER JOIN students_courses on students.id = students_courses.student_id "+
                "INNER JOIN courses ON courses.id = students_courses.course_id WHERE courses.teacher_id = ?;";
        List<String> students = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, teacher.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               String name = resultSet.getString("name");
                students.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return students;
    }


    @Override
    public List<Student> getALL() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, name FROM students;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            addStudentToList(resultSet, students);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return students;
    }


    @Override
    public Student getByID(int id) throws SQLException {
        Student student = new Student();
        String sql = "SELECT id, name FROM students WHERE id=?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return student;
    }

    @Override
    public void update(Student student) throws SQLException {
        String sql = "UPDATE students SET name=? WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getId());
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
    public void remove(Student student) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, student.getId());
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
