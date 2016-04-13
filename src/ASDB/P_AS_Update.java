package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;


public class P_AS_Update {

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
     * updateObjective method used to update objective
     * @param Objective_label is the objective ID
     * @param Objective is the objective name
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updateObjective(int Objective_label, String Objective) throws ClassNotFoundException, SQLException {

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

            String query = "update p_objective set Objective = ? where Objective_label = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, Objective);
            preparedStatement.setInt (2, Objective_label);

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
     * updateOutcome method used to update student outcome
     * @param Outcome_label is the outcome ID
     * @param Student_outcome is the student outcome
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updateOutcome(int Outcome_label, String Student_outcome) throws ClassNotFoundException, SQLException {

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

            String query = "update p_student_outcome set Student_outcome = ? where Outcome_label = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, Student_outcome);
            preparedStatement.setInt (2, Outcome_label);

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
     * updateLinkObj_Out method used update the link objective outcome
     * @param Link_ID is the student ID
     * @param FK_obj is the foreign key objective
     * @param FK_out is the foreign key student outcome
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updateLinkObj_Out(int Link_ID,int FK_obj, int FK_out) throws ClassNotFoundException, SQLException {

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
            System.out.println("dfgsvfhdjsbghjfdjbhjsfdbjhgbjhfbghsbvjbhjsvbvbbfvbvfbhbfdbvbfd");
            String query = "update link_out_obj set FK_obj = ?, FK_out = ? where Link_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt (1, FK_obj);
            preparedStatement.setInt (2, FK_out);
            preparedStatement.setInt (3, Link_ID);

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
     * updateCourse method used to update the course
     * @param Cid is the cycle ID
     * @param C_code is the cycle code
     * @param C_name is the cycle name
     * @param C_level is the cycle level
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updateCourse(int Cid, String C_code,String C_name, int C_level) throws ClassNotFoundException, SQLException {

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
            System.out.println("dfgsvfhdjsbghjfdjbhjsfdbjhgbjhfbghsbvjbhjsvbvbbfvbvfbhbfdbvbfd");
            String query = "update course set C_code = ?, C_name = ?, C_level = ? where C_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, C_code);
            preparedStatement.setString (2, C_name);
            preparedStatement.setInt (3, C_level);
            preparedStatement.setInt (4, Cid);

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
     * updateProgram method used update the program
     * @param P_ID is the program ID
     * @param P_name is the program name
     * @param P_mission is the program name
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void updateProgram(int P_ID, String P_name,String P_mission) throws ClassNotFoundException, SQLException {

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
            System.out.println("dfgsvfhdjsbghjfdjbhjsfdbjhgbjhfbghsbvjbhjsvbvbbfvbvfbhbfdbvbfd");
            String query = "update program set P_name = ?, P_mission = ? where P_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, P_name);
            preparedStatement.setString (2, P_mission);
            preparedStatement.setInt (3, P_ID);

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
