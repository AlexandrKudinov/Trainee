package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.DAO;
import jdbc.entity.Entity;

public class Remove extends AbstractDAO implements Command {
    private DAO<Entity> dao;
    private Integer id;

    public Remove(DAO  dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public Object execute() {
        dao.remove(id);
        return id;
    }

}
