package jdbc;

import jdbc.entity.Student;
import jdbc.entity.Teacher;
import jdbc.service.StudentService;
import jdbc.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

public class Domain {
    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        Student student = new Student();
        student.setId(10);
        student.setName("Gray");
        Teacher teacher = new Teacher();
        teacher.setId(1);

        try {
//     studentService.update(student);
            Student student1 = studentService.getByID(2);
            System.out.println(student1);
//            List<String> students = studentService.getAllTeachedBy(teacher);
//            System.out.println(students);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
