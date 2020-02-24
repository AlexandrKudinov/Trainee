package jdbc.servet;

import com.google.gson.Gson;
import jdbc.controller.Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EntityServlet")
public class EntityServlet extends HttpServlet {
    protected Controller ds;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        ds.setEntity(request);
        ds.runService("Add");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        ds.setEntity(request);
        ds.runService("Remove");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        ds.setEntity(request);
        ds.runService("Update");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ds.setEntity(request);
        out.print(new Gson().toJson(ds.runService("GetById")));
    }
}
