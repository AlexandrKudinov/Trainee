package jdbc.dao;

import jdbc.entity.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {

    //CREATE
    void add(Course course) throws SQLException;

    //READ
    List<Course> getALL() throws SQLException;

    Course getByID(int id) throws SQLException;

    //UPDATE
    void update(Course course) throws SQLException;

    //DELETE
    void remove(Course course) throws SQLException;
}
