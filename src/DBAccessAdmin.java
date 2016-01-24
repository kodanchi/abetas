import java.sql.*;

/**
 * Created by Abdullah on 1/21/2016.
 */
public class DBAccessAdmin {
    private Connection conn;
    private Statement stmt;
    private ResultSet result;

    public void connect() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        conn= DriverManager.getConnection("jdbc:mysql://localhost/abetasdb?user=root&password=abetas");

    }

    public void select(String adminUsername, String password, String email , String adminFirstName, String adminMiddleName, String adminLastName) throws ClassNotFoundException, SQLException {

        connect();
        //stmt=conn.createStatement();
        //result=stmt.executeQuery("select * from university");
        String query = "select * from superuser";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        result=preparedStmt.executeQuery();

        while (result.next()){
            System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4)+" "+result.getString(5)+" "+result.getString(6));
        }
        conn.close();
    }

    public void insert(String adminUsername, String password, String email, String adminFirstName, String adminMiddleName, String adminLastName) throws ClassNotFoundException, SQLException {

        connect();
        // the mysql insert statement
        String query = " insert into superuser (Super_Username,Super_Password,Super_Email,Super_Fname,Super_Mname,Super_Lname,Adm_ID)" + " values (?, ?, ?,?, ?, ?,1)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, adminUsername);
        preparedStmt.setString (2, password);
        preparedStmt.setString (3, email);
        preparedStmt.setString (4, adminFirstName);
        preparedStmt.setString (5, adminMiddleName);
        preparedStmt.setString (6, adminLastName);



        preparedStmt.execute();

        //or insert into university (first_name, last_name) values ('Fred', 'Flinstone');
        conn.close();
    }


}
