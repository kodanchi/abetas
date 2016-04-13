package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;


public class C_AS_Update {

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
     * updateSection method is used to update section
     * @param Section_ID is the section ID
     * @param FK_F is the faculty member
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updateSection(int Section_ID, int FK_F) throws ClassNotFoundException, SQLException {

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
            String query = "update abetasdb.section set FK_F = ? where Section_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt (1, FK_F);
            preparedStatement.setInt (2, Section_ID);

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
     * updateStudent method is used to update the student
     * @param Student_ID is the updated student ID
     * @param Student_Name is the student name
     * @param S_ID is the old student ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updateStudent(String Student_ID, String Student_Name, int S_ID) throws ClassNotFoundException, SQLException {

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
            String query = "update abetasdb.students set Student_ID = ? , Student_Name = ? where S_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, Student_ID);
            preparedStatement.setString (2, Student_Name);
            preparedStatement.setInt (3, S_ID);

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
     * updatePI method used to update the performance indicator
     * @param name is the name of performance indicator
     * @param Threshold is the percentage of threshold
     * @param id is the performance indicator ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */


    public void updatePI(String name,double Threshold, int id) throws ClassNotFoundException, SQLException {

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
            String query = "update performance_indicator set PI_name = ?, Threshold = ? where PI_Label = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, name);
            preparedStatement.setDouble (2, Threshold);
            preparedStatement.setInt (3, id);

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
     * updateDefault method used to update the default term
     * @param T_ID the term ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updateDefault(int T_ID) throws ClassNotFoundException, SQLException {

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

            String query = "update term set term.current = 0";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeUpdate();


            String query2 = "update term set term.current = 1 where T_ID = ?";
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt (1, T_ID);
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
     * includeCourse method is used to update the courses that included in the term
     * @param code is the course code
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void includeCourse(String code) throws ClassNotFoundException, SQLException {

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
            String query = "update course set C_include = 1 where C_code = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, code);

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
     * updateRubric method used to update the rubric descriptions
     * @param A is the performance indicator rubric description 1
     * @param B is the performance indicator rubric description 2
     * @param C is the performance indicator rubric description 3
     * @param D is the performance indicator rubric description 4
     * @param id is the rubric ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updateRubrics(String A,String B,String C,String D,int id) throws ClassNotFoundException, SQLException {

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
            String query = "update pi_rubric set PI_rubric_description_1 = ?, PI_rubric_description_2 = ?, PI_rubric_description_3 = ?, PI_rubric_description_4 = ? where PI_rubric_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, A);
            preparedStatement.setString (2, B);
            preparedStatement.setString (3, C);
            preparedStatement.setString (4, D);
            preparedStatement.setInt (5, id);

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
     * updatePILink used to update the performance indicator link
     * @param Link_ID is the link ID
     * @param FK_out is the foreign key outcome
     * @param FK_pi_ID is the foreign key performance indicator ID
     * @param FK_P_ID is the foreign key program ID
     * @param FK_R_ID is the foreign key rubric ID
     * @param FK_C_ID is the foreign key cycle ID
     * @param FK_T_ID is the foreign key term ID
     * @param LinkType is the link type (formative/summative)
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updatePILink(int Link_ID,int FK_out, int FK_pi_ID, int FK_P_ID, int FK_R_ID, String FK_C_ID, int FK_T_ID, String LinkType) throws ClassNotFoundException, SQLException {

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
            String query = "update link_out_pi set FK_out = ?, FK_pi_ID=?, FK_P_ID=?, FK_R_ID=?, FK_C_ID=?, FK_T_ID=?, LinkType=? where Link_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt (1, FK_out);
            preparedStatement.setInt (2, FK_pi_ID);
            preparedStatement.setInt (3, FK_P_ID);
            preparedStatement.setInt (4, FK_R_ID);
            preparedStatement.setString (5, FK_C_ID);
            preparedStatement.setInt (6, FK_T_ID);
            preparedStatement.setString (7, LinkType);
            preparedStatement.setInt (8, Link_ID);

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





}
