package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Person;
import jdbc.entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemovePerson extends AbstractDAO implements Command {
    private int id;
    private Type type;
    private static Connection connection = getConnection();

    public RemovePerson(Type type, int id) {
        this.type=type;
        this.id=id;
    }

    @Override
    public Object execute() {
        String tableName = type.getTableName();
        String sql = "DELETE FROM " + tableName + " WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
