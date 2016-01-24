package com.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ibrahim Abuaqel on 1/24/2016.
 * source : http://tomcat.apache.org/tomcat-6.0-doc/jndi-datasource-examples-howto.html#MySQL_DBCP_Example
 */
public class ASDB {
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

    public void initialization(String Uname, String Cname, String logo, String Fname, String Mname, String Lname, String Username, String password, String email) throws ClassNotFoundException, SQLException {

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
            String query = " insert into university (Uni_name, College_name, Uni_logo)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, Uname);
            preparedStatement.setString (2, Cname);
            preparedStatement.setString (3, logo);

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

        connect();

        connection = null;
        preparedStatement = null;

        rs = 0;
        try {

            /*
             *  Get connection from the DataSource
             */

            connection = dataSource.getConnection();

            /*
             * Execute the query
             */
            String query = " insert into superuser (Super_Username, Super_Password, Super_Email, Super_Fname, Super_Mname, Super_Lname, Adm_ID)" + " values (?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString (1, Username);
            preparedStatement.setString (2, password);
            preparedStatement.setString (3, email);
            preparedStatement.setString (4, Fname);
            preparedStatement.setString (5, Mname);
            preparedStatement.setString (6, Lname);
            preparedStatement.setInt (7, 1);
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

    public void addUser(String sql) throws ClassNotFoundException, SQLException {

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
            String query = "update university set Uni_logo = ? where Uni_name = ?";
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString(1, logo);
           // preparedStatement.setString(2, name);

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

    public void delete(String sql) throws ClassNotFoundException, SQLException {

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
            String query = "delete from university where Uni_name = ?";
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString(1, name);

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
