package jdbc.controller;

import jdbc.bl.CommandType;

import javax.servlet.http.HttpServletRequest;

public class Controller {
    HttpServletRequest request;
    String commandNumber;


    public Controller(HttpServletRequest request) {
        this.request = request;
        commandNumber = request.getParameter("command");
    }

    public String process() {
        int integer = Integer.valueOf(commandNumber);
        CommandType commandType = CommandType.valueOf(integer);
        return commandType.action(request);
    }




}
