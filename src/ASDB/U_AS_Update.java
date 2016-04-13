package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;


public class U_AS_Update {


    DataSource dataSource = null;
    /**
     * connect method used to connect to the databse as pool connections
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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
    /**
     *
     * @param rs do the query to the database and close the connection after that
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void closRS(ResultSet rs) throws SQLException {
        rs.close();
    }

    /**
     * updateSuperuser method used to update the superuser account
     * @param id is the uperuser ID
     * @param fname is the first name of the superuser
     * @param mname is the middle name of the superuser
     * @param lname is the last name of the superuser
     * @param username is the username of the superuser
     * @param email is the email of the superuser
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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

    /**
     * updateSuperuserPassword method used to update the superuser password
     * @param password is the password of superuser
     * @param email is the email of superuser
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public  void updateSuperuserPassword(String password, String email) throws SQLException, ClassNotFoundException {

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
            String query = " UPDATE superuser SET Super_Password = ? WHERE Super_Email=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);

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

    /**
     *updateFaculty method used to update the faculty member account
     * @param id is the faculty member ID
     * @param fname is the first name of the faculty member
     * @param mname is the middle name of the faculty member
     * @param lname is the last name of the faculty member
     * @param username is the username of the faculty member
     * @param email is the email of the faculty member
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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

    /**
     * updateFacultyPassword method used to update the faculty member
     * @param password is the password of faculty member
     * @param email is the email of faculty member
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public  void updateFacultyPassword(String password, String email) throws SQLException, ClassNotFoundException {

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
            String query = " UPDATE faculty_member SET Faculty_Password = ? WHERE Faculty_Email = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);

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

    /**
     *
     * @param id is the faculty member ID
     * @param fname is the first name of the evaluator
     * @param mname is the middle name of the evaluator
     * @param lname is the last name of the evaluator
     * @param username is the username of the evaluator
     * @param program is the program that the evaluator is responsible for
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public  void updateEvaluator(int id,String fname, String mname, String lname, String username, String program) throws SQLException, ClassNotFoundException {

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
            String query = "UPDATE evaluator SET `E_Username`=?, `E_Fname`=?, `E_Mname`=?, `E_Lname`=?, `E_program`= ? WHERE `E_ID`=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, fname);
            preparedStatement.setString(3, mname);
            preparedStatement.setString(4, lname);
            preparedStatement.setString(5, program);
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
}
