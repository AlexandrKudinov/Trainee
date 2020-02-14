package jdbc.dao;

import jdbc.entity.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDAO {
    //CREATE
    void add(Teacher teacher);

    //READ
    List<Teacher> getALL();

    Teacher getByID(int id) throws SQLException;

    //UPDATE
    void update(Teacher teacher);

    //DELETE
    void remove(Teacher teacher);
}