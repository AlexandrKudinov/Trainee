package jdbc.dao;

import jdbc.entity.Type;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private static Map<Class,DAO> map = new HashMap<>();
    static {
        map.put(CourseDAOimpl.class, new CourseDAOimpl());
        map.put(PersonDAOimpl.class, new PersonDAOimpl());
        PersonDAOimpl personDAOimpl = new PersonDAOimpl();
        personDAOimpl.setType(Type.student);
    }
    public static DAO get(Class DAOimpl){
        return map.get(DAOimpl);
    }
}
