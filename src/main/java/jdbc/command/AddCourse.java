package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Course;
import jdbc.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCourse extends AbstractDAO implements Command {
    private Course course;
    private static Connection connection = getConnection();

    public AddCourse(Course course){
        this.course=course;
    }

    @Override
    public Object execute() {
        String sql = "INSERT INTO courses ( id, title, start_datetime , " +
                "end_datetime, teacher_id, created_at ,status)" +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?::course_status);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.setDate(3, course.getStartDatetime());
            preparedStatement.setDate(4, course.getEndDatetime());
            preparedStatement.setInt(5, course.getTeacherId());
            preparedStatement.setString(6, course.getCreatedAt());
            preparedStatement.setString(7, course.getStatus().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course.getId();
    }
}
