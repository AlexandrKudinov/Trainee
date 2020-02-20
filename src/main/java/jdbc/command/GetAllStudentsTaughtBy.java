package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.Person;
import jdbc.entity.Type;

public class GetAllStudentsTaughtBy extends AbstractDAO implements Command {
    private PersonDAOimpl personDAOimpl = new PersonDAOimpl();
    private Person teacher;

    public GetAllStudentsTaughtBy(Person person){
        if(person.getType().equals(Type.student))
            throw new IllegalArgumentException();
        teacher = person;
    }

    @Override
    public Object execute() {
      return personDAOimpl.getStudentsTaughtBy(teacher);
    }

}
