package jdbc.bl;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public enum CommandType {

    ADD_COURSE(1) {
        public void action() throws IOException, ParseException {
            DBConnector.getInstance().runAddCourse();
        }
    },
    ADD_PERSON(2) {
        public void action() throws IOException {
            DBConnector.getInstance().runAddPerson();
        }
    },
    GET_COURSE_BY_ID(3) {
        public void action() throws IOException {
            DBConnector.getInstance().runGetCourseByID();
        }
    },
    GET_PERSON_BY_ID(4) {
        public void action() throws IOException {
            DBConnector.getInstance().runGetPersonByID();
        }
    },
    REMOVE_COURSE(5) {
        public void action() throws IOException {
            DBConnector.getInstance().runRemoveCourse();
        }
    },
    REMOVE_PERSON(6) {
        public void action() throws IOException {
            DBConnector.getInstance().runRemovePerson();
        }
    },
    UPDATE_COURSE(7) {
        public void action() throws IOException, ParseException {
            DBConnector.getInstance().runUpdateCourse();
        }
    },
    UPDATE_PERSON(8) {
        public void action() throws IOException {
            DBConnector.getInstance().runUpdatePerson();
        }
    },
    EXIT(0) {
        public void action() {
            System.out.println("Exiting...");
            System.exit(0);
        }
    };

    public abstract void action() throws IOException, ParseException;

    private static Map<Integer,CommandType> map = new HashMap<>();

    static {
        for (CommandType commandType : CommandType.values()) {
            map.put(commandType.value, commandType);
        }
    }


    public static CommandType valueOf(int value) {
        return map.get(value);
    }

    private int value;

    CommandType(int value) {
        this.value = value;
    }
}