package jdbc.dao;

import java.util.List;

public interface DAO<T> {

    void add(T t);

    List<T> getALL() ;

    T getByID(int id);

    void update(T t);

    void remove(Integer id) ;
}
