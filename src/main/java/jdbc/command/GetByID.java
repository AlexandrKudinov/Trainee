package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.DAO;
import jdbc.entity.Entity;

public class GetByID extends AbstractDAO implements Command {
    private DAO<Entity> dao;

    private int id;

    public GetByID(DAO dao, int id){
        this.dao = dao;
        this.id = id;
    }

    @Override
    public String execute() {
        return dao.getByID(id);
    }
}

