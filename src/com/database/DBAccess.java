package com.database;

/**
 * Created by ma7mot on 21/01/2016.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class DBAccess {
    private Connection conn;
    private Statement stmt;
    private ResultSet result;

    public void connect() throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/abetas?user=root");
        stmt= conn.createStatement();
        result = stmt.executeQuery("select * from users");

        while (result.next()){
            System.out.println(result.getString(0));
        }

    }

}
