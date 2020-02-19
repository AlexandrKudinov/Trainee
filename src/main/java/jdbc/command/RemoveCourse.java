package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveCourse extends AbstractDAO implements Command{
    private int id;
    private static Connection connection = getConnection();

    public RemoveCourse(int id){
        this.id=id;
    }
    @Override
    public Object execute() {
        String sql = "DELETE FROM courses WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
