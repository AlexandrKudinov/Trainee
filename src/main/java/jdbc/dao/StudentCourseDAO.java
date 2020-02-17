package jdbc.dao;

import jdbc.entity.StudentCourse;

import java.sql.SQLException;
import java.util.List;

public interface StudentCourseDAO {

    //CREATE
    void add(StudentCourse student_course) throws SQLException;

    //READ
    List<StudentCourse> getALL() throws SQLException;

    StudentCourse getByID(int id) throws SQLException;


    //UPDATE
    void update(StudentCourse student_course) throws SQLException;

    //DELETE
    void remove(StudentCourse student_course) throws SQLException;
}