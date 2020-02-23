package jdbc.bl;


import jdbc.dao.Container;
import jdbc.dao.CourseDAOimpl;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.Person;
import jdbc.entity.Status;
import jdbc.entity.Type;
import jdbc.service.*;
import jdbc.entity.Course;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public enum CommandType {

    ADD_COURSE(1) {
        public String action(HttpServletRequest request) {
            Course course = CommandType.getCourse(request);
            Service service = new AddService(Container.get(CourseDAOimpl.class), course);
            return service.execute();
        }
    },

    ADD_PERSON(2) {
        public String action(HttpServletRequest request) {
            Person person = CommandType.getPerson(request);
            PersonDAOimpl personDAOimpl = CommandType.setPersonDAOimplType(request);
            Service service = new AddService(personDAOimpl, person);
            return service.execute();
        }
    },

    GET_COURSE_BY_ID(3) {
        public String action(HttpServletRequest request) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Service service = new GetByIDService(Container.get(CourseDAOimpl.class), id);
            return service.execute();
        }
    },
    GET_PERSON_BY_ID(4) {
        public String action(HttpServletRequest request) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            PersonDAOimpl personDAOimpl = CommandType.setPersonDAOimplType(request);
            Service service = new GetByIDService(personDAOimpl, id);
            return service.execute();
        }
    },
    REMOVE_COURSE(5) {
        public String action(HttpServletRequest request) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Service service = new RemoveService(Container.get(CourseDAOimpl.class), id);
            return service.execute();
        }
    },
    REMOVE_PERSON(6) {
        public String action(HttpServletRequest request) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            PersonDAOimpl personDAOimpl = CommandType.setPersonDAOimplType(request);
            Service service = new RemoveService(personDAOimpl, id);
            return service.execute();
        }
    },
    UPDATE_COURSE(7) {
        public String action(HttpServletRequest request) {
            Course course = CommandType.getCourse(request);
            Service service = new UpdateService(Container.get(CourseDAOimpl.class), course);
            return service.execute();
        }
    },
    UPDATE_PERSON(8) {
        public String action(HttpServletRequest request) {
            Person person = CommandType.getPerson(request);
            PersonDAOimpl personDAOimpl = CommandType.setPersonDAOimplType(request);
            Service service = new UpdateService(personDAOimpl, person);
            return service.execute();
        }
    },
    EXIT(0) {
        public String action(HttpServletRequest request) {
            return null;
        }
    };

    private int value;

    public abstract String action(HttpServletRequest request);


    public static CommandType valueOf(int value) {
        return Arrays.stream(CommandType.values()).filter(commandType1 ->
                commandType1.value == value).findAny().orElse(null);
    }

    CommandType(int value) {
        this.value = value;
    }

    private static Calendar getCalendar(String inputDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf.parse(inputDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private static Course getCourse(HttpServletRequest request) {
        Calendar startCalendar = null;
        Calendar endCalendar = null;
        try {
            startCalendar = getCalendar(request.getParameter("startDateTime"));
            endCalendar = getCalendar(request.getParameter("endDateTime"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Course.builder()
                .id(Integer.valueOf(request.getParameter("id")))
                .title(request.getParameter("title"))
                .status(Status.valueOf(request.getParameter("status")))
                .createdAt(request.getParameter("createdAt"))
                .startDatetime(new Date(startCalendar.getTime().getTime()))
                .endDatetime(new Date(endCalendar.getTime().getTime()))
                .teacherId(Integer.valueOf(request.getParameter("teacherId")))
                .build();
    }

    private static Person getPerson(HttpServletRequest request) {
        return Person.builder()
                .id(Integer.valueOf(request.getParameter("id")))
                .name(request.getParameter("name"))
                .type(Type.valueOf(request.getParameter("type")))
                .build();

    }


    private static PersonDAOimpl setPersonDAOimplType(HttpServletRequest request) {
        PersonDAOimpl personDAOimpl = (PersonDAOimpl) (Container.get(PersonDAOimpl.class));
        personDAOimpl.setType(Type.valueOf(request.getParameter("type")));
        return personDAOimpl;
    }
}