package jdbc.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdbc.dao.Container;
import jdbc.dao.CourseDAOimpl;
import jdbc.entity.Course;
import jdbc.entity.Entity;
import jdbc.service.*;

import javax.servlet.http.HttpServletRequest;

public class CourseController implements Controller {
    private Course course;

    public void setEntity(HttpServletRequest request) {
        this.course = (Course) getCourse(request);
    }

    public Object runService(String serviceName) {
        Service service;
        switch (serviceName) {
            case "Add":
                service = new AddService(Container.get(CourseDAOimpl.class), course);
                break;
            case "GetById":
                service = new GetByIDService(Container.get(CourseDAOimpl.class), course);
                break;
            case "Update":
                service = new UpdateService(Container.get(CourseDAOimpl.class), course);
                break;
            case "Remove":
                service = new RemoveService(Container.get(CourseDAOimpl.class), course);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return service.execute();
    }

    private Entity getCourse(HttpServletRequest request) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy").create();
        return gson.fromJson(getJSON(request), Course.class);
    }
}
