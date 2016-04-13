package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;



public class C_AS_Insert {


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
     * addUser method used to create new user
     * @param type is the type of the user
     * @param Uname is the username of the username
     * @param email is the email of the user
     * @param Fname is the first name of the user
     * @param Mname is the middle name of the user
     * @param Lname is the last name of the user
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
     * addCourse method used to add new course
     * @param code is the course code
     * @param name is the course name
     * @param level is the course level
     * @param FK_P_ID is the program ID
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
     * includeCourse method is used to include the course in the term
     * @param FK_C_code is the course code
     * @param FK_T_ID is the term ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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

    /**
     * addCycle method used to add new cycle
     * @return the new Cycle ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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

    /**
     * addSection method used to add new section
     * @param FK_T is the foreign term ID
     * @param FK_F is the foreign faculty member ID
     * @param FK_C is the foreign course ID
     * @return the new section ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

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

    /**
     * addStudent method used to add new student
     * @param Student_Name is the student name
     * @param Student_ID is the student ID
     * @param FK_Section is the foreign section ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void addStudent(String Student_Name, String Student_ID, int FK_Section) throws ClassNotFoundException, SQLException {

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
            preparedStatement.setString(2, Student_ID);
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

    /**
     * addTerm method used to add new term
     * @param name is the term name
     * @param year is the term year
     * @param C_ID is the cycle ID
     * @return the new term ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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
     * addRubric method used to add new rubric
     * @param PI_rubric_description_1 is the performance indicator rubric description 1
     * @param PI_rubric_description_2 is the performance indicator rubric description 2
     * @param PI_rubric_description_3 is the performance indicator rubric description 3
     * @param PI_rubric_description_4 is the performance indicator rubric description 4
     * @return rubric ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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
     * addRubricNames method used to add rubric name
     * @param PI_rubric_name_1 is the performance indicator rubric name 1
     * @param PI_rubric_name_2 is the performance indicator rubric name 2
     * @param PI_rubric_name_3 is the performance indicator rubric name 3
     * @param PI_rubric_name_4 is the performance indicator rubric name 4
     * @param id is the rubric ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

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

    /**
     * addPILink method used to add the performance indicator link
     * @param FK_out is the foreign key outcome
     * @param FK_pi_ID is the foreign key performance indicator ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_R_ID is the foreign key rubric ID
     * @param FK_C_ID is the foreign key cycle ID
     * @param FK_T_ID is the foreign key term ID
     * @param LinkType is the link type (formative/summative)
     * @return the ID of  new performance indicator link
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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
     * addPi method used to  add performance indicator
     * @param name is the name of the performance indicator name
     * @param Threshold is the percentage of threshold
     * @param FK_P_ID is the foreign key program ID
     * @param FK_C_ID is the foreign key cycle ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void addPI(String name, double Threshold, int FK_P_ID, int FK_C_ID) throws ClassNotFoundException, SQLException {


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
            String query = " insert into performance_indicator (PI_name, Threshold, FK_P_ID, FK_C_ID)" + " values (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, Threshold);
            preparedStatement.setInt(3, FK_P_ID);
            preparedStatement.setInt(4, FK_C_ID);

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

    /**
     * adFormF method is to add formative form
     * @param FK_Link_ID is the foreign key link ID
     * @param FK_Section_ID is the foreign key section ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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

    /**
     * adFormF method is to add summative form
     * @param FK_Link_ID is the foreign key link ID
     * @param FK_Section_ID is the foreign key section ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void addFormS(int FK_Link_ID, int FK_Section_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into summative (Sum_submitted, FK_Link_ID, FK_Section_ID)" + " values (?,?,?)";

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

}
