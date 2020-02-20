package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.CourseDAOimpl;

public class RemoveCourse extends AbstractDAO implements Command{
    private CourseDAOimpl courseDAOimpl = CourseDAOimpl.getInstance();
    private int id;

    public RemoveCourse(int id){
        this.id=id;
    }
    @Override
    public Object execute() {
      courseDAOimpl.remove(id);
      return id;
    }

}
