package jdbc;


import jdbc.entity.Course;
import jdbc.entity.Student;
import jdbc.entity.Teacher;
import jdbc.service.CoursesService;
import jdbc.service.StudentService;
import jdbc.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

public class Domain {
    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        CoursesService coursesService = new CoursesService();
        Student student = new Student();
        student.setId(10);
        student.setName("Gray");
        Teacher teacher = new Teacher();
        teacher.setId(4);
        teacher.setName("Grigorovich");

        try {
//            studentService.update(student);
//            Student student1 = studentService.getByID(2);
//            System.out.println(student1);
//            List<String> students = studentService.getAllTeachedBy(teacher);
//            System.out.println(students);
//            List<Teacher> teachers = teacherService.getALL();
//            teacherService.add(teacher);
//            List<List<String>> teachersCourses = teacherService.assignedToWhom();
//            System.out.println(teachersCourses);
           Course course = coursesService.getByID(1);
            System.out.println(course);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
