package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static jdbc.bl.AbstractDAO.getConnection;

public class UpdateCourse extends AbstractDAO implements Command {
    private Course course;
    private static Connection connection = getConnection();

    public UpdateCourse(Course course){
        this.course=course;
    }

    @Override
    public Object execute() {
        String sql = "UPDATE courses SET title=? ,start_datetime=?, end_datetime=?, " +
                "teacher_id=?, created_at=?, status=?::course_status WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setDate(2, course.getStartDatetime());
            preparedStatement.setDate(3, course.getEndDatetime());
            preparedStatement.setInt(4, course.getTeacherId());
            preparedStatement.setString(5, course.getCreatedAt());
            preparedStatement.setString(6,course.getStatus().name());
            preparedStatement.setInt(7, course.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course.getId();
    }
}
