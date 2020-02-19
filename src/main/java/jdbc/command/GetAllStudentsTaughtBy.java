package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.entity.Person;
import jdbc.entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllStudentsTaughtBy extends AbstractDAO implements Command {
    private Person teacher;
    private static Connection connection = getConnection();

    public GetAllStudentsTaughtBy(Person person){
        if(person.getType().equals(Type.student))
            throw new IllegalArgumentException();
        teacher = person;
    }

    @Override
    public Object execute() {
        String sql = "SELECT DISTINCT students.name FROM students " +
                "INNER JOIN students_courses on students.id = students_courses.student_id " +
                "INNER JOIN courses ON courses.id = students_courses.course_id WHERE courses.teacher_id = ?;";
        List<String> students = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, teacher.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                students.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

}
