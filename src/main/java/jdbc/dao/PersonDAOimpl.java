package jdbc.dao;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Person;
import jdbc.entity.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOimpl extends AbstractDAO implements DAO<Person> {
    private Connection connection = getConnection();
    private Type type;

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String add(Person person) {
        String tableName = person.getType().getTableName();

        String sql = "INSERT INTO " + tableName + " ( id, name ) VALUES ( ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type.name()+" "+person.getId()+" removed";
    }

    private void addPersonToList(ResultSet resultSet, List<Person> persons) throws SQLException {
        while (resultSet.next()) {
            Person person = Person.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .type(type)
                    .build();
            persons.add(person);
        }
    }


    @Override
    public String getALL() {
        List<Person> persons = new ArrayList<>();
        String tableName = type.getTableName();
        String sql = "SELECT id, name FROM " + tableName + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            addPersonToList(resultSet, persons);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons.toString();
    }

    @Override
    public String getByID(Integer id) {
        Person person = null;
        String sql = "SELECT id, name FROM " + type.getTableName() + " WHERE id=?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                person = Person.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .type(type)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person.toString();
    }

    @Override
    public String update(Person person) {
        String tableName = person.getType().getTableName();
        String sql = "UPDATE " + tableName + " SET name=? WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type.name() + " " + person.getId() + " updated";
    }

    @Override
    public String remove(Integer id) {
        String tableName = type.getTableName();
        String sql = "DELETE FROM " + tableName + " WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type.name() + " " + id + " removed";
    }
}
