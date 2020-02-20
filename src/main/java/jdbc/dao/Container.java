package jdbc.dao;


public class Container {
    private static CourseDAOimpl courseDAOimpl = CourseDAOimpl.getInstance();
    private static PersonDAOimpl personDAOimpl = PersonDAOimpl.getInstance();

    public static CourseDAOimpl getCourseDAOimpl() {
        return courseDAOimpl;
    }

    public static PersonDAOimpl getPersonDAOimpl() {
        return personDAOimpl;
    }
}
