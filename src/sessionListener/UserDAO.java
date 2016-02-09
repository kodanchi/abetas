package sessionListener;

import ASDB.AS_Select;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

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

    public User find(String username, int userLvl) throws SQLException, ClassNotFoundException {

        String userTable = "";
        String userTableCols = "";
        String usernameTName = "";
        User user = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;

        switch (userLvl){
            case 0:
                userTable = "superuser";
                userTableCols = "Super_ID, Super_Username, Super_Email";
                usernameTName = "Super_Username";
                break;
            case 1:
                userTable = "faculty_member";
                userTableCols = "Faculty_ID,Faculty_Username,Faculty_Email";
                usernameTName = "Faculty_Username";
                break;

            case 2:
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
                user = new User(rs.getInt(1), rs.getString(2),rs.getString(3),userLvl);
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
