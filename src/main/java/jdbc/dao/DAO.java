package jdbc.dao;

import jdbc.entity.Entity;

import java.util.List;

public interface DAO<T> {

    String add(T t);

    List<Entity> getALL() ;

    Entity getByID(T t);

    String update(T t);

    String remove(T t) ;
}
