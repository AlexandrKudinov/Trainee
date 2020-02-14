package jdbc.dao;

import jdbc.entity.Course;

import java.util.List;

public interface CourseDAO {

    //CREATE
    void add(Course course);

    //READ
    List<Course> getALL();

    Course getByID(int id);

    //UPDATE
    void update(Course course);

    //DELETE
    void remove(Course course);
}
