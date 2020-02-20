package jdbc.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    void add(T t) throws SQLException;

    List<T> getALL() throws SQLException;

    T getByID(int id) throws SQLException;

    void update(T t) throws SQLException;

    void remove(Integer id) throws SQLException;
}
