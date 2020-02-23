package jdbc.service;

import jdbc.dao.DAO;
import jdbc.entity.Entity;

public class AddService implements Service {
    private Entity entity;
    private DAO<Entity> dao;

    public AddService(DAO dao, Entity entity) {
        this.dao = dao;
        this.entity = entity;
    }

    @Override
    public String execute() {
        return dao.add(entity);
    }

}


