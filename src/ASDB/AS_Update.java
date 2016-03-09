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


    public void updateStudent(long Student_ID, String Student_Name, int S_ID) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();
                System.out.println("Student_ID is : "+ Student_ID);
            /*
             * Execute the query
             */
            String query = "update abetasdb.students set Student_ID = ? , Student_Name = ? where S_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong (1, Student_ID);
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

    public void updatePI(String name, int id) throws ClassNotFoundException, SQLException {

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
            String query = "update performance_indicator set PI_name = ? where PI_Label = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, name);
            preparedStatement.setInt (2, id);

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
            System.out.println("dfgsvfhdjsbghjfdjbhjsfdbjhgbjhfbghsbvjbhjsvbvbbfvbvfbhbfdbvbfd");

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
            System.out.println("dfgsvfhdjsbghjfdjbhjsfdbjhgbjhfbghsbvjbhjsvbvbbfvbvfbhbfdbvbfd     code"+code);
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
            System.out.println("dfgsvfhdjsbghjfdjbhjsfdbjhgbjhfbghsbvjbhjsvbvbbfvbvfbhbfdbvbfd     id "+id);
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

    public void updateRubricNames(String A,String B,String C,String D, int id) throws ClassNotFoundException, SQLException {

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
            System.out.println("dfgsvfhdjsbghjfdjbhjsfdbjhgbjhfbghsbvjbhjsvbvbbfvbvfbhbfdbvbfd     id "+id);
            String query = "update pi_rubric set PI_rubric_name_1 = ?, PI_rubric_name_2 = ?, PI_rubric_name_3 = ?, PI_rubric_name_4 = ? where PI_rubric_ID = ?";

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
            System.out.println("dfgsvfhdjsbghjfdjbhjsfdbjhgbjhfbghsbvjbhjsvbvbbfvbvfbhbfdbvbfd");
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


    public void updateFormThredshold( int Link_ID, int Sum_threshold) throws ClassNotFoundException, SQLException {

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
            String query = "update summative set Sum_threshold = ? where FK_Link_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt (1, Sum_threshold);
            preparedStatement.setInt (2, Link_ID);


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

    public void updateSummativeFormSection( int Link_ID, int FK_Section_ID) throws ClassNotFoundException, SQLException {

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
            String query = "update summative set FK_Section_ID = ? where FK_Link_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt (1, FK_Section_ID);
            preparedStatement.setInt (2, Link_ID);


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

    public void updateFormmativeFormSection( int Link_ID, int FK_Section_ID) throws ClassNotFoundException, SQLException {

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
            String query = "update formative set FK_Section_ID = ? where FK_Link_ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt (1, FK_Section_ID);
            preparedStatement.setInt (2, Link_ID);


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
