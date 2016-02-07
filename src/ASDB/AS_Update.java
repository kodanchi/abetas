package ASDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Ibrahim Abuaqel on 2/4/2016.
 */
public class AS_Update {

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