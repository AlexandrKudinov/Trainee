package jdbc.dao;

import jdbc.entity.StudentCourse;

import java.util.List;

public interface StudentCourseDAO {

    //CREATE
    void add(StudentCourse student_course);

    //READ
    List<StudentCourse> getALL();

    StudentCourse getByID(int id);

    StudentCourse getByStudentIDAndCourseID(int student_id, int course_id);

    //UPDATE
    void update(StudentCourse student_course);

    //DELETE
    void remove(StudentCourse student_course);
}