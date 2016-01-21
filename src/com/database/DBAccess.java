package com.database;

import java.sql.*;

/**
 * Created by Ibrahim Abuaqel on 1/19/2016.
 */
public class DBAccess {

    private Connection conn;
    private Statement stmt;
    private ResultSet result;

    public void connect() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        conn=DriverManager.getConnection("jdbc:mysql://localhost/abetasdb?user=root&password=abetas");

    }

    public void select(String name, String logo) throws ClassNotFoundException, SQLException {

        connect();
        //stmt=conn.createStatement();
        //result=stmt.executeQuery("select * from university");
        String query = "select * from university";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        result=preparedStmt.executeQuery();

        while (result.next()){
            System.out.println(result.getString(1)+" "+result.getString(2));
        }
        conn.close();
    }

    public void insert(String name, String logo) throws ClassNotFoundException, SQLException {

        connect();
        // the mysql insert statement
        String query = " insert into university (Uni_name, Uni_logo)" + " values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, name);
        preparedStmt.setString (2, logo);

        preparedStmt.execute();

        //or insert into university (first_name, last_name) values ('Fred', 'Flinstone');
        conn.close();
    }

    public void update(String name, String logo) throws ClassNotFoundException, SQLException {

        connect();
        String query = "update university set Uni_logo = ? where Uni_name = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, logo);
        preparedStmt.setString(2, name);

        preparedStmt.execute();
        //or update users set Uni_logo = "Changed" where Uni_name = a;
        conn.close();

    }

    public void delete(String name, String logo) throws ClassNotFoundException, SQLException {

        connect();
        String query = "delete from university where Uni_name = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, name);

        preparedStmt.execute();

    }
}
