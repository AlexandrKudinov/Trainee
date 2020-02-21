package jdbc.bl;

import jdbc.command.*;

import jdbc.dao.Container;
import jdbc.dao.CourseDAOimpl;
import jdbc.dao.PersonDAOimpl;
import jdbc.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

public class DBConnector {
    private boolean inputUnCorrect = true;
    private boolean requestCanceled = false;
    private String input;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private CourseDAOimpl courseDAOimpl = (CourseDAOimpl) Container.get(CourseDAOimpl.class);
    private PersonDAOimpl personDAOimpl = (PersonDAOimpl) Container.get(PersonDAOimpl.class);

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

    public static String instruction = "\n !!!! WELCOME TO DB CONNECTOR !!!!\n " +
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

    public void start() throws IOException {
        System.out.println(instruction);
        while (true) {
            System.out.println("enter the command:");
            input = br.readLine();
            if (input != null) {
                try {
                    int integer = Integer.valueOf(input);
//                    CommandType commandType = CommandType.valueOf(integer);
//                    //  commandType.action(input);
                    if(input.equals("1")){
                        runAddCourse();
                    }
                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println("Wrong command number!");
                }
            }
        }
    }

    void runAddCourse() {
        if (requestCanceled) {
            requestCanceled = false;
            return;
        }
        runReceiver(new Add(courseDAOimpl, getCourse()));
        System.out.println("Course added");
    }

    void runAddPerson() {
        if (requestCanceled) {
            requestCanceled = false;
            return;
        }
        runReceiver(new Add(personDAOimpl, getPerson()));
        System.out.println("Person added");
    }

    void runGetCourseByID() {
        Receiver receiver = new Receiver(new GetByID(courseDAOimpl, checkCourseID()));
        Course course = (Course) receiver.runCommand();
        System.out.println("Required course:");
        System.out.println(course.toString());
    }

    void runGetPersonByID() {
        try {
            setPersonType(checkPersonType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Receiver receiver = new Receiver(new GetByID(personDAOimpl, checkPersonID()));
        Person person = (Person) receiver.runCommand();
        System.out.println("Required person:");
        System.out.println(person.toString());
    }

    void runRemoveCourse() {
        runReceiver(new Remove(courseDAOimpl, checkCourseID()));
        System.out.println("Course  removed successfully");
    }

    void runRemovePerson() {
        try {
            setPersonType(checkPersonType());
            runReceiver(new Remove(personDAOimpl, checkPersonID()));
            System.out.println("Person  removed successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void runUpdateCourse() {
        runReceiver(new Update(courseDAOimpl, getCourse()));
        System.out.println("Course  updated");
    }

    void runUpdatePerson() {
        runReceiver(new Update(personDAOimpl, getPerson()));
        System.out.println("Person updated");
    }

    private void runReceiver(Command command) {
        Receiver receiver = new Receiver(command);
        receiver.runCommand();
    }

    private void setPersonType(Type personType) {
        personDAOimpl.setType(personType);
    }

    private Integer checkCourseID() {
        Integer courseId = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter id (int):");
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.matches("\\d+")) {
                inputUnCorrect = false;
                courseId = Integer.valueOf(input);
            }
        }
        inputUnCorrect = true;
        return courseId;
    }

    private String checkTitle() throws IOException {
        String title = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course title (String):");
            input = br.readLine();
            if (callRequestCancel()) {
                return null;
            }
            title = checkInputString();
        }
        inputUnCorrect = true;
        return title;
    }

    private Calendar getCalendar(String inputDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf.parse(inputDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Date getDataTime() throws ParseException {
        Date datetime = null;
        if (input.matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
            Calendar calendar = getCalendar(input);
            datetime = new Date(calendar.getTime().getTime());
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

    private Date checkStartDatetime() throws IOException, ParseException {
        Date startDatetime = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course startDatetime (dd-MM-yyyy):");
            input = br.readLine();
            if (callRequestCancel()) {
                return null;
            }
            startDatetime = getDataTime();
        }
        inputUnCorrect = true;
        return startDatetime;
    }

    private Date checkEndDatetime() throws IOException, ParseException {
        Date endDatetime = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course endDatetime (dd-MM-yyyy):");
            input = br.readLine();
            if (callRequestCancel()) {
                return null;
            }
            endDatetime = getDataTime();
        }
        inputUnCorrect = true;
        return endDatetime;
    }

    private Status checkStatus() throws IOException {
        Status status = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course status (open, on_study, studied, rejected):");
            input = br.readLine();
            if (callRequestCancel()) {
                return null;
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
        return status;
    }

    private Integer checkTeacherID() throws IOException {
        Integer teacherId = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course teacherID (int):");
            input = br.readLine();
            if (callRequestCancel()) {
                return null;
            }
            if (input.matches("\\d")) {
                teacherId = Integer.valueOf(input);
                inputUnCorrect = false;
            }
        }
        inputUnCorrect = true;
        return teacherId;
    }

    private String checkInputString() {
        if (input.matches("\\D+")) {
            inputUnCorrect = false;
            return input;
        }
        return null;
    }

    private String checkCreatedAt() throws IOException {
        String createdAt = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the course createdAt (String):");
            input = br.readLine();
            if (callRequestCancel()) {
                return null;
            }
            createdAt = checkInputString();
        }
        inputUnCorrect = true;
        return createdAt;
    }

    private Person getPerson() {
        System.out.println(" Enter the person columns: ");

        if (requestCanceled) {
            return null;
        }
        try {
            return Person.builder()
                    .type(checkPersonType())
                    .id(checkPersonID())
                    .name(checkPersonName())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    private Type checkPersonType() throws IOException {
        Type personType = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the person type ( teacher , student ):");
            input = br.readLine();
            if (callRequestCancel()) {
                return null;
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
        return personType;
    }

    private Integer checkPersonID() {
        Integer personId = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter id (int):");
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (callRequestCancel()) {
                return null;
            }
            if (input.matches("\\d+")) {
                inputUnCorrect = false;
                personId = Integer.valueOf(input);
            }
        }
        inputUnCorrect = true;
        return personId;
    }

    private String checkPersonName() throws IOException {
        String personName = null;
        if (requestCanceled) {
            return null;
        }
        while (inputUnCorrect) {
            System.out.println(" Enter the person name (String):");
            input = br.readLine();
            if (callRequestCancel()) {
                return null;
            }
            personName = checkInputString();
        }
        inputUnCorrect = true;
        return personName;
    }

    private Course getCourse() {
        System.out.println(" Enter the course columns: ");
        if (requestCanceled) {
            return null;
        }
        try {
            return Course.builder()
                    .id(checkCourseID())
                    .title(checkTitle())
                    .startDatetime(checkStartDatetime())
                    .endDatetime(checkEndDatetime())
                    .teacherId(checkTeacherID())
                    .createdAt(checkCreatedAt())
                    .status(checkStatus())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }
}

