package FDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;


public class F_Delete {

    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    DataSource dataSource = null;

    /**
     * Function that provides database connection using connection pool for multi access usage.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void connect() throws ClassNotFoundException, SQLException {

        try {
            /*
             *  Using JDNI lookup get the DataSource.
             */

            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/abetasDB");

        } catch (Exception exe) {
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
     * Connect to the database and perform delete sql to delete Summative rubrics by ID.
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteSummRub(int id) throws ClassNotFoundException, SQLException {

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
            String query = "delete from summative_rubric where FK_Summative_ID = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            //preparedStatement.setInt(2, id);

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