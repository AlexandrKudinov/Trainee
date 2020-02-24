package jdbc.controller;

public class DispatcherServlet {
    public Controller process(String url) {
        switch (url) {
            case "Persons":
                return new PersonController();
            case "Courses":
                return new CourseController();
            default:
                return null;
        }
    }
}
