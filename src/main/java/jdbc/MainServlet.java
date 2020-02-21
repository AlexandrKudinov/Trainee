package jdbc;

import com.google.gson.Gson;
import jdbc.bl.DBConnector;
import jdbc.controller.Controller;
import jdbc.entity.Person;

import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String s = request.getQueryString();
        PrintWriter printWriter = response.getWriter();
        Controller controller = new Controller(request);
        printWriter.println("<p> " + controller.process() + " </p1>");
        //http://localhost:8080/main?command=1&id=3333&title=mmm&createdAt=qqqq&startDateTime=11-11-1111&endDateTime=12-12-1212&teacherID=2&
    }
}
