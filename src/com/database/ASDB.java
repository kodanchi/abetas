package com.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ibrahim Abuaqel on 1/24/2016.
 * source : http://tomcat.apache.org/tomcat-6.0-doc/jndi-datasource-examples-howto.html#MySQL_DBCP_Example
 */
public class ASDB {
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

    public ArrayList<ArrayList<String>> selectUsers() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> rsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> rowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "select * FROM superuser";

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
                rowDate.add(rs.getString(5));
                rowDate.add(rs.getString(6));
                rowDate.add(rs.getString(7));
                rowDate.add(rs.getString(2));
                rowDate.add(rs.getString(4));
                rowDate.add(rs.getString(8));
                rsArr.add(rowDate);
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

             return rsArr;

        }

    }

    public void initialization(String Uname, String Cname, String logo, String Fname, String Mname, String Lname, String Username, String password, String email) throws ClassNotFoundException, SQLException {

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
            String query = " insert into university (Uni_name, College_name, Uni_logo)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, Uname);
            preparedStatement.setString (2, Cname);
            preparedStatement.setString (3, logo);

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

        connect();

        connection = null;
        preparedStatement = null;

        rs = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " insert into superuser (Super_Username, Super_Password, Super_Email, Super_Fname, Super_Mname, Super_Lname, Adm_ID)" + " values (?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, Username);
            preparedStatement.setString (2, password);
            preparedStatement.setString (3, email);
            preparedStatement.setString (4, Fname);
            preparedStatement.setString (5, Mname);
            preparedStatement.setString (6, Lname);
            preparedStatement.setInt (7, 1);
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

    public void addProgramm(String pName, String mission) throws ClassNotFoundException, SQLException {
        //public void addProgramm(String pName, String mission, String sOutcome, String pObj, String outcomeLable, String objectiveLable ) throws ClassNotFoundException, SQLException {

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
            String query = " insert into superuser (P_name, P_mission)" + " values (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pName);
            preparedStatement.setString(2, mission);

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

    public void addObject(String Objective_label, String Objective, int FK_P_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into p_objective (Objective_label, Objective, FK_P_ID)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Objective_label);
            preparedStatement.setString(2, Objective);
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

    public void addOutcome(String Outcome_label, String Student_outcome, int FK_P_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into p_student_outcome (Outcome_label, Student_outcome, FK_P_ID)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Outcome_label);
            preparedStatement.setString(2, Student_outcome);
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

    public void addLinkObj_Out(String Outcome_label, String Objective_label) throws ClassNotFoundException, SQLException {

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
            String query = " insert into link_out_obj (FK_outcome, FK_objective)" + " values (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Outcome_label);
            preparedStatement.setString(2, Objective_label);
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

    public void addCourse(String code, String level, String name, int include, int FK_T_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into course (C_code, C_level, C_name, C_include, FK_T_ID)" + " values (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, level);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, include);
            preparedStatement.setInt(5, FK_T_ID);
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

    public void addCycle(int C_ID) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rsSelect = null;
        int id = 0;
        int rs = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */

            String querySelect = " SELECT Cycle_ID FROM cycle ORDER BY Cycle_ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                id = rsSelect.getInt(1);
            }

            if (id!=0) {
                String query = " insert into cycle (Cycle_ID)" + " values (?)";
                id++;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                rs = preparedStatement.executeUpdate();
            }

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

    public void addStudent(int SID, String Fname, String Mname, String Lname) throws ClassNotFoundException, SQLException {

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
            String query = " insert into students (Student_ID, Student_Fname, Student_Mname, Student_Lname)" + " values (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, SID);
            preparedStatement.setString(2, Fname);
            preparedStatement.setString(3, Mname);
            preparedStatement.setString(4, Lname);
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

    public void addTerm(String name, String year, String C_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into term (T_name, T_year, FK_Cycle_ID)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, year);
            preparedStatement.setString(3, C_ID);
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

    public void addRubric(String rubric_name, String rubric_description) throws ClassNotFoundException, SQLException {

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
            String query = " insert into pi_rubric (PI_rubric_name, PI_rubric_description)" + " values (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rubric_name);
            preparedStatement.setString(2, rubric_description);
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

    public void addPI(String name, String label, int type, int threshold) throws ClassNotFoundException, SQLException {


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
                String query = " insert into performance_indicator (PI_name, PI_label)" + " values (?, ?)";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, label);
                rs = preparedStatement.executeUpdate();


                ////Need to display the temp password to the screen


                String querySelect = " SELECT PI_ID FROM performance_indicator ORDER BY PI_ID DESC LIMIT 1";

                preparedStatement = connection.prepareStatement(querySelect);

                rsSelect = preparedStatement.executeQuery();

                if (rsSelect.next()){
                    piid = rsSelect.getInt(1);
                }

                if (piid!=0) {
                    if (type==0){
                        int rsInsertS = 0;
                        ResultSet rsSelectS = null;
                        int sid=0;
                        String queryInsertS = " insert into summative (Sum_threshold)" + " values (?)";

                        preparedStatement = connection.prepareStatement(queryInsertS);
                        preparedStatement.setInt(1, threshold);
                        rsInsertS = preparedStatement.executeUpdate();


                        String querySelectS = " SELECT Summative_ID FROM summative ORDER BY Summative_ID DESC LIMIT 1";

                        preparedStatement = connection.prepareStatement(querySelectS);

                        rsSelectS = preparedStatement.executeQuery();

                        if (rsSelectS.next()){
                            sid = rsSelect.getInt(1);
                        }

                        int rsSummative = 0;
                        String querySummative = " insert into pi_type_summative (FK_Summative_ID, FK_PI_ID)" + " values (?, ?)";

                        preparedStatement = connection.prepareStatement(querySummative);
                        preparedStatement.setInt(1, piid);
                        preparedStatement.setInt(2, sid);
                        rs = preparedStatement.executeUpdate();

                    }else if (type==1){
                        int rsFormative = 0;
                        ResultSet rsSelectF = null;
                        int fid=0;

                        String queryInsertF = " insert into formative (F_written_rubic)" + " values (?)";

                        preparedStatement = connection.prepareStatement(queryInsertF);
                        preparedStatement.setString(1, "");
                        rsFormative = preparedStatement.executeUpdate();


                        String querySelectF = " SELECT Formative_ID FROM formative ORDER BY Formative_ID DESC LIMIT 1";

                        preparedStatement = connection.prepareStatement(querySelectF);

                        rsSelectF = preparedStatement.executeQuery();

                        if (rsSelectF.next()){
                            fid = rsSelect.getInt(1);
                        }

                        String queryFormative = " insert into pi_type_formative (FK_Formative_ID, FK_PI_ID)" + " values (?, ?)";

                        preparedStatement = connection.prepareStatement(queryFormative);
                        preparedStatement.setInt(1, piid);
                        preparedStatement.setInt(2, fid);
                        rs = preparedStatement.executeUpdate();
                    }//else display a message to the user that type is not set.
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
    }

    public void link_pi_course(int PI_ID, String C_code, String relation) throws ClassNotFoundException, SQLException {

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
            String query = " insert into pi_measure_course (FK_PI_ID, FK_C_code, Relation)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, PI_ID);
            preparedStatement.setString(2, C_code);
            preparedStatement.setString(3, relation);
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

    public void delete(String sql) throws ClassNotFoundException, SQLException {

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
            String query = "delete from university where Uni_name = ?";
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString(1, name);

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


    public ArrayList<ArrayList<String>> selectProgram() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "select * FROM program";

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
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(2));

                RsArr.add(RowDate);
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

            return RsArr;

        }

    }






    public ArrayList<ArrayList<String>> selectObjective() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT P_name, Objective FROM abetasdb.program, abetasdb.p_objective;";

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
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(1));
                RowDate.add(rs.getString(2));

                RsArr.add(RowDate);
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

            return RsArr;

        }

    }



    public ArrayList<ArrayList<String>> selectStudentOutcomeWithObjectives() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM link_out_obj;";

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
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(1));
                RowDate.add(rs.getString(2));

                RsArr.add(RowDate);
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

            return RsArr;

        }

    }




    public ArrayList<ArrayList<String>> selectStudentOutcomes() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM p_student_outcome;";

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
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(2));

                RsArr.add(RowDate);
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

            return RsArr;

        }

    }



    public ArrayList<ArrayList<String>> selectCourses() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM course;";

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
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(3));
                RowDate.add(rs.getString(1));
                RowDate.add(rs.getString(2));


                RsArr.add(RowDate);
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

            return RsArr;

        }

    }


    public ArrayList<ArrayList<String>> selectCycle() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM cycle;";

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
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(1));


                RsArr.add(RowDate);
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

            return RsArr;

        }

    }



    public ArrayList<ArrayList<String>> selectStudents() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM students;";

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
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(3));
                RowDate.add(rs.getString(4));
                RowDate.add(rs.getString(5));
                RowDate.add(rs.getString(6));


                RsArr.add(RowDate);
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

            return RsArr;

        }

    }


    public ArrayList<ArrayList<String>> selectTerm() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT  C_name, C_code, C_level ,Group_ID , C_include  FROM faculty_member_teach_course, course;";

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
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(1));
                RowDate.add(rs.getString(2));
                RowDate.add(rs.getString(3));
                RowDate.add(rs.getString(4));
                RowDate.add(rs.getString(5));


                RsArr.add(RowDate);
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

            return RsArr;

        }

    }

}
