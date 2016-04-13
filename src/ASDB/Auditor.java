package ASDB;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Auditor class is used to handle recording every action done by the users in the system by inserting new record to Auditor
 * table in the database.
 */
public class Auditor {
    /**
     * record date and time and action for every event
     * @param userName username of the user as String
     * @param action action done by the user as String
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
