package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPerson extends AbstractDAO implements Command {
    private Person person;
    private static Connection connection = getConnection();

    public AddPerson(Person person) {
        this.person = person;
    }

    @Override
    public void execute() {
        String tableName = person.getType().getTableName();
        String sql = "INSERT INTO " + tableName + " ( id, name ) VALUES ( ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
