package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;


public class Settings_Update {

    DataSource dataSource = null;

    public void connect() throws ClassNotFoundException, SQLException {

        try
        {


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

    /**
     * update the superuser information without password
     * @param id
     * @param fname
     * @param mname
     * @param lname
     * @param username
     * @param email
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public  void updateSuperuser(int id,String fname, String mname, String lname, String username, String email) throws SQLException, ClassNotFoundException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {



            connection = dataSource.getConnection();


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
     * update the superuser information with password
     * @param id
     * @param fname
     * @param mname
     * @param lname
     * @param username
     * @param email
     * @param newPass
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public  void updateSuperuser(int id,String fname, String mname, String lname, String username, String email,String newPass) throws SQLException, ClassNotFoundException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {



            connection = dataSource.getConnection();


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
     * update the faculty member information without password
     * @param id
     * @param fname
     * @param mname
     * @param lname
     * @param username
     * @param email
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public  void updateFaculty(int id,String fname, String mname, String lname, String username, String email) throws SQLException, ClassNotFoundException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {



            connection = dataSource.getConnection();


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
     * update the superuser information with password
     * @param id
     * @param fname
     * @param mname
     * @param lname
     * @param username
     * @param email
     * @param newPass
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public  void updateFaculty(int id,String fname, String mname, String lname, String username, String email, String newPass) throws SQLException, ClassNotFoundException {
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {



            connection = dataSource.getConnection();


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
     * update system information (university name and logo and college name).
     * @param uname
     * @param cname
     * @param ulogo
     * @param color
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public  void updateSystemSettings(String uname, String cname, String ulogo, String color) throws SQLException, ClassNotFoundException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;

        try {



            connection = dataSource.getConnection();


            String query = null;
                query = "UPDATE university SET Uni_name=?, College_name=?, Color=?, Uni_logo=? LIMIT 1;";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, cname);
            preparedStatement.setString(3, color);
            preparedStatement.setString(4, ulogo);

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
