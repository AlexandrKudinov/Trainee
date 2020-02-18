package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemovePerson extends AbstractDAO implements Command {
    private Person person;
    private static Connection connection = getConnection();

    public RemovePerson(Person person) {
        this.person = person;
    }

    @Override
    public void execute() {
        String tableName = person.getType().getTableName();
        String sql = "DELETE FROM " + tableName + " WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
