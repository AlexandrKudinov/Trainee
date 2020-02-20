package jdbc.command;

import jdbc.bl.AbstractDAO;
import jdbc.dao.DAO;
import jdbc.entity.Entity;

public class Update extends AbstractDAO implements Command {
    private Entity entity;
    private DAO<Entity> dao;

    public Update(DAO dao, Entity entity) {
        this.dao = dao;
        this.entity = entity;
    }

    @Override
    public Entity execute() {
        dao.update(entity);
        return entity;
    }
}