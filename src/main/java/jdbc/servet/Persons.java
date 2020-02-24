package jdbc.servet;

import jdbc.controller.DispatcherServlet;

import javax.servlet.annotation.WebServlet;


@WebServlet(name = "Persons")
public class Persons extends EntityServlet {
    public Persons(){
        super.ds = new DispatcherServlet().process("Persons");
    }
}

