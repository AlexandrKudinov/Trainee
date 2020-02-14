package jdbc.dao;

import jdbc.entity.Student_Course;

import java.util.List;

public interface Student_CourseDAO {

    //CREATE
    void add(Student_Course student_course);

    //READ
    List<Student_Course> getALL();

    Student_Course getByID(int id);
    Student_Course getByStudentIDAndCourseID(int student_id,int course_id);

    //UPDATE
    void update(Student_Course student_course);

    //DELETE
    void remove(Student_Course student_course);
}