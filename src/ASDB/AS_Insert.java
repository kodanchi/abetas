package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ibrahim Abuaqel on 1/31/2016.
 */
public class AS_Insert {

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

    public ArrayList<String> addProgramm(String pName, String mission) throws ClassNotFoundException, SQLException {
        //public void addProgramm(String pName, String mission, String sOutcome, String pObj, String outcomeLable, String objectiveLable ) throws ClassNotFoundException, SQLException {

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
                //data.add((id= rsSelect.getInt(1))+"");

                data.add(name = rsSelect.getString(1));
                System.out.println(data.get(0));
                return data;
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
        return data;
    }

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

    public void includeCourse(String FK_C_code, int FK_T_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into term_contains_courses (FK_C_code, FK_T_ID)" + " values (?,?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, FK_C_code);
            preparedStatement.setInt(2, FK_T_ID);

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

    public int addCycle() throws ClassNotFoundException, SQLException {

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

                String query = " insert into cycle (Cycle_ID)" + " values (?)";
                id++;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
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
        return id;
    }


    public int addSection(int FK_T, int FK_F, String FK_C) throws ClassNotFoundException, SQLException {

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
            String query = " insert into abetasdb.section (FK_T, FK_F, FK_C)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T);
            preparedStatement.setInt(2, FK_F);
            preparedStatement.setString(3, FK_C);

            rs = preparedStatement.executeUpdate();


            String querySelect = " SELECT Section_ID FROM abetasdb.section ORDER BY Section_ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                id = rsSelect.getInt(1);
                return id;
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
        return id;
    }
    public void addStudent(String Student_Name, long Student_ID, int FK_Section) throws ClassNotFoundException, SQLException {

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
            String query = " insert into students (Student_Name, Student_ID, FK_Section)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Student_Name);
            preparedStatement.setLong(2, Student_ID);
            preparedStatement.setInt(3, FK_Section);

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

    public int addTerm(String name, String year, int C_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into term (T_name, T_year, FK_Cycle_ID, current)" + " values (?, ?, ?,?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, year);
            preparedStatement.setInt(3, C_ID);
            preparedStatement.setInt(4, 0);
            rs = preparedStatement.executeUpdate();

            String querySelect = " SELECT T_ID FROM term ORDER BY T_ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                id = rsSelect.getInt(1);
                System.out.println("        dddd        "+id);
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

        System.out.println("        ooooodddd        "+id);
        return id;
    }

    public int addRubric(String PI_rubric_description_1, String PI_rubric_description_2, String PI_rubric_description_3, String PI_rubric_description_4) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rsSelect = null;
        int rs = 0;
        int id=0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */

            String query = " insert into pi_rubric (PI_rubric_description_1, PI_rubric_description_2, PI_rubric_description_3, PI_rubric_description_4)" + " values (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, PI_rubric_description_1);
            preparedStatement.setString(2, PI_rubric_description_2);
            preparedStatement.setString(3, PI_rubric_description_3);
            preparedStatement.setString(4, PI_rubric_description_4);
            rs = preparedStatement.executeUpdate();


            ////Need to display the temp password to the screen
            String querySelect = " SELECT PI_rubric_ID FROM pi_rubric ORDER BY PI_rubric_ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                id = rsSelect.getInt(1);
                System.out.println("        dddd        "+id);
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

    public void addRubricNames(String PI_rubric_name_1,String PI_rubric_name_2, String PI_rubric_name_3, String PI_rubric_name_4, int id) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

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

            String query = " UPDATE cycle SET PI_rubric_name_1 = ?, PI_rubric_name_2 = ?, PI_rubric_name_3 = ?, PI_rubric_name_4 = ? WHERE Cycle_ID = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, PI_rubric_name_1);
            preparedStatement.setString(2, PI_rubric_name_2);
            preparedStatement.setString(3, PI_rubric_name_3);
            preparedStatement.setString(4, PI_rubric_name_4);
            preparedStatement.setInt(5, id);

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

    public int addPILink(int FK_out, int FK_pi_ID, int FK_P_ID, int FK_R_ID, String FK_C_ID, int FK_T_ID, String LinkType) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        ResultSet rsSelect = null;
        int id=0;
        int rs1 = 0;
        int rs2 = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */

            ////Need to display the temp password to the screen


            String query1 = " insert into link_out_pi (FK_out, FK_pi_ID, FK_P_ID, FK_R_ID, FK_C_ID, FK_T_ID, LinkType)" + " values (?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, FK_out);
            preparedStatement.setInt(2, FK_pi_ID);
            preparedStatement.setInt(3, FK_P_ID);
            preparedStatement.setInt(4, FK_R_ID);
            preparedStatement.setString(5, FK_C_ID);
            preparedStatement.setInt(6, FK_T_ID);
            preparedStatement.setString(7, LinkType);

            rs2 = preparedStatement.executeUpdate();


            String querySelect = " SELECT Link_ID FROM link_out_pi ORDER BY Link_ID DESC LIMIT 1";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                id = rsSelect.getInt(1);
                System.out.println("        dddd        "+id);
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

    public void addPI(String name, int FK_P_ID, int FK_C_ID ) throws ClassNotFoundException, SQLException {


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
            String query = " insert into performance_indicator (PI_name, FK_P_ID, FK_C_ID)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, FK_P_ID);
            preparedStatement.setInt(3, FK_C_ID);

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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        public void addPIold(String name, String label, int type, int threshold) throws ClassNotFoundException, SQLException {


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

    public void addFormF(int FK_Link_ID, int FK_Section_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into formative (F_submitted, FK_Link_ID,FK_Section_ID)" + " values (?,?,?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, FK_Link_ID);
            preparedStatement.setInt(3, FK_Section_ID);

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

    public void addFormS(int FK_Link_ID, int Sum_threshold, int FK_Section_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into summative (Sum_submitted, FK_Link_ID, Sum_threshold, FK_Section_ID)" + " values (?,?,?,?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, FK_Link_ID);
            preparedStatement.setInt(3, Sum_threshold);
            preparedStatement.setInt(4, FK_Section_ID);

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

    public void audit(String userName, String action, String time) throws ClassNotFoundException, SQLException {

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
            String query = " insert into auditing (userName, action, time) values (?,?,?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, action);
            preparedStatement.setString(3, time);

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

}
