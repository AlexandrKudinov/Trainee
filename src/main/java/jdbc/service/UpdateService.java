package jdbc.service;

import jdbc.dao.DAO;
import jdbc.entity.Entity;

public class UpdateService implements Service {
    private Entity entity;
    private DAO<Entity> dao;

    public UpdateService(DAO dao, Entity entity) {
        this.dao = dao;
        this.entity = entity;
    }

    @Override
    public String execute() {
        return dao.update(entity);
    }

}