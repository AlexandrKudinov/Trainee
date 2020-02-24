package jdbc.controller;

import com.google.gson.Gson;
import jdbc.dao.Container;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.Entity;
import jdbc.entity.Person;
import jdbc.service.*;

import javax.servlet.http.HttpServletRequest;

public class PersonController implements Controller {
    private Person person;

    public void setEntity(HttpServletRequest request) {
        this.person = (Person) getPerson(request);
    }

    public Object runService(String serviceName) {
        Service service;
        switch (serviceName) {
            case "Add":
                service = new AddService(setPersonDAOimpl(), person);
                break;
            case "GetById":
                service = new GetByIDService(setPersonDAOimpl(), person);
                break;
            case "Update":
                service = new UpdateService(setPersonDAOimpl(), person);
                break;
            case "Remove":
                service = new RemoveService(setPersonDAOimpl(), person);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return service.execute();
    }

    private Entity getPerson(HttpServletRequest request) {
        return new Gson().fromJson(getJSON(request), Person.class);
    }

    private PersonDAOimpl setPersonDAOimpl() {
        PersonDAOimpl personDAOimpl = (PersonDAOimpl) Container.get(PersonDAOimpl.class);
        personDAOimpl.setType(person.getType());
        return personDAOimpl;
    }
}
