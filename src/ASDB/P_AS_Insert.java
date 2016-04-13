package ASDB;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;


public class P_AS_Insert {


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
     * addUser method used to add the user
     * @param type is the user type
     * @param Uname is the username
     * @param email is the email
     * @param Fname is the first name
     * @param Mname is the middle name
     * @param Lname is the last name
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void addUser(int type, String Uname, String email, String Fname, String Mname, String Lname) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        SessionIdentifierGenerator randomPassword = new SessionIdentifierGenerator();
        String rn = randomPassword.nextSessionId().substring(0, 8);

        int rs = 0;
        if (type == 0) {
            try {

            /*
             *  Get connection from the DataSource
             */

                connection = dataSource.getConnection();

            /*
             * Execute the query
             */
                String query = " insert into superuser (Super_Username, Super_Email, Super_Fname, Super_Mname, Super_Lname, Adm_ID, Super_Password)" + " values (?, ?, ?, ?, ?, ?, ?)";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, Uname);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, Fname);
                preparedStatement.setString(4, Mname);
                preparedStatement.setString(5, Lname);
                preparedStatement.setInt(6, 0);
                preparedStatement.setString(7, rn);
                rs = preparedStatement.executeUpdate();








                ////Need to display the temp password to the screen








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
        }else if (type==1){
            try {

            /*
             *  Get connection from the DataSource
             */

                connection = dataSource.getConnection();

            /*
             * Execute the query
             */
                String query = " insert into faculty_member (Faculty_Username, Faculty_Email, Faculty_Fname, Faculty_Mname, Faculty_Lname, Faclty_Password)" + " values (?, ?, ?, ?, ?, ?)";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, Uname);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, Fname);
                preparedStatement.setString(4, Mname);
                preparedStatement.setString(5, Lname);
                preparedStatement.setString(6, rn);
                rs = preparedStatement.executeUpdate();








                ////Need to display the temp password to the screen








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
        }else if(type==2){
            try {

            /*
             *  Get connection from the DataSource
             */

                connection = dataSource.getConnection();

            /*
             * Execute the query
             */
                String query = " insert into evaluator (E_Username, E_Fname, E_Mname, E_Lname, E_Password)" + " values (?, ?, ?, ?, ?)";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, Uname);
                preparedStatement.setString(2, Fname);
                preparedStatement.setString(3, Mname);
                preparedStatement.setString(4, Lname);
                preparedStatement.setString(5, rn);
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

    /**
     * addProgram arraylist used to list all contents of the program table
     * @param pName is the program name
     * @param mission is the mission of the program
     * @return arraylist of arraylist of strings which containts the program
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> addProgramm(String pName, String mission) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        String name = "";
        int id=0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " insert into program (P_name, P_mission)" + " values (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pName);
            preparedStatement.setString(2, mission);

            rs = preparedStatement.executeUpdate();

            String querySelect = " SELECT P_name FROM program ORDER BY P_ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){

                data.add(name = rsSelect.getString(1));
                return data;
            }



        } catch (MySQLIntegrityConstraintViolationException e) {
            e.fillInStackTrace();
            throw e;
        }catch (Exception e) {
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
        return data;
    }

    /**
     * addObject method used to add the objective
     * @param Objective is the objective of the course
     * @param FK_P_ID is the foreign key of program ID
     * @throws ClassNotFoundException
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public void addObject(String Objective, int FK_P_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into p_objective (Objective, FK_P_ID)" + " values (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Objective);
            preparedStatement.setInt(2, FK_P_ID);
            rs = preparedStatement.executeUpdate();


            ////Need to display the temp password to the screen


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

    /**
     * addoutcome method is used to add outcomes
     * @param Student_outcome is the student outcomes saved as string
     * @param FK_P_ID is the foreign key of program ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void addOutcome(String Student_outcome, int FK_P_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into p_student_outcome (Student_outcome, FK_P_ID)" + " values (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Student_outcome);
            preparedStatement.setInt(2, FK_P_ID);
            rs = preparedStatement.executeUpdate();


            ////Need to display the temp password to the screen


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

    /**
     * addLinkObj_out method is used to add link between the objective and student outcomes
     * @param Outcome_label is the outcome ID
     * @param Objective_label is the objective ID
     * @param FK_P_ID is the foreign key program ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void addLinkObj_Out(int Outcome_label, int Objective_label, int FK_P_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into link_out_obj (FK_out, FK_obj, FK_P_ID)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Outcome_label);
            preparedStatement.setInt(2, Objective_label);
            preparedStatement.setInt(3, FK_P_ID);
            rs = preparedStatement.executeUpdate();


            ////Need to display the temp password to the screen


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

    /**
     * addCourse method used to add course
     * @param code is the course code
     * @param name is the course name
     * @param level is the course level
     * @param include is the course include in the term or not
     * @param FK_P_ID is the foreign key program ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void addCourse(String code, String name, int level,int include, int FK_P_ID) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;
        int rs2 = 0;
        ResultSet rsSelect = null;
        String Selected_Code;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */

            String query = " insert into course (C_code, C_name, C_level, C_include)" + " values (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, level);
            preparedStatement.setInt(4, include);

            rs = preparedStatement.executeUpdate();

            /*String querySelect = " SELECT C_code FROM course ORDER BY C_code DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

                Selected_Code = rsSelect.getString(1);*/

            String query2 = " insert into program_has_course (FK_program_ID, FK_course_code)" + " values (?, ?)";

            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt(1, FK_P_ID);
            preparedStatement.setString(2, code);

            rs2 = preparedStatement.executeUpdate();

            ////Need to display the temp password to the screen


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

    /**
     * includeCourse method used to include the course with term
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void includeCourse(int include) throws ClassNotFoundException, SQLException {

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
            String query = " insert into course (C_include)" + " values (?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, include);
            rs = preparedStatement.executeUpdate();


            ////Need to display the temp password to the screen


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

    /**
     * addTerm method is used to add the term
     * @param name is the name of the term
     * @param year is the year of the term
     * @param C_ID is the cycle ID
     * @return the term ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public int addTerm(String name, String year, String C_ID) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;
        ResultSet rsSelect = null;
        int id=0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " insert into term (T_name, T_year, FK_Cycle_ID)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, year);
            preparedStatement.setString(3, C_ID);
            rs = preparedStatement.executeUpdate();

            String querySelect = " SELECT T_ID FROM term ORDER BY T_ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                id = rsSelect.getInt(1);
                return id;
            }

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

        return id;
    }

    /**
     * addPI method is used to add new performance indicator
     * @param name is the name of performance indicator
     * @param FK_P_ID is the foreign key program ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void addPI(String name, int FK_P_ID) throws ClassNotFoundException, SQLException {


        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int piid=0;
        ResultSet rsSelect = null;
        int rs = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " insert into performance_indicator (PI_name, FK_P_ID)" + " values (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, FK_P_ID);

            rs = preparedStatement.executeUpdate();


            ////Need to display the temp password to the screen


            /*String querySelect = " SELECT PI_ID FROM performance_indicator ORDER BY PI_ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                piid = rsSelect.getInt(1);
            }*/

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


}
