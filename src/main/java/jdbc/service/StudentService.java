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
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO students ( id, name ) VALUES ( ?, ?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
           // preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public List<Student> getAllWhichHaveAtListOneCourseOf(Teacher teacher) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT DISTINCT students FROM students INNER JOIN students_courses on students.id = students_courses.student_id INNET JOIN courses ON courses.id = students_courses.course_id WHERE courses.teacher_id = ?;";
        List<Student> students = new ArrayList<>();
        //  Statement statement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teacher.getId());
//            statement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
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
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return students;
    }

    @Override
    public Student getByID(int id) throws SQLException {
        Student student = new Student();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, name FROM students WHERE id=?;";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
           // preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return student;
    }

    @Override
    public void update(Student student) throws SQLException {
        String sql = "UPDATE students SET name=? WHERE id=?;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Student student) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM students WHERE id=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
           // preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
