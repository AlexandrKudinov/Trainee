package jdbc.service;

import jdbc.dao.DAO;
import jdbc.entity.Entity;

public class GetByIDService implements Service  {
    private DAO<Entity> dao;

    private int id;

    public GetByIDService(DAO dao, int id){
        this.dao = dao;
        this.id = id;
    }

    @Override
    public String execute() {
        return dao.getByID(id);
    }
}
