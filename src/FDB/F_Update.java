package FDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;


public class F_Update {


    DataSource dataSource = null;

    /**
     * Function that provides database connection using connection pool for multi access usage.
     *
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
     * Connect to the database and perform update sql to update course in term using course code.
     * @param code
     * @throws ClassNotFoundException
     * @throws SQLException
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
     * Connect to the database and perform update sql to update formative written rubrics, instructor_feedback_comment, instructor_feedback_obstacle, instructor_feedback_improvement, F_evidence using form ID.
     * @param F_written_rubic
     * @param F_instructor_feedback_comment
     * @param F_instructor_feedback_obstacle
     * @param F_instructor_feedback_improvement
     * @param F_evidence
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateFormF(String F_written_rubic, String F_instructor_feedback_comment, String F_instructor_feedback_obstacle, String F_instructor_feedback_improvement, String F_evidence,  int id) throws ClassNotFoundException, SQLException {

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

            if (F_evidence!=null) {
                String query = "update formative set F_written_rubic = ?, F_instructor_feedback_comment = ?, F_instructor_feedback_obstacle = ?, F_instructor_feedback_improvement = ?, F_evidence = ? where Formative_ID = ?";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, F_written_rubic);
                preparedStatement.setString(2, F_instructor_feedback_comment);
                preparedStatement.setString(3, F_instructor_feedback_obstacle);
                preparedStatement.setString(4, F_instructor_feedback_improvement);
                preparedStatement.setString(5, F_evidence);
                preparedStatement.setInt(6, id);

                rs = preparedStatement.executeUpdate();
            }
            else{
                String query = "update formative set F_written_rubic = ?, F_instructor_feedback_comment = ?, F_instructor_feedback_obstacle = ?, F_instructor_feedback_improvement = ? where Formative_ID = ?";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, F_written_rubic);
                preparedStatement.setString(2, F_instructor_feedback_comment);
                preparedStatement.setString(3, F_instructor_feedback_obstacle);
                preparedStatement.setString(4, F_instructor_feedback_improvement);
                preparedStatement.setInt(5, id);

                rs = preparedStatement.executeUpdate();
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

    /**
     * Connect to the database and perform update sql to update Formative form as submitted with a date using Formative ID.
     * @param id
     * @param date
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateSubmitFormF(int id, String date) throws ClassNotFoundException, SQLException {

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

            String query = "update formative set F_submitted = 1, F_date = ? where Formative_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, id);

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
     * Connect to the database and perform update sql to update Summative form submission with date using Summative ID.
     * @param id
     * @param date
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateSubmitFormS(int id, String date) throws ClassNotFoundException, SQLException {

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

            String query = "update summative set Sum_submitted = 1, Sum_date = ? where Summative_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, id);


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
     * Connect to the database and perform update sql to update Summative form with evidence using Formative ID.
     * @param Sum_evidence
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateFormS(String Sum_evidence,  int id) throws ClassNotFoundException, SQLException {

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

            if (Sum_evidence!=null) {

                String query = "update summative set Sum_evidence = ? where Summative_ID = ?";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, Sum_evidence);
                preparedStatement.setInt(2, id);

                rs = preparedStatement.executeUpdate();
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

}
