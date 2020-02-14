package jdbc.service;

import jdbc.bl.Util;
import jdbc.dao.TeacherDAO;
import jdbc.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeacherService extends Util implements TeacherDAO {
    final Connection connection = getConnection();

    @Override
    public void add(Teacher teacher) {

    }

    @Override
    public List<Teacher> getALL() {
        return null;
    }

    @Override
    public Teacher getByID(int id) throws SQLException {
        Teacher teacher = new Teacher();
//        String sql = "SELECT id, name FROM teachers WHERE id=?;";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            teacher.setId(resultSet.getInt("id"));
//            teacher.setName(resultSet.getString("name"));
//            //preparedStatement.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                connection.close();
//            }
//        }
        return teacher;
    }

    @Override
    public void update(Teacher teacher) {

    }

    @Override
    public void remove(Teacher teacher) {

    }
}
