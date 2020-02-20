package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.Type;

public class GetPersonByID extends AbstractDAO implements Command {
    private PersonDAOimpl personDAOimpl = PersonDAOimpl.getInstance();
    private int id;

    public GetPersonByID(Type personType, int id) {
        this.id = id;
        personDAOimpl.setType(personType);
    }

    @Override
    public Object execute() {
      return personDAOimpl.getByID(id);
    }
}
