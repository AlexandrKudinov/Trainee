package jdbc;

import jdbc.bl.AbstractDAO;
import jdbc.bl.DBConnector;

import java.io.IOException;

import java.text.ParseException;


public abstract class Domain extends AbstractDAO {

    public static void main(String[] args) throws IOException, ParseException {
        DBConnector dbConnector = DBConnector.getInstance();
        dbConnector.start();

    }
}
