package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePerson extends AbstractDAO implements Command {
    private Person person;
    private static Connection connection = getConnection();

    public UpdatePerson(Person person) {
        this.person = person;
    }

    @Override
    public void execute() {
        String tableName = person.getType().getTableName();
        String sql = "UPDATE " + tableName + " SET name=? WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
