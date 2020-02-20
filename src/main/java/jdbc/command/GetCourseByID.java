package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.CourseDAOimpl;

public class GetCourseByID extends AbstractDAO implements Command {
    private CourseDAOimpl courseDAOimpl = CourseDAOimpl.getInstance();
    private int id;

    public GetCourseByID(int id){
        this.id = id;
    }

    @Override
    public Object execute() {
        return courseDAOimpl.getByID(id);
    }
}
