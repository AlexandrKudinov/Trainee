package jdbc.dao;

import jdbc.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    //CREATE
    void add(Student student) throws SQLException;

    //READ
    List<Student> getALL() throws SQLException;

    Student getByID(int id) throws SQLException;

    //UPDATE
    void update(Student student) throws SQLException;

    //DELETE
    void remove(Student student) throws SQLException;
}