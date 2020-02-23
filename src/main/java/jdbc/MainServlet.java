package jdbc;

import jdbc.controller.Controller;

import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        Controller controller = new Controller(request);
        printWriter.println("Commands: " +
                            " 1 - add course" +
                            " 2 - add person" +
                            " 3 - get course by id" +
                            " 4 - get person by id" +
                            " 5 - remove course" +
                            " 6 - remove person " +
                            " 7 - update course" +
                            " 8 - update person ");
        printWriter.println("Data output: "+ controller.process());
    }

}
//http://localhost:8080/main?command=1&id=3333&title=mmm&createdAt=qqqq&startDateTime=11-11-1111&endDateTime=12-12-1212&teacherId=2&status=open