package jdbc.servet;

import jdbc.controller.DispatcherServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "Courses")

public class Courses extends EntityServlet {
    public Courses(){
        super.ds = new DispatcherServlet().process("Courses");
    }
}
