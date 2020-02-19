package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.CourseDAOimpl;
import jdbc.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCourseByID extends AbstractDAO implements Command {
    private int id;
    private static Connection connection = getConnection();

    public GetCourseByID(int id){
        this.id = id;
    }

    @Override
    public Object execute() {
        Course course = null;
        String sql = "SELECT id, title, start_datetime, end_datetime, status, " +
                "teacher_id,created_at  FROM courses WHERE id=?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course = CourseDAOimpl.createCourse(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        }
        return course;
    }
}
