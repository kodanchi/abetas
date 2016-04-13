package EDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;


public class E_Select {

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
     * selectCycle arraylist used to list all contents of the cycle table
     * @return arraylist of arraylist of strings which contains the cycles
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<Integer> selectCycle() throws ClassNotFoundException, SQLException {

        //ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<Integer> data = new ArrayList<Integer>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM cycle ORDER BY Cycle_ID DESC ;";

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
                //data = new ArrayList<String>();
                data.add(rs.getInt(1));


                //RsArr.add(data);
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
     * selectFormmativeIdToEvaluate method used to show formative report based on formative ID
     * @param FK_Section_ID is the foreign key section ID
     * @return the selected formative based on the ID of the section
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public String  selectFormmativeIdToEvaluate(int FK_Section_ID) throws ClassNotFoundException, SQLException {

        String data = null;
        //ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Formative_ID \n" +
                    "FROM formative \n" +
                    "where FK_Section_ID= ? \n" +
                    "AND F_submitted = 1;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_Section_ID);
            //preparedStatement.setInt(2, FK_Summative_ID);

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
     * selectRubrics method used to select thr rubric based on the rubric ID
     * @param id is the rubric ID
     * @return the data of the rubric based on its ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
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
            //
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
     * selectRubricsFormativeNames  arraylist used to list all contents of formative, link_out_pi, term, cycle tables
     * @param Formative_ID is the formative ID
     * @return arraylist of arraylist of strings which contains the specific rubric formaitve name
     * @throws SQLException once the connection to the database aborted or wrong query occurred
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
            //
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
     * selectRubricsToEvaluate arraylist used to list all contents of the term, cycle tables
     * @param FK_T_ID is the foreign key term ID
     * @return arraylist of arraylist of strings which contains the specific rubric to evaluate
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectRubricsToEvaluate(int FK_T_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT PI_rubric_name_1, PI_rubric_name_2, PI_rubric_name_3, PI_rubric_name_4 \n" +
                    "from term, cycle\n" +
                    "where T_ID = ? \n" +
                    "and FK_Cycle_ID = Cycle_ID;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T_ID);
            //preparedStatement.setString(4, LinkType);

            rs = preparedStatement.executeQuery();
            //
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
     * selectSummativeRubricResultsToEvaluate arraylist used to list all contents of term, cycle tables
     * @param FK_pi_ID is the foreign key performance indicator ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_T_ID is the foreign key term ID
     * @return arraylist of arraylist of strings which contains the specific summative rubric result
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectSummativeRubricResultsToEvaluate(int FK_pi_ID, int FK_P_ID, int FK_T_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT student_rubric \n" +
                    "FROM abetasdb.summative_rubric , summative, link_out_pi\n" +
                    "where FK_Summative_ID = Summative_ID \n" +
                    "and summative.FK_Link_ID = link_out_pi.Link_ID \n" +
                    "and LinkType= 'Summative' \n" +
                    "and link_out_pi.FK_pi_ID= ?  \n" +
                    "and link_out_pi.FK_P_ID= ? \n" +
                    "and link_out_pi.FK_T_ID= ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_pi_ID);
            preparedStatement.setInt(2, FK_P_ID);
            preparedStatement.setInt(3, FK_T_ID);
            //preparedStatement.setString(1, LinkType);

            rs = preparedStatement.executeQuery();

            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1));
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
     * selectSummativeRubricResultsOfCourseToEvaluate arraylist used to list all contents of summative_rubric , summative, link_out_pi, course tables
     * @param FK_pi_ID is the foreign key performance indicator ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_T_ID is the foreign key term ID
     * @param FK_C_ID is the foreign key cycle ID
     * @return arraylist of arraylist of strings which contains the specific summative rubric result of specific course
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectSummativeRubricResultsOfCourseToEvaluate(int FK_pi_ID, int FK_P_ID, int FK_T_ID, String FK_C_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT student_rubric \n" +
                    "FROM abetasdb.summative_rubric , summative, link_out_pi, course\n" +
                    "where FK_Summative_ID= Summative_ID \n" +
                    "and summative.FK_Link_ID = link_out_pi.Link_ID \n" +
                    "and LinkType='Summative' \n" +
                    "AND summative.Sum_submitted=1\n" +
                    "AND link_out_pi.FK_C_ID= course.C_code\n" +
                    "and link_out_pi.FK_pi_ID= ?  \n" +
                    "and link_out_pi.FK_P_ID= ? \n" +
                    "and link_out_pi.FK_T_ID= ? \n" +
                    "AND link_out_pi.FK_C_ID= ?;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_pi_ID);
            preparedStatement.setInt(2, FK_P_ID);
            preparedStatement.setInt(3, FK_T_ID);
            preparedStatement.setString(4, FK_C_ID);

            rs = preparedStatement.executeQuery();

            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1));
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
     * selectSummativeRubricResultsOfSectionToEvaluate arraylist used to list all contents of summative_rubric , summative,
     * link_out_pi, course tables
     * @param FK_Section_ID is the foreign key section ID
     * @param FK_pi_ID is the foreign key performance indicator ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_T_ID is the foreign key term ID
     * @param FK_C_ID is the foreign key cycle ID
     * @return arraylist of arraylist of strings which contains the Summative Rubric Results Of Section
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectSummativeRubricResultsOfSectionToEvaluate(int FK_Section_ID, int FK_pi_ID, int FK_P_ID, int FK_T_ID, String FK_C_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT student_rubric \n" +
                    "FROM abetasdb.summative_rubric , summative, link_out_pi, course\n" +
                    "where FK_Summative_ID= Summative_ID \n" +
                    "and summative.FK_Link_ID = link_out_pi.Link_ID \n" +
                    "and LinkType='Summative' \n" +
                    "AND link_out_pi.FK_C_ID= course.C_code\n" +
                    "AND summative.Sum_submitted=1\n" +
                    "AND summative.FK_Section_ID= ?\n" +
                    "and link_out_pi.FK_pi_ID= ?  \n" +
                    "and link_out_pi.FK_P_ID= ? \n" +
                    "and link_out_pi.FK_T_ID= ? \n" +
                    "AND link_out_pi.FK_C_ID= ?;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_Section_ID);
            preparedStatement.setInt(2, FK_pi_ID);
            preparedStatement.setInt(3, FK_P_ID);
            preparedStatement.setInt(4, FK_T_ID);
            preparedStatement.setString(5, FK_C_ID);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1));
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
     * selectCourseName method used to select the course name based on the code
     * @param code is the course code
     * @return the course name based on the course code
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public String selectCourseName(String code) throws ClassNotFoundException, SQLException {

        String name = "";
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT C_name FROM course WHERE C_code = '"+code+"' ;";

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
     * selectTermToEvaluate arraylist used to list all contents of the term table
     * @param id is the term ID
     * @return the term to be evaluated if it exists
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectTermToEvaluate(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM term where FK_Cycle_ID = ?;";

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
                RowDate.add(rs.getString(4));


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
     * selectPIofSummativeToEvaluate arraylist used to list all contents of link_out_pi, performance_indicator, program ,
     * summative table
     * @param FK_T_ID is the foreign key term ID
     * @param FK_P_ID is the foreign key program ID
     * @return  the performance indicator of the selected summative if it exists
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectPIofSummativeToEvaluate(int FK_T_ID, int FK_P_ID) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "SELECT distinct FK_pi_ID, PI_name, LinkType \n" +
                    "From abetasdb.link_out_pi, abetasdb.performance_indicator, program , summative\n" +
                    "WHERE FK_T_ID = ? \n" +
                    "AND FK_PI_ID = PI_Label \n" +
                    "AND link_out_pi.FK_P_ID = ? \n" +
                    "AND program.P_ID = link_out_pi.FK_P_ID \n" +
                    "AND summative.Sum_submitted = 1\n" +
                    "AND summative.FK_Link_ID= link_out_pi.Link_ID;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T_ID);
            preparedStatement.setInt(2, FK_P_ID);

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
     * selectPIofOutcomeSummativeToEvaluate arraylist used to list all contents of the link_out_pi, performance_indicator,
     * program , summative tables
     * @param FK_T_ID is the foreign key term ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_out is the foreign key outcome
     * @return the preformance indicator of specific outcome of specific summative report if it exists
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectPIofOutcomeSummativeToEvaluate(int FK_T_ID, int FK_P_ID, int FK_out) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {



            String query = "SELECT distinct FK_pi_ID, PI_name \n" +
                    "From abetasdb.link_out_pi, abetasdb.performance_indicator, program , summative\n" +
                    "WHERE FK_T_ID = ? \n" +
                    "AND FK_PI_ID = PI_Label\n" +
                    "AND link_out_pi.FK_P_ID = ? \n" +
                    "AND link_out_pi.FK_out = ? \n" +
                    "AND program.P_ID = link_out_pi.FK_P_ID\n" +
                    "AND summative.Sum_submitted = 1\n" +
                    "AND summative.FK_Link_ID= link_out_pi.Link_ID;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T_ID);
            preparedStatement.setInt(2, FK_P_ID);
            preparedStatement.setInt(3, FK_out);

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

    /**
     * selectPIofOutcomeFormativeToEvaluate  arraylist used to list all contents of the link_out_pi, performance_indicator,
     * program , formative tables
     * @param FK_T_ID is the forign key term ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_out is the foreign key outcome
     * @return the preformance indicator of specific outcome of specific formative report if it exists
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectPIofOutcomeFormativeToEvaluate(int FK_T_ID, int FK_P_ID, int FK_out) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            /*String query = "SELECT FK_pi_ID, PI_name, LinkType From abetasdb.link_out_pi, abetasdb.performance_indicator, " +
                    "program WHERE FK_T_ID = ? AND FK_PI_ID = PI_Label AND link_out_pi.FK_P_ID = ? AND " +
                    "program.P_ID = link_out_pi.FK_P_ID ;";*/

            String query = "SELECT distinct FK_pi_ID, PI_name, FK_Section_ID \n" +
                    "From abetasdb.link_out_pi,abetasdb.performance_indicator, program , formative \n" +
                    "WHERE FK_T_ID = ? \n" +
                    "AND FK_PI_ID = PI_Label\n" +
                    "AND link_out_pi.FK_P_ID = ? \n" +
                    "AND link_out_pi.FK_out = ? \n" +
                    "AND program.P_ID = link_out_pi.FK_P_ID \n" +
                    "AND formative.F_submitted = 1\n" +
                    "AND formative.FK_Link_ID = link_out_pi.Link_ID ;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T_ID);
            preparedStatement.setInt(2, FK_P_ID);
            preparedStatement.setInt(3, FK_out);

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
     * selectOutcomesToEvaluate  arraylist used to list all contents of the link_out_pi,p_student_outcome, program tables
     * @param FK_T_ID is the foreign key term ID
     * @param FK_P_ID is the foreign key program ID
     * @return the outcome if it exists
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectOutcomesToEvaluate(int FK_T_ID, int FK_P_ID) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "SELECT distinct FK_out, Student_outcome \n" +
                    "From abetasdb.link_out_pi, abetasdb.p_student_outcome, program\n" +
                    "WHERE FK_T_ID = ? \n" +
                    "AND FK_out = Outcome_label\n" +
                    "AND link_out_pi.FK_P_ID = ? \n" +
                    "AND program.P_ID = link_out_pi.FK_P_ID\n;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T_ID);
            preparedStatement.setInt(2, FK_P_ID);

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

    /**
     * selectCoursesOfSummativeToEvaluate arraylist used to list all contents of the course,link_out_pi, performance_indicator,summative tables
     * @param FK_T_ID is the foreign key term ID
     * @param FK_P_ID is the foreign key program ID
     * @return the summative course if it exists
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectCoursesOfSummativeToEvaluate(int FK_T_ID, int FK_P_ID) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "SELECT distinct C_code,C_name \n" +
                    "FROM course, abetasdb.link_out_pi, performance_indicator,summative\n" +
                    "where LinkType='Summative'\n" +
                    "AND Link_ID = FK_Link_ID\n" +
                    "AND FK_PI_ID= performance_indicator.PI_Label\n" +
                    "AND link_out_pi.FK_C_ID = C_code\n" +
                    "AND summative.Sum_submitted = 1\n" +
                    "AND FK_T_ID= ?\n" +
                    "AND link_out_pi.FK_P_ID = ? ;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T_ID);
            preparedStatement.setInt(2, FK_P_ID);

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

    /**
     * selectSectionsCourseOfSummativeToEvaluate arraylist used to list all contents of the course, abetasdb.link_out_pi, performance_indicator,summative tables
     * @param FK_T_ID is the foreign key term ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_C_ID is the foreign key cycle ID
     * @return the section of the course that's summative if it exists
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectSectionsCourseOfSummativeToEvaluate(int FK_T_ID, int FK_P_ID, String FK_C_ID) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "SELECT distinct summative.FK_Section_ID\n" +
                    "FROM course, abetasdb.link_out_pi, performance_indicator,summative\n" +
                    "where LinkType='Summative'\n" +
                    "AND Link_ID = FK_Link_ID\n" +
                    "AND FK_PI_ID= performance_indicator.PI_Label\n" +
                    "AND link_out_pi.FK_C_ID = C_code\n" +
                    "AND summative.Sum_submitted = 1\n" +
                    "AND FK_T_ID= ?\n" +
                    "AND link_out_pi.FK_P_ID = ? \n" +
                    "AND link_out_pi.FK_C_ID=?;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T_ID);
            preparedStatement.setInt(2, FK_P_ID);
            preparedStatement.setString(3, FK_C_ID);

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

    /**
     * selectEvidenceOfSummativeToEvaluate arraylist used to list all contents of the summative, link_out_pi, course, section tables
     * @param FK_Section_ID is the foreign key section ID
     * @param FK_pi_ID is the foreign key Pi ID
     * @param FK_T_ID is the foreign key term ID
     * @param FK_P_ID is the program ID
     * @param FK_C_ID is the cycle ID
     * @return the select Evidence Of Summative To Evaluate
     */
    public String selectEvidenceOfSummativeToEvaluate(int FK_Section_ID, int FK_pi_ID, int FK_T_ID, int FK_P_ID, String FK_C_ID) throws ClassNotFoundException, SQLException {

        String data = null;

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "SELECT Sum_evidence \n" +
                    "FROM  summative, link_out_pi, course, abetasdb.section\n" +
                    "where summative.FK_Link_ID = link_out_pi.Link_ID \n" +
                    "and LinkType='Summative' \n" +
                    "AND link_out_pi.FK_C_ID= course.C_code\n" +
                    "AND summative.FK_Section_ID=abetasdb.section.Section_ID\n" +
                    "AND summative.Sum_submitted=1\n" +
                    "AND summative.FK_Section_ID= ? \n" +
                    "and link_out_pi.FK_pi_ID= ?  \n" +
                    "and link_out_pi.FK_P_ID= ? \n" +
                    "and link_out_pi.FK_T_ID= ? \n" +
                    "AND link_out_pi.FK_C_ID= ?;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_Section_ID);
            preparedStatement.setInt(2, FK_pi_ID);
            preparedStatement.setInt(4, FK_T_ID);
            preparedStatement.setInt(3, FK_P_ID);
            preparedStatement.setString(5, FK_C_ID);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                data = rs.getString(1);
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
     * selectThresholdOfSummativeToEvaluate method used threshold to evaulate
     * @param PI_Label is the pi ID
     * @return the threshold
     */

    public int selectThresholdOfSummativeToEvaluate(int PI_Label) throws ClassNotFoundException, SQLException {

        int data = -1;

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "select Threshold\n" +
                    "from performance_indicator\n" +
                    "where performance_indicator.PI_Label = ?";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, PI_Label);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                data = rs.getInt(1);
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
     * selectThresholdToEvaluate method used to evaluate
     * @param PI_Label is the PI ID
     * @return the threshold
     */
    public int selectThresholdToEvaluate(int PI_Label) throws ClassNotFoundException, SQLException {

        int data = -1;

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "SELECT Threshold \n" +
                    "FROM abetasdb.performance_indicator\n" +
                    "where PI_Label = ?;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, PI_Label);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                data = rs.getInt(1);
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
     * selectPIofFormativeToEvaluate method used to evaulate
     * @param FK_T_ID is the foreign key of the term ID
     * @param FK_P_ID is the foreign key of the program ID
     * @return pi formative
     */

    public ArrayList<ArrayList<String>> selectPIofFormativeToEvaluate(int FK_T_ID, int FK_P_ID) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            /*String query = "SELECT FK_pi_ID, PI_name, LinkType From abetasdb.link_out_pi, abetasdb.performance_indicator, " +
                    "program WHERE FK_T_ID = ? AND FK_PI_ID = PI_Label AND link_out_pi.FK_P_ID = ? AND " +
                    "program.P_ID = link_out_pi.FK_P_ID ;";*/

            String query = "SELECT FK_pi_ID, PI_name, LinkType, FK_Section_ID From abetasdb.link_out_pi, " +
                    "abetasdb.performance_indicator, program , formative\n" +
                    "WHERE FK_T_ID = ? \n" +
                    "AND FK_PI_ID = PI_Label \n" +
                    "AND link_out_pi.FK_P_ID = ? \n" +
                    "AND program.P_ID = link_out_pi.FK_P_ID \n" +
                    "AND formative.F_submitted = 1\n" +
                    "AND formative.FK_Link_ID = link_out_pi.Link_ID ;";
            //,abetasdb.course,abetasdb.performance_indicator,abetasdb.p_student_outcome,abetasdb.program,abetasdb.term

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T_ID);
            preparedStatement.setInt(2, FK_P_ID);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(1));
                RowDate.add(rs.getString(2));
                RowDate.add(rs.getString(3));
                RowDate.add(rs.getString(4));


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
     * selectAllProgramsofEvaluatorToEvaluate arraylist used to list all the contents of program
     * @param evaluator is the evaulator ID
     * @return th list of program for the evaluator
     */
    public ArrayList<ArrayList<String>> selectAllProgramsofEvaluatorToEvaluate(int evaluator) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> dataRow = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT P_ID, P_name \n" +
                    "FROM abetasdb.program, evaluator\n" +
                    "WHERE E_program = P_name\n" +
                    "AND E_ID = ?;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, evaluator);

            rs = preparedStatement.executeQuery();
            //
            while (rs.next()) {
                dataRow = new ArrayList<String>();
                dataRow.add(rs.getString(1));
                dataRow.add(rs.getString(2));
                data.add(dataRow);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */
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

        }

        return data;

    }

    /**
     * selectAllProgramsToEvaluate arraylist used to select progrm that the evaluator is assigned to
     * @return the programs for the evaluator
     */

    public ArrayList<ArrayList<String>> selectAllProgramsToEvaluate() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> dataRow = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM abetasdb.program";

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
            while (rs.next()) {
                dataRow = new ArrayList<String>();
                dataRow.add(rs.getString(1));
                dataRow.add(rs.getString(2));
                data.add(dataRow);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */
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

        }

        return data;

    }

    /**
     * selectProgramName method used to select the program name based o the evaluator ID
     * @param id evaluator ID
     * @return the program name based on the evaluator ID
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
     * selectLinkIDOfFormF method used to select the ID of formative
     * @param id is the evaluator ID
     * @return the forms that the evaluator has

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
     * selectPILinksValuse arraylist used to select the value for  the selected PI
     * @param id is the evaluator ID
     * @return he value for  the selected PI

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
     * selectOutForLinkSingle arraylist used to list the outcome for single link
     * @param oid outcome ID
     * @return the single link

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
     * selectPIForLinkSingle arraylist used to list the PI for single link
     * @param id is the PI link
     * @return the PI for single link
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
     * selectCourseLevel method used to select the course level
     * @param code is the code ID
     * @return the course level

     */
    public String selectCourseLevel(String code) throws ClassNotFoundException, SQLException {

        String name = "";
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT C_level FROM course WHERE C_code = '"+code+"' ;";

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
     * selectTermName method to select the term name
     * @param id is the term ID
     * @return the term ID

     */
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
     * selectTermYear method used to select the term year
     * @param id is the term ID
     * @return the term ID

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
     * selectFacultyForForm method used to select the faculty member (formative/summative)
     * @param id is the faculty member

     */
    public String selectFacultyForForm(int id) throws ClassNotFoundException, SQLException {

        String name="";
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Faculty_ID, Faculty_Fname, Faculty_Mname, Faculty_Lname FROM abetasdb.faculty_member WHERE Faculty_ID = ?;";

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
     * selectFormative arraylist used to list all contents of the formative,section table
     * @param Formative_ID is the formative ID
     * @return the list of selective formative based on formative ID

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
            //
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
