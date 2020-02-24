package jdbc.service;

import jdbc.dao.DAO;
import jdbc.entity.Entity;

public class RemoveService implements Service  {
    private DAO<Entity> dao;
    private Entity entity;

    public RemoveService(DAO dao, Entity entity) {
        this.dao = dao;
        this.entity = entity;
    }

    @Override
    public String execute() {
        return dao.remove(entity);
    }

}