package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.Person;

public class UpdatePerson extends AbstractDAO implements Command {
    private PersonDAOimpl personDAOimpl = PersonDAOimpl.getInstance();
    private Person person;

    public UpdatePerson(Person person) {
        this.person = person;
    }

    @Override
    public Object execute() {
       personDAOimpl.update(person);
        return person.getId();
    }
}
