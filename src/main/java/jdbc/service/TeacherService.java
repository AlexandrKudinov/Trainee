package jdbc.service;

import jdbc.bl.Util;
import jdbc.dao.TeacherDAO;
import jdbc.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TeacherService extends Util implements TeacherDAO {
    Connection connection = getConnection();

    @Override
    public void add(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teachers ( id, name ) VALUES ( ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setString(2, teacher.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<List<String>> assignedToWhom() throws SQLException {
        String sql = "SELECT courses.title, teachers.name " +
                "FROM ( SELECT * FROM courses) courses LEFT JOIN ( SELECT * FROM teachers) teachers " +
                "ON teachers.id = courses.teacher_id;";

        List<List<String>> assignedToWhom =new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List<String> courseOfTeacher = new ArrayList<>();
                String coursesName = resultSet.getString("title");
                String teachersName = resultSet.getString("name");
                courseOfTeacher.add(coursesName);
                courseOfTeacher.add(teachersName);
                assignedToWhom.add(courseOfTeacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return assignedToWhom;
    }

    private void addTeachersToList(ResultSet resultSet, List<Teacher> teachers) throws SQLException {
        while (resultSet.next()) {
            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getInt("id"));
            teacher.setName(resultSet.getString("name"));
            teachers.add(teacher);
        }
    }

    @Override
    public List<Teacher> getALL() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT id, name FROM teachers;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            addTeachersToList(resultSet, teachers);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return teachers;
    }

    @Override
    public Teacher getByID(int id) throws SQLException {
       Teacher teacher = new Teacher();
        String sql = "SELECT id, name FROM teachers WHERE id=?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return teacher;
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        String sql = "UPDATE teachers SET name=? WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setInt(2, teacher.getId());
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
    public void remove(Teacher teacher) throws SQLException {
        String sql = "DELETE FROM teahers WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, teacher.getId());
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
