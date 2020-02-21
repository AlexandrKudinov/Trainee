package jdbc.bl;


import jdbc.dao.Container;
import jdbc.dao.CourseDAOimpl;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.Person;
import jdbc.entity.Status;
import jdbc.entity.Type;
import jdbc.service.AddService;
import jdbc.service.Service;
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
            CourseDAOimpl courseDAOimpl = new CourseDAOimpl();
            Service service = new AddService(courseDAOimpl, course);
            return service.execute();
        }
    },
    //http://localhost:8080/main?command=1&id=3333&title=mmm&createdAt=qqqq&startDateTime=11-11-1111&endDateTime=12-12-1212&teacherID=2&status=open

    ADD_PERSON(2) {
        public String action(HttpServletRequest request) {
            Person person = CommandType.getPerson(request);
            PersonDAOimpl personDAOimpl = new PersonDAOimpl();
            Service service = new AddService(personDAOimpl,person);
            return service.execute();
        }
    },
    GET_COURSE_BY_ID(3) {
        public String action(HttpServletRequest request) {
            return null;
        }
    },
    GET_PERSON_BY_ID(4) {
        public String action(HttpServletRequest request) {
            return null;
        }
    },
    REMOVE_COURSE(5) {
        public String action(HttpServletRequest request) {
            return null;
        }
    },
    REMOVE_PERSON(6) {
        public String action(HttpServletRequest request) {
            return null;
        }
    },
    UPDATE_COURSE(7) {
        public String action(HttpServletRequest request) {
            return null;
        }
    },
    UPDATE_PERSON(8) {
        public String action(HttpServletRequest request) {
            return null;
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


}