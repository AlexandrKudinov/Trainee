package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.CourseDAOimpl;
import jdbc.entity.Course;


public class UpdateCourse extends AbstractDAO implements Command {
    private CourseDAOimpl courseDAOimpl = CourseDAOimpl.getInstance();
    private Course course;

    public UpdateCourse(Course course) {
        this.course = course;
    }

    @Override
    public Object execute() {
        courseDAOimpl.update(course);
        return course.getId();
    }
}
