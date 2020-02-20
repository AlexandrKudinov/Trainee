package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.Type;

public class RemovePerson extends AbstractDAO implements Command {
    private PersonDAOimpl personDAOimpl = PersonDAOimpl.getInstance();
    private int id;

    public RemovePerson(Type type, int id) {
       personDAOimpl.setType(type);
        this.id=id;
    }

    @Override
    public Object execute() {
        personDAOimpl.remove(id);
        return id;
    }
}
