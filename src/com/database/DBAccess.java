/*
package com.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

*/
/**
 * Created by Ibrahim Abuaqel on 1/24/2016.
 *//*

public class ASDB {
    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    DataSource dataSource = null;

    public void connect() throws ClassNotFoundException, SQLException {

        try
        {
            */
/*
             *  Using JDNI lookup get the DataSource.
             *//*


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

            String query = "select * from university";

            */
/*
             *  Get connection from the DataSource
             *//*


            connection = dataSource.getConnection();

            */
/*
             * Execute the query
             *//*

            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, 10);

            rs = preparedStatement.executeQuery();

            //rs.close();

            while (result.next()){
                System.out.println(result.getString(1)+" "+result.getString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            */
/*
             * finally block used to close resources
             *//*

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

    }

    public void insert(String sql) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;
        try {

            */
/*
             *  Get connection from the DataSource
             *//*


            connection = dataSource.getConnection();

            */
/*
             * Execute the query
             *//*

            String query = " insert into university (Uni_name, Uni_logo)" + " values (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, name);
            preparedStatement.setString (2, logo)
            //preparedStatement.setInt(1, 10);

            rs = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            */
/*
             * finally block used to close resources
             *//*

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

    public void update(String sql) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;
        try {

            */
/*
             *  Get connection from the DataSource
             *//*


            connection = dataSource.getConnection();

            */
/*
             * Execute the query
             *//*

            String query = "update university set Uni_logo = ? where Uni_name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, logo);
            preparedStatement.setString(2, name);

            rs = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            */
/*
             * finally block used to close resources
             *//*

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

    public void delete(String sql) throws ClassNotFoundException, SQLException {

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int rs = 0;
        try {

            */
/*
             *  Get connection from the DataSource
             *//*


            connection = dataSource.getConnection();

            */
/*
             * Execute the query
             *//*

            String query = "delete from university where Uni_name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            rs = preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            */
/*
             * finally block used to close resources
             *//*

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
*/
