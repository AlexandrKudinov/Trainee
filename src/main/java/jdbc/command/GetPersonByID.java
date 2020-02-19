package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Person;
import jdbc.entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetPersonByID extends AbstractDAO implements Command {
    private int id;
    private Type type;
    private static Connection connection = getConnection();

    public GetPersonByID(Type personType, int id) {
        this.id = id;
        this.type = personType;
    }

    @Override
    public Object execute() {
        String tableName = type.getTableName();
        String sql = "SELECT id, name FROM " + tableName + " WHERE id=?;";
        Person person = null;
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
        return person;
    }
}
