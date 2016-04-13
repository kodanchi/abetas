package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class AS_Select {

    private Connection conn;
    private Statement stmt;
    private ResultSet result;
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
     * login list of strings of the username
     * @param username is the username
     * @return the details list of the user information
     */

    public String[] login(String username) throws ClassNotFoundException, SQLException {

        String userData[] = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "select * FROM users where username = ?;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                userData = new String[]{rs.getString(1), rs.getString(3),rs.getString(4),rs.getString(5)};
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
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<ArrayList<String>> selectAllSubmittedFormsForValidTerm() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> result= new ArrayList<ArrayList<String>>();
        ArrayList<String> rsRow;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Summative_ID As 'ID' ,C_name , PI_name, Faculty_Fname, Faculty_Lname, 'summative' As 'type'  " +
                    "FROM summative, faculty_member, abetasdb.section , term, cycle, link_out_pi , course, performance_indicator " +
                    "where FK_Section_ID=Section_ID and term.current = 1 and Sum_submitted = 1 and FK_F= Faculty_ID and FK_T= T_ID " +
                    "and FK_Cycle_ID = Cycle_ID and FK_Link_ID=Link_ID and PI_Label=FK_pi_ID and course.C_code = link_out_pi.FK_C_ID " +
                    "union all " +
                    "select  Formative_ID ,C_name , PI_name, Faculty_Fname, Faculty_Lname, 'formative' FROM formative, faculty_member, " +
                    "abetasdb.section , term, cycle, link_out_pi , course, performance_indicator where FK_Section_ID=Section_ID and " +
                    "term.current = 1 and F_submitted = 1 and FK_F= Faculty_ID and FK_T= T_ID  and FK_Cycle_ID = Cycle_ID and " +
                    "FK_Link_ID=Link_ID and PI_Label=FK_pi_ID and course.C_code=link_out_pi.FK_C_ID";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                rsRow = new ArrayList<String>();
                rsRow.add(rs.getString(1));
                rsRow.add(rs.getString(2));
                rsRow.add(rs.getString(3));
                rsRow.add(rs.getString(4));
                rsRow.add(rs.getString(5));
                rsRow.add(rs.getString(6));
                result.add(rsRow);
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

            return result;

        }

    }

    /**
     * selectAllUnsubmittedFormsForValidTerm arraylist used to list all contents of university table
     * @return all unsubmitted forms for valid term
     */


    public ArrayList<ArrayList<String>> selectAllUnsubmittedFormsForValidTerm() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> result= new ArrayList<ArrayList<String>>();
        ArrayList<String> rsRow;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Summative_ID As 'ID' ,C_name , PI_name, Faculty_Fname, Faculty_Lname, 'summative' As 'type',Faculty_Email  " +
                    "FROM summative, faculty_member, abetasdb.section , term, cycle, link_out_pi , course, performance_indicator " +
                    "where FK_Section_ID=Section_ID and term.current = 1 and Sum_submitted = 0 and FK_F= Faculty_ID and FK_T= T_ID " +
                    "and FK_Cycle_ID = Cycle_ID and FK_Link_ID=Link_ID and PI_Label=FK_pi_ID and course.C_code = link_out_pi.FK_C_ID " +
                    "union all " +
                    "select  Formative_ID ,C_name , PI_name, Faculty_Fname, Faculty_Lname, 'formative', Faculty_Email FROM formative, faculty_member, " +
                    "abetasdb.section , term, cycle, link_out_pi , course, performance_indicator where FK_Section_ID=Section_ID and " +
                    "term.current = 1 and F_submitted = 0 and FK_F= Faculty_ID and FK_T= T_ID  and FK_Cycle_ID = Cycle_ID and " +
                    "FK_Link_ID=Link_ID and PI_Label=FK_pi_ID and course.C_code=link_out_pi.FK_C_ID";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                rsRow = new ArrayList<String>();
                rsRow.add(rs.getString(1));
                rsRow.add(rs.getString(2));
                rsRow.add(rs.getString(3));
                rsRow.add(rs.getString(4));
                rsRow.add(rs.getString(5));
                rsRow.add(rs.getString(6));
                rsRow.add(rs.getString(7));

                result.add(rsRow);
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

            return result;

        }

    }

    /**
     * selectHeaderData arraylist used to list all contents of university table
     * @return the header data
     * @throws ClassNotFoundException
     */
    public ArrayList<String> selectHeaderData() throws ClassNotFoundException, SQLException {

        ArrayList<String> rowDate = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT  Uni_name, College_name, Uni_logo, color  FROM university LIMIT 1;";

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
                rowDate = new ArrayList<String>();
                rowDate.add(rs.getString(1));
                rowDate.add(rs.getString(2));
                rowDate.add(rs.getString(3));
                rowDate.add(rs.getString(4));

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

            return rowDate;

        }

    }

    /**
     * selectUniversityData arraylist used to list all contents of university table
     * @return the selectUniversityData
     */
    public ArrayList<String> selectUniversityData() throws ClassNotFoundException, SQLException {

        ArrayList<String> rowDate = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT  Uni_name, College_name, Uni_logo  FROM university LIMIT 1;";

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
                rowDate = new ArrayList<String>();
                rowDate.add(rs.getString(1));
                rowDate.add(rs.getString(2));
                rowDate.add(rs.getString(3));

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

            return rowDate;

        }

    }





}
