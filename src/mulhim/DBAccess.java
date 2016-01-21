package mulhim;

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
        stmt=conn.createStatement();
        result=stmt.executeQuery("select * from university");

        while (result.next()){
            System.out.println(result.getString(1)+" "+result.getString(2));
        }
    }

}
