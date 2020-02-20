package jdbc.bl;

import jdbc.command.*;

import jdbc.dao.Container;
import jdbc.dao.DAO;
import jdbc.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

public class DBConnector {

    private Course course;
    private Person person;
    private Type personType;
    private int personId;
    private String personName;
    private int courseId;
    private String title;
    private Date startDatetime;
    private Date endDatetime;
    private Status status;
    private int teacherId;
    private String createdAt;
    private Calendar calendar;
    private boolean inputUnCorrect = true;
    private boolean requestCanceled = false;
    private String input;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Receiver receiver;
    private Command command;
    private DAO courseDAOimpl = Container.getCourseDAOimpl();
    private DAO personDAOimpl = Container.getPersonDAOimpl();

    private static volatile DBConnector INSTANCE;

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        DBConnector dbConnector = INSTANCE;
        if (dbConnector == null) {
            synchronized (DBConnector.class) {
                dbConnector = INSTANCE = new DBConnector();

            }
        }
        return dbConnector;
    }

    private String instruction = "\n !!!! WELCOME TO DB CONNECTOR !!!!\n " +
            "INSTRUCTION: \n " +
            "-input number of command and 'enter' to call\n" +
            " ******* commands numbers **********\n " +
            "    1 - add course\n " +
            "    2 - add person\n " +
            "    3 - get course by id\n " +
            "    4 - get person by id\n " +
            "    5 - remove course\n " +
            "    6 - remove person\n " +
            "    7 - update course\n " +
            "    8 - update person\n " +
            "    q - request canceled\n " +
            "    0 - exit";

    public void start() throws IOException, ParseException {
        System.out.println(instruction);
        while (true) {
            System.out.println("enter the command:");
            input = br.readLine();
            assert input != null;
            try {
                int integer = Integer.valueOf(input);
                CommandType commandType = CommandType.valueOf(integer);
                commandType.action();
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Wrong command number!");
            }
        }
    }

    void runAddCourse() throws IOException, ParseException {
        getCourse();
        if (requestCanceled) {
            requestCanceled = false;
            return;
        }
        command = new Add(courseDAOimpl, course);
        runReceiver();
        System.out.println("Course " + courseId + " added");
    }

    void runAddPerson() throws IOException {
        getPerson();
        if (requestCanceled) {
            requestCanceled = false;
            return;
        }
        setPersonType(personType);
        command = new Add(personDAOimpl, person);
        runReceiver();
        System.out.println("Person " + personId + " added");
    }

    void runGetCourseByID() throws IOException {
        checkCourseID();
        command = new GetByID(courseDAOimpl, courseId);
        receiver = new Receiver(command);
        Course course = (Course) receiver.runCommand();
        System.out.println("Required course:");
        System.out.println(course.toString());
    }

    void runGetPersonByID() throws IOException {
        checkPersonType();
        checkPersonID();
        setPersonType(personType);
        command = new GetByID(personDAOimpl, personId);
        receiver = new Receiver(command);
        Person person = (Person) receiver.runCommand();
        System.out.println("Required person:");
        System.out.println(person.toString());
    }

    void runRemoveCourse() throws IOException {
        checkCourseID();
        command = new Remove(courseDAOimpl, courseId);
        runReceiver();
        System.out.println("Course " + courseId + " removed successfully");
    }

    void runRemovePerson() throws IOException {
        checkPersonType();
        checkPersonID();
        setPersonType(personType);
        command = new Remove(personDAOimpl, personId);
        runReceiver();
        System.out.println("Person " + personId + " removed successfully");
    }

    void runUpdateCourse() throws IOException, ParseException {
        getCourse();
        command = new Update(courseDAOimpl, course);
        runReceiver();
        System.out.println("Course " + courseId + " updated");
    }

    void runUpdatePerson() throws IOException {
        getPerson();
        command = new Update(personDAOimpl, person);
        runReceiver();
        System.out.println("Person " + personId + " updated");
    }

    private void runReceiver() {
        receiver = new Receiver(command);
        receiver.runCommand();
    }

    private void setPersonType(Type personType) {
        Container.getPersonDAOimpl().setType(personType);
    }

    private void checkCourseID() throws IOException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter id (int):");
            input = br.readLine();
            if (input.matches("\\d+")) {
                inputUnCorrect = false;
                courseId = Integer.valueOf(input);
            }
        }
        inputUnCorrect = true;
    }

    private void checkTitle() throws IOException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course title (String):");
            input = br.readLine();
            if (callRequestCancel()) {
                return;
            }
            title = checkInputString();
        }
        inputUnCorrect = true;
    }

    private Calendar getCalendar(String inputDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf.parse(inputDate);
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Date getDataTime() throws ParseException {
        Date datetime = null;
        if (input.matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
            Calendar calendar = getCalendar(input);
            datetime = new java.sql.Date(calendar.getTime().getTime());
            inputUnCorrect = false;
        }
        return datetime;
    }

    private boolean callRequestCancel() {
        if (input.equals("q")) {
            System.out.println("The request canceled!");
            requestCanceled = true;
            return true;
        }
        return false;
    }

    private void checkStartDatetime() throws IOException, ParseException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course startDatetime (dd-MM-yyyy):");
            input = br.readLine();
            if (callRequestCancel()) {
                return;
            }
            startDatetime = getDataTime();
        }
        inputUnCorrect = true;
    }

    private void checkEndDatetime() throws IOException, ParseException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course endDatetime (dd-MM-yyyy):");
            input = br.readLine();
            if (callRequestCancel()) {
                return;
            }
            endDatetime = getDataTime();
        }
        inputUnCorrect = true;
    }

    private void checkStatus() throws IOException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course status (open, on_study, studied, rejected):");
            input = br.readLine();
            if (callRequestCancel()) {
                return;
            }
            if (input.matches("\\D+")) {
                try {
                    status = Status.valueOf(input);
                    inputUnCorrect = false;
                } catch (IllegalArgumentException e) {
                    System.out.println("wrong status!");
                }
            }
        }
        inputUnCorrect = true;
    }

    private void checkTeacherID() throws IOException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course teacherID (int):");
            input = br.readLine();
            if (callRequestCancel()) {
                return;
            }
            if (input.matches("\\d")) {
                teacherId = Integer.valueOf(input);
                inputUnCorrect = false;
            }
        }
        inputUnCorrect = true;
    }

    private String checkInputString() {
        if (input.matches("\\D+")) {
            inputUnCorrect = false;
            return input;
        }
        return null;
    }

    private void checkCreatedAt() throws IOException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course createdAt (String):");
            input = br.readLine();
            if (callRequestCancel()) {
                return;
            }
            createdAt = checkInputString();
        }
        inputUnCorrect = true;
    }

    private void getPerson() throws IOException {
        System.out.println(" Enter the person columns: ");
        checkPersonType();
        checkPersonID();
        checkPersonName();

        if (requestCanceled) {
            return;
        }
        person = Person.builder()
                .type(personType)
                .id(personId)
                .name(personName)
                .build();

    }

    private void checkPersonType() throws IOException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the person type ( teacher , student ):");
            input = br.readLine();
            if (callRequestCancel()) {
                return;
            }
            if (input.matches("\\D+")) {
                try {
                    personType = Type.valueOf(input);
                    inputUnCorrect = false;
                } catch (IllegalArgumentException e) {
                    System.out.println("wrong person type!");
                }
            }
        }
        inputUnCorrect = true;
    }

    private void checkPersonID() throws IOException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter id (int):");
            input = br.readLine();
            if (callRequestCancel()) {
                return;
            }
            if (input.matches("\\d+")) {
                inputUnCorrect = false;
                personId = Integer.valueOf(input);
            }
        }
        inputUnCorrect = true;
    }

    private void checkPersonName() throws IOException {
        if (requestCanceled) {
            return;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the person name (String):");
            input = br.readLine();
            if (callRequestCancel()) {
                return;
            }
            personName = checkInputString();
        }
        inputUnCorrect = true;
    }

    private void getCourse() throws IOException, ParseException {
        System.out.println(" Enter the course columns: ");
        checkCourseID();
        checkTitle();
        checkStartDatetime();
        checkEndDatetime();
        checkStatus();
        checkTeacherID();
        checkCreatedAt();
        if (requestCanceled) {
            return;
        }
        course = Course.builder()
                .id(courseId)
                .title(title)
                .startDatetime(startDatetime)
                .endDatetime(endDatetime)
                .teacherId(teacherId)
                .createdAt(createdAt)
                .status(status)
                .build();
    }

}

