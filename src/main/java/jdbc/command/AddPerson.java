package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.Person;

public class AddPerson extends AbstractDAO implements Command {
    private PersonDAOimpl personDAOimpl = new PersonDAOimpl();
    private Person person;

    public AddPerson(Person person) {
        this.person = person;
    }

    @Override
    public Object execute() {
        personDAOimpl.add(person);
        return person.getId();
    }
}
