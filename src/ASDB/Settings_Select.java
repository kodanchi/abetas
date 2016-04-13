package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;


public class Settings_Select {

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
     * select program from database and return array list of a programs details.
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<ArrayList<String>> selectProgram() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "select * FROM program";



            connection = dataSource.getConnection();


            preparedStatement = connection.prepareStatement(query);

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(1));
                RowDate.add(rs.getString(2));

                RsArr.add(RowDate);
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
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

            return RsArr;

        }

    }

    /**
     * select superuser from database and return array list of a superuser details.
     * @param Super_ID
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectSuperuserForSettings(int Super_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> rowDate = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM superuser where Super_ID = ?;";



            connection = dataSource.getConnection();


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Super_ID);

            rs = preparedStatement.executeQuery();


            while (rs.next()){
                rowDate = new ArrayList<String>();
                rowDate.add(rs.getString(1));
                rowDate.add(rs.getString(5));
                rowDate.add(rs.getString(6));
                rowDate.add(rs.getString(7));
                rowDate.add(rs.getString(2));
                rowDate.add(rs.getString(4));
                rowDate.add(rs.getString(8));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
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

            return rowDate;

        }

    }

    /**
     * select faculty member from database and return array list of a faculty member details.
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectFaculty(String username) throws ClassNotFoundException, SQLException {

        ArrayList<String> rowDate = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM faculty_member where Faculty_Username = '"+username+"';";



            connection = dataSource.getConnection();


            preparedStatement = connection.prepareStatement(query);

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                rowDate = new ArrayList<String>();
                rowDate.add(rs.getString(1));
                rowDate.add(rs.getString(5));
                rowDate.add(rs.getString(6));
                rowDate.add(rs.getString(7));
                rowDate.add(rs.getString(2));
                rowDate.add(rs.getString(4));
                rowDate.add(rs.getString(3));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
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

            return rowDate;

        }

    }

    /**
     * select username from database and return array list of a username details.
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String[] login(String username) throws ClassNotFoundException, SQLException {

        String userData[] = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "select * FROM users where username= ?;";


            connection = dataSource.getConnection();


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                userData = new String[]{rs.getString(1), rs.getString(3),rs.getString(4)};
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

            return userData;

        }

    }

    /**
     * select university from database and return array list of a university details.
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectSystemSettings() throws ClassNotFoundException, SQLException {

        ArrayList<String> rowDate = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM university;";



            connection = dataSource.getConnection();


            preparedStatement = connection.prepareStatement(query);

            rs = preparedStatement.executeQuery();


            while (rs.next()){
                rowDate = new ArrayList<String>();
                rowDate.add(rs.getString(1));
                rowDate.add(rs.getString(2));
                rowDate.add(rs.getString(3));
                rowDate.add(rs.getString(4));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
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

            return rowDate;

        }

    }

    /**
     * select email from database and replace the old email with new one.
     * @param email
     * @param olduEmail
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean selectEmailIfExist(String email, String olduEmail) throws ClassNotFoundException, SQLException {

        boolean isExist = false;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT EXISTS(SELECT * FROM users where email = '"+email+"' AND email NOT IN ( SELECT email FROM users where email = '"+olduEmail+"'));";



            connection = dataSource.getConnection();


            preparedStatement = connection.prepareStatement(query);

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                if(rs.getInt(1) == 1){
                    isExist = true;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
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

            return isExist;

        }

    }





}
