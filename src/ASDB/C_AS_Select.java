package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ibrahim Abuaqel on 1/31/2016.
 */
public class C_AS_Select {

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

    public ArrayList<ArrayList<String>> selectObjective(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Objective_label, Objective FROM abetasdb.program, abetasdb.p_objective where P_ID = FK_P_ID and FK_P_ID = "+ id +" ;";

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



    public ArrayList<ArrayList<String>> selectStudentOutcomeWithObjectives(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM abetasdb.link_out_obj WHERE FK_P_ID = "+ id +" ;";

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
                RowDate.add(selectWholeObj(Integer.parseInt(rs.getString(2))).get(0));
                RowDate.add(selectWholeOut(Integer.parseInt(rs.getString(3))).get(0));

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




    public ArrayList<ArrayList<String>> selectStudentOutcomes(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM p_student_outcome WHERE FK_P_ID = "+ id +" ;";

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



    public ArrayList<ArrayList<String>> selectCourses(int id) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT C_ID, C_code,C_name,C_level FROM course,program_has_course WHERE FK_course_code = C_code and FK_program_ID = "+ id +" ;";

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


    public ArrayList<ArrayList<String>> selectTerm() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT  C_name, C_code, C_level ,Group_ID , C_include  FROM faculty_member_teach_course, course where FK_T_ID="+ null+";";

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

    public ArrayList<ArrayList<String>> selectCycleManagement() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM term where FK_Cycle_ID = "+null +";";

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
            System.out.println("@@@@@@@@@@@@@@@@@@@  FK_P_ID   "+FK_P_ID);
            System.out.println("@@@@@@@@@@@@@@@@@@@  FK_C_ID   "+FK_C_ID);
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1)+": "+rs.getString(2));
                System.out.println("@@@@@@@@@@@@@@@@@@@     "+data.get(++i));
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
            System.out.println("@@@@@@@@@@@@@@@@@@@  FK_program_ID   "+FK_program_ID);
            System.out.println("@@@@@@@@@@@@@@@@@@@  FK_T_ID   "+FK_T_ID);
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1)+": "+rs.getString(2));
                System.out.println("@@@@@@@@@@@@@@@@@@@     "+data.get(++i));
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
            System.out.println("@@@@@@@@@@@@@@@@@@@  id   "+id);
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
                System.out.println("@@@@@@@@@@@@@@@@@@@  id   "+id);
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
            System.out.println("@@@@@@@@@@@@@@@@@@@  code   "+code);
            //
            int i=-1;
            while (rs.next()){
                name=rs.getString(1);
                System.out.println("@@@@@@@@@@@@@@@@@@@     "+name);
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


    public ArrayList<ArrayList<String>> selectProgramManagementFig30(int id) throws ClassNotFoundException, SQLException {


        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT C_name,C_code,C_level FROM abetasdb.program_has_course, course Where FK_program_ID="+ id +" and FK_course_code=C_code;";

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




    public ArrayList<ArrayList<String>> selectFormativeStudentOutcome() throws ClassNotFoundException, SQLException {

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


    public ArrayList<ArrayList<String>> selectFormativeRubric() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> RsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> RowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM pi_rubric;";

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



    public ArrayList<ArrayList<String>> selectAllSuperusers() throws ClassNotFoundException, SQLException {

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
                rowDate.add(rs.getString(1));
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

    public ArrayList<String> selectSuperuser(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> rowDate = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM superuser where Super_ID = "+ id +";";

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


    public ArrayList<ArrayList<String>> selectAllFaculty() throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<String>> rsArr = new ArrayList<ArrayList<String>>();
        ArrayList<String> rowDate;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM abetasdb.faculty_member;";

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
                rowDate.add(rs.getString(5));
                rowDate.add(rs.getString(6));
                rowDate.add(rs.getString(7));
                rowDate.add(rs.getString(2));
                rowDate.add(rs.getString(4));
                rowDate.add("Faculty");
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


    public ArrayList<String> selectFaculty(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> rowDate = null;
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT * FROM faculty_member where Faculty_ID= "+id+";";

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
                System.out.println(id+"    dsgfdgdgs");
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
                System.out.println(name+"    dsgfdgdgs");
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

    public ArrayList<String> selectObjForLink(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Objective_label, Objective FROM abetasdb.program, abetasdb.p_objective where P_ID = FK_P_ID and FK_P_ID = "+ id +" ;";

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

    public ArrayList<String> selectWholeObj(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Objective_label, Objective FROM abetasdb.p_objective where Objective_label = "+ id +" ;";

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
            System.out.println("@@@@@@@@@@@@@@@@@@@  id   "+id);
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1)+": "+rs.getString(2));
                System.out.println("@@@@@@@@@@@@@@@@@@@     "+data.get(++i));
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

    public ArrayList<String> selectOutForLinkSingle(int id, int oid) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Outcome_label, Student_outcome FROM abetasdb.program, abetasdb.p_student_outcome where Outcome_label = "+oid+ " and P_ID = FK_P_ID and FK_P_ID = "+ id +" ;";

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
            System.out.println("@@@@@@@@@@@@@@@@@@@  id   "+id);
            //
            int i=-1;
            while (rs.next()){
                data.add(rs.getString(1)+": "+rs.getString(2));
                System.out.println("@@@@@@@@@@@@@@@@@@@     "+data.get(++i));
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

    public ArrayList<String> selectWholeOut(int id) throws ClassNotFoundException, SQLException {

        ArrayList<String> data = new ArrayList<String>();
        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "SELECT Outcome_label, Student_outcome FROM abetasdb.p_student_outcome where Outcome_label = "+ id +" ;";

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
                System.out.println(name+"    dsgfdgdgs");
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

    public int selectFormThreshold(int id) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<String> data = new ArrayList<String>();
        ResultSet rsSelect = null;
        int rs = 0;
        int name=-1;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String querySelect = " select Threshold from performance_indicator, link_out_pi where FK_pi_ID = PI_Label AND Link_ID= ?;";

            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setInt (1, id);

            rsSelect = preparedStatement.executeQuery();

            if (rsSelect.next()){
                //data.add((id= rsSelect.getInt(1))+"");
                //data.add(name = rsSelect.getString(1));
                name= rsSelect.getInt(1);
                //System.out.println(name+"    Sum_threshold");
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
                System.out.println(isExist+"    isTermYearExist");
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
                System.out.println(isExist+"    isStudentIDExist");
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
                System.out.println(isExist+"    isTermYearExist");
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
                System.out.println(isExist+"    isPILinkExist");
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
                System.out.println(isExist+"    isPILinkExist");
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
                System.out.println(isExist+"    isPIExist");
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
                System.out.println(isExist+"    isPIExist");
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
