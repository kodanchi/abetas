package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ibrahim Abuaqel on 3/10/2016.
 */
public class Auditor {
    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    DataSource dataSource = null;

    public static void add(String userName, String action, String time){
        AS_Insert dbs = new AS_Insert();
        try {
            dbs.audit(userName,action,time);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
