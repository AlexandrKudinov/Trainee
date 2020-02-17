package jdbc;

import jdbc.entity.Course;
import jdbc.entity.Status;
import jdbc.entity.Student;
import jdbc.entity.Teacher;
import jdbc.service.CourseService;
import jdbc.service.StudentService;
import jdbc.service.TeacherService;

import java.sql.SQLException;
import java.util.Calendar;

public class Domain {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        CourseService coursesService = new CourseService();
        Student student = new Student();
        student.setId(10);
        student.setName("Gray");
        Teacher teacher = new Teacher();
        teacher.setId(4);
        teacher.setName("Grigorovich");
        Course course = new Course();
        course.setId(7);
        course.setCreatedAt("ITMO");
        course.setTeacherId(1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.APRIL, 10);
        course.setEndDatetime(new java.sql.Date(calendar.getTime().getTime()));
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(1999, Calendar.AUGUST, 29);
        course.setStartDatetime(new java.sql.Date(calendar1.getTime().getTime()));
        course.setStatus(Status.open);
        course.setTitle("Java");

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
//            Course course = coursesService.getByID(1);
//            coursesService.add(course);
//            List<Course> courses = coursesService.getALL();
//            Course course1 = coursesService.getByID(1);
//            System.out.println(course1);

            coursesService.remove(course);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
