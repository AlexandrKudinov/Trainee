package jdbc;

import jdbc.bl.AbstractDAO;
import jdbc.command.AddPerson;
import jdbc.command.Command;
import jdbc.command.Receiver;
import jdbc.command.RemovePerson;
import jdbc.dao.CourseDAOimpl;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.Course;
import jdbc.entity.Person;
import jdbc.entity.Status;
import jdbc.entity.Type;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class Domain extends AbstractDAO {
    public static void main(String[] args) {

        PersonDAOimpl personDAOimpl = new PersonDAOimpl();
        CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
        Person person = Person.builder()
                .id(5)
                .name("Marianich")
                .type(Type.student)
                .build();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.APRIL, 10);
        Course course = Course.builder()
                .id(7)
                .title("Piano")
                .startDatetime(new java.sql.Date(calendar.getTime().getTime()))
                .endDatetime(new java.sql.Date(calendar.getTime().getTime()))
                .teacherId(2)
                .createdAt("SPbU")
                .status(Status.on_study)
                .build();

        Receiver receiver = new Receiver(new AddPerson(person));
        Receiver receiver1 = new Receiver(new RemovePerson(person));

        try (Connection connection = getConnection()) {
           receiver.runCommand();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
