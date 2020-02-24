package jdbc.controller;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public interface Controller {
    Object runService(String serviceName);

    void setEntity(HttpServletRequest request);

    default String getJSON(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
