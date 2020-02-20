package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.CourseDAOimpl;
import jdbc.entity.Course;

public class AddCourse extends AbstractDAO implements Command {
    private CourseDAOimpl courseDAOimpl = CourseDAOimpl.getInstance();
    private Course course;

    public AddCourse(Course course) {
        this.course = course;
    }

    @Override
    public Object execute() {
        courseDAOimpl.add(course);
        return course.getId();
    }
}
