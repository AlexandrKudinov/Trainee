package jdbc.service;

import jdbc.dao.DAO;
import jdbc.entity.Entity;

public class GetByIDService implements Service  {
    private Entity entity;
    private DAO<Entity> dao;

    public GetByIDService(DAO dao, Entity entity){
        this.dao = dao;
        this.entity = entity;
    }

    @Override
    public Entity execute() {
        return dao.getByID(entity);
    }

}
