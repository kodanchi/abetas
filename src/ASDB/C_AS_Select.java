package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;



public class C_AS_Select {


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
     * selectProgram arraylist used to list all contents of the program table
     * @return arraylist of arraylist of strings which containts the program
     * @throws SQLException once the connection to the database aborted or wrong query occurred
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
     * selectAllProgram arraylist used to list all contents of the program table
     * @return arraylist of arraylist of strings which containts the program
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectAllPrograms() throws ClassNotFoundException, SQLException {

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
     *
     * selectCycle arraylist used to list all contents of the cycle table
     * @return arraylist of arraylist of strings which containts the cycle
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
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

    /**
     * selectStudents arraylist used to list all contents of the students table
     * @param id is the student ID
     * @return arraylist of arraylist of strings which contains the specific student
     * @throws SQLException once the connection to the database aborted or wrong query occurred
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
     * selectPerformanceIndicators arraylist used to list all contents of the perfromance_indicator, program, cycle tables
     * @param id is the performance ID
     * @param FK_C_ID is the foreign key cycle ID
     * @return arraylist of arraylist of strings which contains the specific performance indicator
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectPerformanceIndicators(int id, int FK_C_ID) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT PI_label, PI_name, Threshold FROM performance_indicator, program, cycle WHERE FK_P_ID = ? AND" +
                    " FK_C_ID = ? AND P_ID = FK_P_ID AND Cycle_ID = FK_C_ID  ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, FK_C_ID);

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
     * selectPerformanceIndicatorsForCycle arraylist used to list all contents of the perfromance_indicator, program, cycle tables
     * @param P_ID is the program ID
     * @param T_ID is the term ID
     * @return arraylist of arraylist of strings which contains the specific performance indicator for cycle
     * @throws ClassNotFoundException
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectPerformanceIndicatorsForCycle(int P_ID, int T_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> RsArr = new ArrayList<String>();
        //ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            //String query = "SELECT C_ID, C_include, C_code, C_name, C_level FROM program_has_course,course,term_contains_courses WHERE FK_C_code = C_code and FK_course_code = C_code and FK_T_ID = ? and FK_program_ID = ?;";
            String query = "SELECT PI_label, PI_name FROM performance_indicator, program, cycle WHERE FK_P_ID = ? AND" +
                    " FK_C_ID = ? AND P_ID = FK_P_ID AND Cycle_ID = FK_C_ID  ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, P_ID);
            preparedStatement.setInt(2, T_ID);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                RsArr.add(rs.getString(1)+": "+rs.getString(2));
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
     * selectCourseSections arraylist used to list all contents of the perfromance_indicator, program, cycle tables
     * @param termId is the term ID
     * @param courseId is the course ID
     * @return arraylist of arraylist of strings which contains the specific performance indicator for cycle
     * @throws ClassNotFoundException
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public ArrayList<ArrayList<String>> selectCourseSections(int termId, String courseId) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Section_ID, FK_F FROM abetasdb.section WHERE FK_T = ? AND FK_C = ?;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, termId);
            preparedStatement.setString(2, courseId);

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
     * selectSectionFaculty method is used to select the section for the faculty member
     * @param Section_ID is the section ID
     * @return if the faculkty member is added on the section or not
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public String selectSectionFaculty(int Section_ID) throws ClassNotFoundException, SQLException {

        //ArrayList<String> RsArr = new ArrayList<String>();
        String result = null;
        //ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Faculty_Fname, Faculty_Mname, Faculty_Lname FROM abetasdb.section, faculty_member WHERE FK_F = Faculty_ID AND Section_ID = ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Section_ID);


            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                result = rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
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

    /**selectionFacultyID method used to find the faculty member of the selected section
     *
     * @param Section_ID is the section ID
     * @return the faculty member ID after select the section
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public int selectSectionFacultyID(int Section_ID) throws ClassNotFoundException, SQLException {

        //ArrayList<String> RsArr = new ArrayList<String>();
        int id = -1;
        //ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT FK_F FROM abetasdb.section WHERE Section_ID = ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Section_ID);


            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                id = rs.getInt(1);
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

            return id;

        }

    }

    /**
     * selectPIForLink arraylist used to list all contents of the perfromance_indicator, program, cycle tables
     * @param FK_C_ID is the foreign key cycle ID
     * @param FK_P_ID is the foreign key program ID
     * @return arraylist of arraylist of strings which contains the specific performance indicator for link
     * @throws ClassNotFoundException
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectPIForLink(int FK_C_ID, int FK_P_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT  PI_Label, PI_name FROM abetasdb.program, abetasdb.performance_indicator, cycle where" +
                    "  FK_C_ID = ? AND FK_P_ID = ? AND P_ID = FK_P_ID AND Cycle_ID = FK_C_ID ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_C_ID);
            preparedStatement.setInt(2, FK_P_ID);

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
     * selectCourseForTerm arraylist used to list all contents of the program_has_course,course,term_contains_courses tables
     * @param P_ID  is the program ID
     * @param T_ID is the term ID
     * @return arraylist of arraylist of strings which contains the specific course for term
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectCourseForTerm(int P_ID, int T_ID) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT C_ID, C_include, C_code, C_name, C_level FROM program_has_course,course,term_contains_courses WHERE FK_C_code = C_code and FK_course_code = C_code and FK_T_ID = ? and FK_program_ID = ?;";


            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, T_ID);
            preparedStatement.setInt(2, P_ID);

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

    /**
     * selectCourseForProgram arraylist used to list all contents of the course,program_has_course tables
     * @param P_ID is the program ID
     * @param T_ID is the term ID
     * @return arraylist of arraylist of strings which contains the specific course for program
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectCourseForProgram(int P_ID, int T_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> RsArr = new ArrayList<String>();
        //ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            //String query = "SELECT C_ID, C_include, C_code, C_name, C_level FROM program_has_course,course,term_contains_courses WHERE FK_C_code = C_code and FK_course_code = C_code and FK_T_ID = ? and FK_program_ID = ?;";
            String query = "SELECT C_code,C_name FROM course,program_has_course WHERE FK_course_code = C_code and FK_program_ID = ?  " +
                    "AND C_code NOT IN(SELECT C_code FROM program_has_course,course,term_contains_courses " +
                    "WHERE FK_C_code = C_code and FK_course_code = C_code and FK_T_ID = ? and FK_program_ID = ?);";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, P_ID);
            preparedStatement.setInt(2, T_ID);
            preparedStatement.setInt(3, P_ID);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                RsArr.add(rs.getString(1)+": "+rs.getString(2));
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
     * selectCourseForLink arraylist used to list all contents of the course,term_contains_courses,program_has_course,section tables
     * @param FK_program_ID the foreign key program ID
     * @param FK_T_ID the foreign key term ID
     * @return arraylist of arraylist of strings which contains the course for link
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectCourseForLink(int FK_program_ID, int FK_T_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            //String query = "SELECT C_code,C_name FROM course,program_has_course WHERE FK_course_code = C_code and FK_program_ID = "+ id +" ;";
            String query = "SELECT distinct C_code,C_name FROM course,term_contains_courses,program_has_course,abetasdb.section " +
                    "WHERE FK_course_code = C_code and FK_program_ID = ? and FK_C_code = C_code and FK_T_ID = ? and FK_C = C_code;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_program_ID);
            preparedStatement.setInt(2, FK_T_ID);
            //preparedStatement.setInt(3, FK_program_ID);

            rs = preparedStatement.executeQuery();

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
     * selectRubrics arraylist used to list all contents of the cycle table
     * @param id is the rubric ID
     * @return arraylist of arraylist of strings which contains the rubric information
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectRubrics(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT PI_rubric_description_1, PI_rubric_description_2, PI_rubric_description_3, PI_rubric_description_4 FROM pi_rubric WHERE PI_rubric_ID = "+ id +" ;";

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
     * selectRubricNames arraylist used to list all contents of the cycle
     * @param id is the Rubric ID
     * @return arraylist of arraylist of strings which contains the specific rubric name
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public ArrayList<String> selectRubricNames(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rsSelect = null;
        ResultSet rs = null;
        try {

            connection = dataSource.getConnection();


                String query = "SELECT PI_rubric_name_1, PI_rubric_name_2, PI_rubric_name_3, PI_rubric_name_4 FROM cycle WHERE Cycle_ID = ? ;";

            /*
             *  Get connection from the DataSource
             */


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
     * selectCourseName method used to select the course based on the course code
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
     * selectAddTerm arraylist used to list all contents of the term table
     * @param FK_Cycle_ID is the foreign key cycle ID
     * @return arraylist of arraylist of strings which contains the specific term
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectAddTerm(int FK_Cycle_ID) throws ClassNotFoundException, SQLException {

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
            preparedStatement.setInt(1, FK_Cycle_ID);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(1));
                RowDate.add(rs.getString(2));
                RowDate.add(rs.getString(3));
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

    /**
     * selectPILinks arraylist used to list all contents of the perfromance_indicator, program, cycle tables
     * @param id is the performance indicator ID
     * @param pid is the program ID
     * @return arraylist of arraylist of strings which contains the specific performance indicator link
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<ArrayList<String>> selectPILinks(int id, int pid) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Link_ID,FK_out, Student_outcome, FK_pi_ID, PI_name , link_out_pi.FK_C_ID, LinkType," +
                    " FK_R_ID, link_out_pi.FK_P_ID, FK_T_ID \n" +
                    "From abetasdb.link_out_pi , performance_indicator, p_student_outcome\n" +
                    "WHERE FK_T_ID = ? \n" +
                    "AND link_out_pi.FK_P_ID = ?\n" +
                    "AND FK_out = Outcome_label\n" +
                    "AND link_out_pi.FK_pi_ID= PI_Label\n" +
                    "AND p_student_outcome.FK_P_ID=link_out_pi.FK_P_ID\n" +
                    "AND link_out_pi.FK_P_ID = performance_indicator.FK_P_ID;";
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
            preparedStatement.setInt(2, pid);

            rs = preparedStatement.executeQuery();

            //
            while (rs.next()){
                RowDate = new ArrayList<String>();
                RowDate.add(rs.getString(1));
                RowDate.add(rs.getString(2)+": "+rs.getString(3));
                RowDate.add(rs.getString(4)+": "+rs.getString(5));
                RowDate.add(rs.getString(6));
                RowDate.add(rs.getString(7));
                RowDate.add(rs.getString(8));
                RowDate.add(rs.getString(9));
                RowDate.add(rs.getString(10));

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
     * selectProgramID method used to select the program based on ID
     * @param pName Program name
     * @return the program ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public int selectProgramID(String pName) throws ClassNotFoundException, SQLException {

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
            String querySelect = " SELECT P_ID FROM program where P_name =\""+pName+"\";";

            preparedStatement = connection.prepareStatement(querySelect);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((id= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                id= rsSelect.getInt(1);
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
     * selectProgramName method used to select the program based on program name
     * @param id is the program ID
     * @return the program name
     * @throws ClassNotFoundException
     * @throws SQLException once the connection to the database aborted or wrong query occurred
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
     * selectFacultyForCourse arraylist used to list all contents of the faculty_member table
     * @return arraylist of arraylist of strings which contains the faculty member who's chose for course
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectFacultyForCourse() throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Faculty_ID, Faculty_Fname, Faculty_Mname, Faculty_Lname FROM abetasdb.faculty_member;";

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
                data.add(rs.getString(1)+": "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
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
     * selectOutForLink arraylist used to list all contents of the program, p_student_outcome tables
     * @param id is the ID of outcome
     * @return arraylist of arraylist of strings which contains the outcome for the link based on outcome ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<String> selectOutForLink(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Outcome_label, Student_outcome FROM abetasdb.program, abetasdb.p_student_outcome where P_ID = FK_P_ID and FK_P_ID = "+ id +" ;";

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
     * selectMatchedRubrics arraylist used to list all contents of the link_out_pi, pi_rubric tables
     * @param FK_out is the foreign key outcome
     * @param FK_pi_ID is the foreign key performance indicator ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_C_ID is the foreign key cycle ID
     * @return arraylist of arraylist of strings which contains the rubric that matched with program
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public ArrayList<String> selectMatchedRubrics(int FK_out, int FK_pi_ID, int FK_P_ID,String FK_C_ID) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT PI_rubric_description_1,\tPI_rubric_description_2 ,PI_rubric_description_3,PI_rubric_description_4 \n" +
                    "FROM link_out_pi, pi_rubric\n" +
                    "where FK_out =?\n" +
                    "and FK_pi_ID = ?\n" +
                    "and FK_P_ID= ?\n" +
                    "and FK_C_ID = ?\n" +
                    "and FK_R_ID = PI_rubric_ID\n" +
                    "ORDER BY Link_ID DESC limit 1;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_out);
            preparedStatement.setInt(2, FK_pi_ID);
            preparedStatement.setInt(3, FK_P_ID);
            preparedStatement.setString(4, FK_C_ID);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                data = new ArrayList<String>();
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
     * selectTermCourseSection arraylist used to list all contents of the section table,after that select the term,course and section
     * @param FK_C is the foreign key of cycle
     * @param FK_T is the foreign key of term
     * @return arraylist of arraylist of strings which contains the specific performance indicator for cycle
     * @throws ClassNotFoundException
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<Integer> selectTermCourseSection(String FK_C,int FK_T) throws ClassNotFoundException, SQLException {

        ArrayList<Integer> data = new ArrayList<Integer>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Section_ID FROM abetasdb.section where FK_C = ? and FK_T = ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, FK_C);
            preparedStatement.setInt(2, FK_T);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getInt(1));
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
     * selectSFormsofSameCourse arraylist used to list all contents of the section , summative tables to find the forms of the same course
     * @param FK_T is the foreign key of term
     * @param FK_C is the foreign key of cycle
     * @return arraylist of arraylist of strings which contains the forms of same course
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<Integer> selectSFormsofSameCourse(int FK_T, String FK_C) throws ClassNotFoundException, SQLException {

        ArrayList<Integer> data = new ArrayList<Integer>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "SELECT distinct summative.FK_Link_ID\n" +
                    "FROM abetasdb.section , summative\n" +
                    "where section.Section_ID = summative.FK_Section_ID\n" +
                    "AND FK_T= ? \n" +
                    "AND FK_C= ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T);
            preparedStatement.setString(2, FK_C);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getInt(1));
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
     * selectFFormsofSameCourse arraylist used to list all contents of section , formative tables to find the formative forms of the same course
     * @param FK_T is the foreign key of term
     * @param FK_C is the coreign key of cycle
     * @return arraylist of arraylist of strings which contains the forms of same course
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<Integer> selectFFormsofSameCourse(int FK_T, String FK_C) throws ClassNotFoundException, SQLException {

        ArrayList<Integer> data = new ArrayList<Integer>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT distinct formative.FK_Link_ID\n" +
                    "FROM abetasdb.section , formative\n" +
                    "where abetasdb.section.Section_ID = formative.FK_Section_ID\n" +
                    "AND FK_T= ? \n" +
                    "AND FK_C= ? ;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, FK_T);
            preparedStatement.setString(2, FK_C);

            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getInt(1));
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
     * selectPiforNewSectionSameCourse arraylist used to list all contents of link_out_pi table to find the performance indicator for the new section which has the same course
     * @param Link_ID is the link ID
     * @return arraylist of arraylist of strings which contains the performance indicator for the new section which has the same course
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public ArrayList<Integer> selectPiforNewSectionSameCourse(int Link_ID) throws ClassNotFoundException, SQLException {

        ArrayList<Integer> data = new ArrayList<Integer>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT FK_out , FK_PI_ID, FK_P_ID,FK_R_ID \n" +
                    "FROM abetasdb.link_out_pi\n" +
                    "where Link_ID= ?;";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Link_ID);


            rs = preparedStatement.executeQuery();
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getInt(1));
                data.add(rs.getInt(2));
                data.add(rs.getInt(3));
                data.add(rs.getInt(4));
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
     * selectFormType method used to select the form type (summative/formative)
     * @param id is the form ID
     * @return the type of form (summative/formative)
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public String selectFormType(int id) throws ClassNotFoundException, SQLException {

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
            String querySelect = " SELECT LinkType FROM link_out_pi where Link_ID = ?";

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
     * isTermYearExist method used as boolean to retrun if the term year exists or not
     * @param T_name is the term name
     * @param T_year is the term year
     * @param FK_Cycle_ID is the foreign key cycle ID
     * @return true or false if the term year exist or not
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public boolean isTermYearExist(String T_name, String T_year, int FK_Cycle_ID) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        int isExist = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String querySelect = "SELECT EXISTS(SELECT * FROM term where T_name = ? AND T_year = ? AND FK_Cycle_ID = ? );";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setString (1, T_name);
            preparedStatement.setString (2, T_year);
            preparedStatement.setInt (3, FK_Cycle_ID);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((termName= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                isExist= rsSelect.getInt(1);
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
        return isExist == 0 ? false : true;
    }

    /**
     * isStudentIDExist method used as boolean to return if the student exists or not
     * @param Student_ID is the stuent ID
     * @param FK_Section is the foreign key section
     * @return true or false if the student exist or not
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public boolean isStudentIDExist(String Student_ID, int FK_Section) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        int isExist = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String querySelect = "SELECT EXISTS(SELECT * FROM students where Student_ID = ? AND FK_Section = ? );";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setString (1, Student_ID);
            preparedStatement.setInt (2, FK_Section);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((termName= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                isExist= rsSelect.getInt(1);
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
        return isExist == 0 ? false : true;
    }

    /**isStudentIDExistExcept
     * isStudentIDExistExcept method used as boolean to return if the student exists or not except specific student
     * @param Student_ID is the student ID
     * @param FK_Section is the foreign key section
     * @param S_ID is the exception student ID
     * @return true or false if the student exist or not
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public boolean isStudentIDExistExcept(String Student_ID, int FK_Section, int S_ID) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        int isExist = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String querySelect = "SELECT EXISTS(SELECT * FROM students where Student_ID = ? AND FK_Section = ? AND Student_ID NOT IN " +
                    "(SELECT  Student_ID FROM students WHERE S_ID = ? ));";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setString (1, Student_ID);
            preparedStatement.setInt (2, FK_Section);
            preparedStatement.setInt (3, S_ID);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((termName= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                isExist= rsSelect.getInt(1);
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
        return isExist == 0 ? false : true;
    }

    /**
     * isPILinkExist method used as boolean to return if the Performance indicator link exists or not
     * @param FK_out is the foreign key outcome
     * @param FK_pi_ID is foreign key the performance ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_C_ID is the foreign key cycle ID
     * @param FK_T_ID is the foreign key term ID
     * @param LinkType is the type of link (summative/formative)
     * @return true or false if the sthe Performance indicator link exists or not
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public boolean isPILinkExist(int FK_out, int FK_pi_ID, int FK_P_ID, String FK_C_ID, int FK_T_ID, String LinkType) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        int isExist = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String querySelect = "SELECT EXISTS(SELECT * FROM link_out_pi where FK_out = ? AND FK_pi_ID = ? AND FK_P_ID = ? " +
                    " AND FK_C_ID = ? AND FK_T_ID = ? AND LinkType = ?) ;";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setLong (1, FK_out);
            preparedStatement.setInt (2, FK_pi_ID);
            preparedStatement.setInt (3, FK_P_ID);
            //preparedStatement.setInt (4, FK_R_ID);
            preparedStatement.setString (4, FK_C_ID);
            preparedStatement.setInt (5, FK_T_ID);
            preparedStatement.setString (6, LinkType);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((termName= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                isExist= rsSelect.getInt(1);
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
        return isExist == 0 ? false : true;
    }

    /**
     * isPILinkExist method used as boolean to return if the Performance indicator link exists or not except specific Performance indicator link
     * @param FK_out is the foreign key outcome
     * @param FK_pi_ID is foreign key the performance ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_C_ID is the foreign key cycle ID
     * @param FK_T_ID is the foreign key term ID
     * @param LinkType is the type of link (summative/formative)
     * @param Link_ID is the link ID
     * @return is the performance indicator link exist or not
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public boolean isPILinkExistExcept(int FK_out, int FK_pi_ID, int FK_P_ID, String FK_C_ID, int FK_T_ID, String LinkType, int Link_ID) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        int isExist = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String querySelect = "SELECT EXISTS(SELECT * FROM link_out_pi where FK_out = ? AND FK_pi_ID = ? AND FK_P_ID = ? " +
                    " AND FK_C_ID = ? AND FK_T_ID = ? AND LinkType = ? AND FK_R_ID NOT IN " +
                    "(SELECT FK_R_ID FROM link_out_pi WHERE Link_ID = ? ));";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setLong (1, FK_out);
            preparedStatement.setInt (2, FK_pi_ID);
            preparedStatement.setInt (3, FK_P_ID);
            //preparedStatement.setInt (4, FK_R_ID);
            preparedStatement.setString (4, FK_C_ID);
            preparedStatement.setInt (5, FK_T_ID);
            preparedStatement.setString (6, LinkType);
            preparedStatement.setInt (7, Link_ID);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((termName= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                isExist= rsSelect.getInt(1);
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
        return isExist == 0 ? false : true;
    }

    /**
     * isPIExist method used as boolean to return if the Performance indicator  exists or not
     * @param PI_name is the performance indicator name
     * @param FK_P_ID is the foreign key program ID
     * @param FK_C_ID is the foreign cycle ID
     * @return if the performance indicator exist or not
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public boolean isPIExist(String PI_name, int FK_P_ID, int FK_C_ID) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        int isExist = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String querySelect = "SELECT EXISTS(SELECT * FROM performance_indicator where PI_name = ? AND FK_P_ID = ? AND FK_C_ID = ?) ;";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setString (1, PI_name);
            preparedStatement.setInt (2, FK_P_ID);
            preparedStatement.setInt (3, FK_C_ID);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((termName= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                isExist= rsSelect.getInt(1);
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
        return isExist == 0 ? false : true;
    }

    /**
     * isPIExist method used as boolean to return if the Performance indicator  exists or not except specific Performance indicator
     * @param PI_name is the performance indicator name
     * @param FK_P_ID is the foreign key program ID
     * @param FK_C_ID is the foreign cycle ID
     * @param PI_Label
     * @return if the performance indicator exist or not
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public boolean isPIExistExcept(String PI_name, int FK_P_ID, int FK_C_ID, int PI_Label ) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        int isExist = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String querySelect = "SELECT EXISTS(SELECT * FROM performance_indicator where PI_name = ? AND FK_P_ID = ? AND FK_C_ID = ? " +
                    "AND PI_name NOT IN (SELECT PI_name FROM performance_indicator WHERE PI_Label = ?)) ;";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setString (1, PI_name);
            preparedStatement.setInt (2, FK_P_ID);
            preparedStatement.setInt (3, FK_C_ID);
            preparedStatement.setInt (4, PI_Label);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((termName= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                isExist= rsSelect.getInt(1);
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
        return isExist == 0 ? false : true;
    }

    /**
     * selectThreshold method used to select the threshold percentage
     * @param PI_Label is the performance indicator ID
     * @return the threshold percentage
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public int selectThreshold(int PI_Label) throws ClassNotFoundException, SQLException {

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




}
