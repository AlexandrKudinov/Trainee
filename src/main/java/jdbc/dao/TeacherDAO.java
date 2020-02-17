package jdbc.dao;

import jdbc.entity.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDAO {
    //CREATE
    void add(Teacher teacher) throws SQLException;

    //READ
    List<Teacher> getALL() throws SQLException;

    Teacher getByID(int id) throws SQLException;

    //UPDATE
    void update(Teacher teacher) throws SQLException;

    //DELETE
    void remove(Teacher teacher) throws SQLException;
}