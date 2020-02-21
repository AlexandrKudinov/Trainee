package jdbc;


import jdbc.bl.DBConnector;

import java.io.IOException;

import java.text.ParseException;

public class Runner {
    public static void main(String[] args) throws IOException, ParseException {
        DBConnector dbConnector = DBConnector.getInstance();
        dbConnector.start();
    }
}
