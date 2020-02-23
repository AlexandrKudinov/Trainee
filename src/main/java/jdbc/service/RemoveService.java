package jdbc.service;

import jdbc.dao.DAO;
import jdbc.entity.Entity;

public class RemoveService implements Service  {
    private DAO<Entity> dao;
    private Integer id;

    public RemoveService(DAO  dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public String execute() {
        return dao.remove(id);
    }
}