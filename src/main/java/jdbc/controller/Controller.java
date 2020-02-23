package jdbc.controller;

import jdbc.bl.CommandType;

import javax.servlet.http.HttpServletRequest;

public class Controller {
    private HttpServletRequest request;
    private String commandNumber;

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
