package FDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class F_Select {

    DataSource dataSource = null;

    /**
     * Function that provides database connection using connection pool for multi access usage.
     * @throws ClassNotFoundException
     * @throws SQLException
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
     * Close an open connection to database.
     * @param rs
     * @throws SQLException
     */
    public void closRS(ResultSet rs) throws SQLException {
        rs.close();
    }

    /**
     * Connect to the database and perform select sql to select all program data.
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

    /**
     * Connect to the database and perform select sql to select S_ID, Student_Name and Student_ID using  section ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<ArrayList<String>>  selectStudents(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT S_ID, Student_Name, Student_ID FROM students WHERE FK_Section = ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(1));
                RowDate.add(rs.getString(2));
                RowDate.add(rs.getString(3));



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


    /**
     * Connect to the database and perform select sql to select student_rubric, Student_Name and Student_ID using FK_S_ID and FK_Summative_ID.
     * @param FK_S_ID
     * @param FK_Summative_ID
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String  selectStudentRubric(int FK_S_ID, int FK_Summative_ID) throws ClassNotFoundException, SQLException {

        String data = null;
        //ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT student_rubric FROM summative_rubric WHERE FK_S_ID = ? AND FK_Summative_ID = ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_S_ID);
            preparedStatement.setInt(2, FK_Summative_ID);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                //RowDate = new ArrayList<String>();
                data = rs.getString(1);

                //RsArr.add(RowDate);
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

            return data;

        }

    }

    /**
     * Connect to the database and perform select sql to select PI_rubric_description_1, PI_rubric_description_2, PI_rubric_description_3 and PI_rubric_description_4 using PI_rubric_ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectRubrics(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT PI_rubric_description_1, PI_rubric_description_2, PI_rubric_description_3, " +
                    "PI_rubric_description_4 FROM pi_rubric" +
                    " WHERE PI_rubric_ID = ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
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

            return data;

        }

    }

    /**
     * Connect to the database and perform select sql to select PI_rubric_name_1, PI_rubric_name_2, PI_rubric_name_3 and PI_rubric_name_4 using Formative_ID.
     * @param Formative_ID
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectRubricsFormativeNames(int Formative_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "select PI_rubric_name_1, PI_rubric_name_2, PI_rubric_name_3, PI_rubric_name_4 \n" +
                    "from abetasdb.formative, link_out_pi, term, cycle\n" +
                    "where Formative_ID = ? \n" +
                    "and FK_Link_ID = Link_ID\n" +
                    "and FK_T_ID = T_ID\n" +
                    "and FK_Cycle_ID = Cycle_ID;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Formative_ID);

            rs = preparedStatement.executeQuery();

            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
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

            return data;

        }

    }

    /**
     * Connect to the database and perform select sql to select PI_rubric_name_1, PI_rubric_name_2, PI_rubric_name_3 and PI_rubric_name_4 using Summative_ID.
     * @param Summative_ID
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectRubricsSummativeNames(int Summative_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "select PI_rubric_name_1, PI_rubric_name_2, PI_rubric_name_3, PI_rubric_name_4 \n" +
                    "from abetasdb.summative, link_out_pi, term, cycle\n" +
                    "where Summative_ID = ? \n" +
                    "and FK_Link_ID = Link_ID\n" +
                    "and FK_T_ID = T_ID\n" +
                    "and FK_Cycle_ID = Cycle_ID;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Summative_ID);

            rs = preparedStatement.executeQuery();

            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
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

            return data;

        }

    }

    /**
     * Connect to the database and perform select sql to select course name using course code.
     * @param code
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String selectCourseName(String code) throws ClassNotFoundException, SQLException {

        String name = "";
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT C_name FROM course WHERE C_code = ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);

            rs = preparedStatement.executeQuery();

            int i=-1;
            while (rs.next()){
                name=rs.getString(1);
                return name;

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

            return name;

        }

    }

    /**
     * Connect to the database and perform select sql to select all of the faculty member information using Faculty ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectFaculty(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> rowDate = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM faculty_member where Faculty_ID= ?;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                rowDate = new ArrayList<String>();
                rowDate.add(rs.getString(1));
                rowDate.add(rs.getString(5));
                rowDate.add(rs.getString(6));
                rowDate.add(rs.getString(7));
                rowDate.add(rs.getString(2));
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
     * Connect to the database and perform select sql to select program name using program ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String selectProgramName(int id) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        String name="";
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String querySelect = " SELECT P_name FROM program where P_ID = ?";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setInt (1, id);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((id= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                name= rsSelect.getString(1);
                return name;
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
        return name;
    }

    /**
     * Connect to the database and perform select sql to select link ID of form Formative using Formative_ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String selectLinkIDOfFormF(int id) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        String name="";
        try {

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            String querySelect = " SELECT FK_Link_ID FROM formative where Formative_ID = ?";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setInt (1, id);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((id= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                name= rsSelect.getString(1);
                return name;
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
        return name;
    }

    /**
     * Connect to the database and perform select sql to select Link ID and Section of Summative form using Summative ID and Section_ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectLinkIDAndSectionIDOfFormS(int id) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        String name="";
        try {

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            String querySelect = " SELECT FK_Link_ID, FK_Section_ID, Sum_evidence, Sum_submitted, FK_F, Sum_date FROM summative, abetasdb.section " +
                    "where Summative_ID = ?  AND FK_Section_ID = Section_ID";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setInt (1, id);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                data.add(rsSelect.getString(1));
                data.add(rsSelect.getString(2));
                data.add(rsSelect.getString(3));
                data.add(String.valueOf(rsSelect.getInt(4)));
                data.add(String.valueOf(rsSelect.getInt(5)));
                data.add(rsSelect.getString(6));

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

    /**
     * Connect to the database and perform select sql to select performance indicator link values using Link_ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectPILinksValuse(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT FK_out, FK_pi_ID, FK_C_ID, LinkType, FK_R_ID, FK_P_ID, FK_T_ID From abetasdb.link_out_pi WHERE Link_ID = ?;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                data.add(rs.getString(6));
                data.add(rs.getString(7));
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

            return data;

        }

    }

    /**
     * Connect to the database and perform select sql to select Outcome label and outcome using Outcome_label.
     * @param oid
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectOutForLinkSingle(int oid) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Outcome_label, Student_outcome FROM abetasdb.p_student_outcome where Outcome_label = ? ;";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, oid);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1)+": "+rs.getString(2));
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

            return data;

        }

    }

    /**
     * Connect to the database and perform select sql to select performance indicator label and name to be used in linking using performance indicator label.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectPIForLinkSingle(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT PI_Label, PI_name FROM abetasdb.performance_indicator where PI_Label = ? ;";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1)+": "+rs.getString(2));
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

            return data;

        }

    }

    /**
     * Connect to the database and perform select sql to select course level using course code.
     * @param code
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String selectCourseLevel(String code) throws ClassNotFoundException, SQLException {

        String name = "";
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT C_level FROM course WHERE C_code = ? ;";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                name=rs.getString(1);
                return name;

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

            return name;

        }

    }


    public String selectTermName(int id) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        String name="";
        try {

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            String querySelect = " SELECT T_name FROM term where T_ID = ?";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setInt (1, id);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((id= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                name= rsSelect.getString(1);
                return name;
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
        return name;
    }


    /**
     * Connect to the database and perform select sql to select year of term using term ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String selectTermYear(int id) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        String name="";
        try {

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            String querySelect = " SELECT T_year FROM term where T_ID = ?";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setInt (1, id);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((id= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                name= rsSelect.getString(1);
                return name;
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
        return name;
    }


    /**
     * Connect to the database and perform select sql to select Faculty_ID, Faculty_First_name, Faculty_Middle_name and Faculty_Last_name using Faculty_ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String selectFacultyForForm(int id) throws ClassNotFoundException, SQLException {

        String name="";
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Faculty_ID, Faculty_Fname, Faculty_Mname, Faculty_Lname FROM abetasdb.faculty_member " +
                    "WHERE Faculty_ID = ?;";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                name=(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
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

            return name;

        }

    }

    /**
     * Connect to the database and perform select sql to select Formative_ID, course name and performance indicator using faculty member ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<ArrayList<String>> selectFacultyUnsubmittedFForms(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> result= new ArrayList<ArrayList<String>>();
        ArrayList<String> rsRow;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Formative_ID ,C_name , PI_name  \n" +
                    "FROM formative, section , term, cycle, link_out_pi , course, performance_indicator\n" +
                    "where FK_Section_ID=Section_ID and term.current = 1 and F_submitted = 0 and FK_T= T_ID and FK_F= ? and FK_Cycle_ID = Cycle_ID and" +
                    " FK_Link_ID=Link_ID and PI_Label=FK_pi_ID and course.C_code=link_out_pi.FK_C_ID;";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                rsRow = new ArrayList<String>();
                rsRow.add(rs.getString(1));
                rsRow.add(rs.getString(2));
                rsRow.add(rs.getString(3));
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
     * Connect to the database and perform select sql to select Formative_ID, course name and performance indicator using faculty member ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<ArrayList<String>> selectFacultySubmittedFForms(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> result= new ArrayList<ArrayList<String>>();
        ArrayList<String> rsRow;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Formative_ID ,C_name , PI_name  \n" +
                    "FROM formative, section , term, cycle, link_out_pi , course, performance_indicator\n" +
                    "where FK_Section_ID=Section_ID and term.current = 1 and F_submitted = 1 and FK_T= T_ID and FK_F= ? and FK_Cycle_ID = Cycle_ID and" +
                    " FK_Link_ID=Link_ID and PI_Label=FK_pi_ID and course.C_code=link_out_pi.FK_C_ID;";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                rsRow = new ArrayList<String>();
                rsRow.add(rs.getString(1));
                rsRow.add(rs.getString(2));
                rsRow.add(rs.getString(3));
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
     * Connect to the database and perform select sql to select Summative_ID, course name and performance indicator using faculty member ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<ArrayList<String>> selectFacultyUnsubmittedSForms(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> result= new ArrayList<ArrayList<String>>();
        ArrayList<String> rsRow;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Summative_ID ,C_name , PI_name FROM summative, section , term, cycle, link_out_pi , course, performance_indicator " +
                    "where FK_Section_ID=Section_ID and term.current = 1 and Sum_submitted = 0 and FK_T= T_ID and FK_F = ? and FK_Cycle_ID = Cycle_ID and" +
                    " FK_Link_ID=Link_ID and PI_Label=FK_pi_ID and course.C_code = link_out_pi.FK_C_ID; ";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                rsRow = new ArrayList<String>();
                rsRow.add(rs.getString(1));
                rsRow.add(rs.getString(2));
                rsRow.add(rs.getString(3));
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
     * Connect to the database and perform select sql to select Summative_ID, course name and performance indicator using faculty member ID.
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<ArrayList<String>> selectFacultySubmittedSForms(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> result= new ArrayList<ArrayList<String>>();
        ArrayList<String> rsRow;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Summative_ID ,C_name , PI_name FROM summative, abetasdb.section , term, cycle, link_out_pi , course, performance_indicator " +
                    "where FK_Section_ID=Section_ID and term.current = 1 and Sum_submitted = 1 and FK_T= T_ID and FK_F = ? and FK_Cycle_ID = Cycle_ID and" +
                    " FK_Link_ID=Link_ID and PI_Label=FK_pi_ID and course.C_code = link_out_pi.FK_C_ID; ";

        /*
         *  Get connection from the DataSource
         */

            connection = dataSource.getConnection();

        /*
         * Execute the query
         */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                rsRow = new ArrayList<String>();
                rsRow.add(rs.getString(1));
                rsRow.add(rs.getString(2));
                rsRow.add(rs.getString(3));
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
     * Connect to the database and perform select sql to select Formative form details using Formative ID.
     * @param Formative_ID
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> selectFormative(int Formative_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT  F_written_rubic, F_instructor_feedback_comment, F_instructor_feedback_obstacle, " +
                    "F_instructor_feedback_improvement, F_evidence, F_submitted, FK_F, F_date FROM abetasdb.formative,abetasdb.section " +
                    "where Formative_ID = ? AND FK_Section_ID = Section_ID;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Formative_ID);

            rs = preparedStatement.executeQuery();


            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                data.add(String.valueOf(rs.getInt(6)));
                data.add(String.valueOf(rs.getInt(7)));
                data.add(rs.getString(8));
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

            return data;

        }

    }


}
