package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Ibrahim Abuaqel on 2/4/2016.
 */
public class Settings_Update {

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
    public void closRS(ResultSet rs) throws SQLException {
        rs.close();
    }

    public void updateObjective(int Objective_label, String Objective) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */

            String query = "update p_objective set Objective = ? where Objective_label = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, Objective);
            preparedStatement.setInt (2, Objective_label);

            rs = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */
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

        }

    }

    public  void updateSuperuser(int id,String fname, String mname, String lname, String username, String email) throws SQLException, ClassNotFoundException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " UPDATE superuser SET `Super_Username`=?, `Super_Email`=?, `Super_Fname`=?, `Super_Mname`=?, `Super_Lname`=? WHERE `Super_ID`=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, fname);
            preparedStatement.setString(4, mname);
            preparedStatement.setString(5, lname);
            preparedStatement.setInt(6, id);
            rs = preparedStatement.executeUpdate();



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            /*
             * finally block used to close resources
             */
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
        }
    }

    public  void updateSuperuser(int id,String fname, String mname, String lname, String username, String email,String newPass) throws SQLException, ClassNotFoundException {

        System.out.println("got the new password");
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " UPDATE superuser SET `Super_Username`=?, `Super_Email`=?, `Super_Fname`=?, `Super_Mname`=?, `Super_Lname`=?,`Super_Password`=?  WHERE `Super_ID`=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, fname);
            preparedStatement.setString(4, mname);
            preparedStatement.setString(5, lname);
            preparedStatement.setString(6, newPass);
            preparedStatement.setInt(7, id);

            rs = preparedStatement.executeUpdate();



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            /*
             * finally block used to close resources
             */
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
        }
    }


    public  void updateFaculty(int id,String fname, String mname, String lname, String username, String email) throws SQLException, ClassNotFoundException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " UPDATE faculty_member SET `Faculty_Username`=?, `Faculty_Email`=?, `Faculty_Fname`=?, `Faculty_Mname`=?, `Faculty_Lname`=? WHERE `Faculty_ID`=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, fname);
            preparedStatement.setString(4, mname);
            preparedStatement.setString(5, lname);
            preparedStatement.setInt(6, id);
            rs = preparedStatement.executeUpdate();



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            /*
             * finally block used to close resources
             */
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
        }
    }

    public  void updateFaculty(int id,String fname, String mname, String lname, String username, String email, String newPass) throws SQLException, ClassNotFoundException {
        System.out.println("got the new password");
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " UPDATE faculty_member SET `Faculty_Username`=?, `Faculty_Email`=?, `Faculty_Fname`=?, `Faculty_Mname`=?, `Faculty_Lname`=?,`Faculty_Password`=? WHERE `Faculty_ID`=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, fname);
            preparedStatement.setString(4, mname);
            preparedStatement.setString(5, lname);
            preparedStatement.setString(6, newPass);
            preparedStatement.setInt(7, id);

            rs = preparedStatement.executeUpdate();



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            /*
             * finally block used to close resources
             */
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
        }
    }


    public  void updateEvaluator(int id,String fname, String mname, String lname, String username) throws SQLException, ClassNotFoundException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = "UPDATE evaluator SET `E_Username`=?, `E_Fname`=?, `E_Mname`=?, `E_Lname`=? WHERE `E_ID`=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, fname);
            preparedStatement.setString(3, mname);
            preparedStatement.setString(4, lname);
            preparedStatement.setInt(5, id);
            rs = preparedStatement.executeUpdate();



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            /*
             * finally block used to close resources
             */
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
        }
    }


    public  void updateSystemSettings(String uname, String cname, String ulogo, String color) throws SQLException, ClassNotFoundException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = null;
            if(ulogo != null) {
                query = "UPDATE university SET Uni_name=?, College_name=?, Color=?, Uni_logo=? LIMIT 1;";
            }else {
                query = "UPDATE university SET `Uni_name`=?, `College_name`=?, Color=? LIMIT 1;";
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, cname);
            preparedStatement.setString(3, color);
            if(ulogo != null)
            preparedStatement.setString(4, ulogo);

            //preparedStatement.setString(4, oldUname);
             preparedStatement.executeUpdate();



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            /*
             * finally block used to close resources
             */
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
        }
    }
}
