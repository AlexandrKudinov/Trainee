package jdbc;

import jdbc.entity.Student;
import jdbc.entity.Teacher;
import jdbc.service.StudentService;
import jdbc.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

public class Domain {
    public static void main(String[] args) {
//        Util util = new Util();
//        util.getConnection();
        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        Student student = new Student();
        student.setId(9);
        student.setName("Morgan");
        Teacher teacher = new Teacher();
        teacher.setId(1);

        try {
            List<Student> students = studentService.getALL();
            System.out.println(students);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
