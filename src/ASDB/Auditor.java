package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Ibrahim Abuaqel on 3/10/2016.
 */
public class Auditor {
    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    DataSource dataSource = null;

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
