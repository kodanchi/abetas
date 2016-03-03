package sessionListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Mojahed on 2/9/2016.
 */
public class UserDAO {

    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    DataSource dataSource = null;

    public void connect() throws ClassNotFoundException, SQLException {

        try
        {
            /*
             *  Using JDNI lookup get the DataSource.
             */

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/abetasDB");

        }
        catch( Exception exe )
        {
            exe.printStackTrace();
        }

    }

    public ArrayList<String> find(String username, int userLvl) throws SQLException, ClassNotFoundException {

        String userTable = "";
        String userTableCols = "";
        String usernameTName = "";
        //User user = null;
        ArrayList<String> user = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;

        switch (userLvl){
            case 0:
            case 1:
                userTable = "superuser";
                userTableCols = "Super_ID, Super_Username, Super_Email";
                usernameTName = "Super_Username";
                break;
            case 2:
                userTable = "faculty_member";
                userTableCols = "Faculty_ID,Faculty_Username,Faculty_Email";
                usernameTName = "Faculty_Username";
                break;

            case 3:
                userTable = "evaluator";
                userTableCols = "E_ID, E_Username, null";
                usernameTName = "E_Username";
                break;
        }
        try {

            String query = "select "+userTableCols+" FROM "+userTable+" where "+usernameTName+"='"+username+"';";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, 10);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                user.add(String.valueOf(rs.getInt(1)));
                user.add(rs.getString(2));
                user.add(rs.getString(3));
                user.add(String.valueOf(userLvl));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */rs.close();
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

            return user;

        }
    }
}
