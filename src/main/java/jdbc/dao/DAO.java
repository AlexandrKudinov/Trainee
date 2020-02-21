package jdbc.dao;

import java.util.List;

public interface DAO<T> {

    String add(T t);

    String getALL() ;

    String getByID(Integer id);

    String update(T t);

    String remove(Integer id) ;
}
