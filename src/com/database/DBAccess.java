package com.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Ibrahim Abuaqel on 1/19/2016.
 */
public class DBAccess {

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
            dataSource = (DataSource) envContext.lookup("jdbc/worldDB");

        }
        catch( Exception exe )
        {
            exe.printStackTrace();
        }


    }

    public ResultSet select(String sql) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            //String sql = "select * from city limit ?";

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, 10);

            rs = preparedStatement.executeQuery();

            //rs.close();

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

            return rs;

        }




        /*//stmt=conn.createStatement();
        //result=stmt.executeQuery("select * from university");
        String query = "select * from university";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        result=preparedStmt.executeQuery();

        while (result.next()){
            System.out.println(result.getString(1)+" "+result.getString(2));
        }
        conn.close();*/
    }

    public void insert(String sql) throws ClassNotFoundException, SQLException {

        connect();


        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, 10);

            rs = preparedStatement.executeQuery();

            rs.close();

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


        /*// the mysql insert statement
        String query = " insert into university (Uni_name, Uni_logo)" + " values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, name);
        preparedStmt.setString (2, logo);

        preparedStmt.execute();

        //or insert into university (first_name, last_name) values ('Fred', 'Flinstone');
        conn.close();*/
    }

    public void update(String sql) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //ResultSet rs = null;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, 10);

            preparedStatement.executeUpdate();

            //rs.close();

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

        /*String query = "update university set Uni_logo = ? where Uni_name = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, logo);
        preparedStmt.setString(2, name);

        preparedStmt.execute();
        //or update users set Uni_logo = "Changed" where Uni_name = a;
        conn.close();*/

    }

    public void delete(String sql) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //ResultSet rs = null;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, 10);

            preparedStatement.executeUpdate();

            //rs.close();

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



        /*String query = "delete from university where Uni_name = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, name);

        preparedStmt.execute();*/

    }
}
