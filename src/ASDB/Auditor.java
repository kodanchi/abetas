package ASDB;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Auditor {
    /**
     * record date and time for every event
     * @param userName String
     * @param action String
     */
    public static void add(String userName, String action){
        U_AS_Insert dbs = new U_AS_Insert();
        try {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date());
            dbs.audit(userName,action,timeStamp);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
