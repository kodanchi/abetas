package mulhim;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import mulhim.PassCodeMap;
/**
 * Created by Ibrahim Abuaqel on 1/24/2016.
 * source : http://tomcat.apache.org/tomcat-6.0-doc/jndi-datasource-examples-howto.html#MySQL_DBCP_Example
 */
public class loginDB {
    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    DataSource dataSource = null;
    Random rand = new Random();
    int randNumber=randNumber=rand.nextInt(100000);

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


    public String selectEmail(String d) throws ClassNotFoundException, SQLException {

        String selEmail ="";

        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {

            String query = "select Uni_name from university where Uni_logo=\""+d+"\"";

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
                selEmail=rs.getString(1);
                System.out.println(rs.getString(1));


            }
            if(PassCodeMap.checkKey(selEmail)){

                System.out.println("Exist !!!!");
                System.out.println("The code is "+PassCodeMap.getpassKey(selEmail));
            }
            else {
                PassCodeMap.setPassCode(selEmail,randNumber);
                System.out.println(PassCodeMap.getMapSize());
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

             return selEmail;

        }

    }

//    public void initialization(String Uname, String Cname, String logo, String Fname, String Mname, String Lname, String Username, String password, String email) throws ClassNotFoundException, SQLException {
//
//        connect();
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        int rs = 0;
//        try {
//
//            /*
//             *  Get connection from the DataSource
//             */
//
//            connection = dataSource.getConnection();
//
//            /*
//             * Execute the query
//             */
//            String query = " insert into university (Uni_name, College_name, Uni_logo)" + " values (?, ?, ?)";
//
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString (1, Uname);
//            preparedStatement.setString (2, Cname);
//            preparedStatement.setString (3, logo);
//
//            rs = preparedStatement.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            /*
//             * finally block used to close resources
//             */
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//
//        }
//
//        connect();
//
//        connection = null;
//        preparedStatement = null;
//
//        rs = 0;
//        try {
//
//            /*
//             *  Get connection from the DataSource
//             */
//
//            connection = dataSource.getConnection();
//
//            /*
//             * Execute the query
//             */
//            String query = " insert into superuser (Super_Username, Super_Password, Super_Email, Super_Fname, Super_Mname, Super_Lname, Adm_ID)" + " values (?, ?, ?, ?, ?, ?, ?)";
//
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString (1, Username);
//            preparedStatement.setString (2, password);
//            preparedStatement.setString (3, email);
//            preparedStatement.setString (4, Fname);
//            preparedStatement.setString (5, Mname);
//            preparedStatement.setString (6, Lname);
//            preparedStatement.setInt (7, 1);
//            rs = preparedStatement.executeUpdate();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            /*
//             * finally block used to close resources
//             */
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//
//        }
//
//    }
//
//    public void addUser(int type, String Uname, String email, String Fname, String Mname, String Lname) throws ClassNotFoundException, SQLException {
//
//        connect();
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
////        SessionIdentifierGenerator randomPassword = new SessionIdentifierGenerator();
////        String rn = randomPassword.nextSessionId().substring(0, 8);
//
//        int rs = 0;
//        if (type == 0) {
//        try {
//
//            /*
//             *  Get connection from the DataSource
//             */
//
//            connection = dataSource.getConnection();
//
//            /*
//             * Execute the query
//             */
//                String query = " insert into superuser (Super_Username, Super_Email, Super_Fname, Super_Mname, Super_Lname, Adm_ID, Super_Password)" + " values (?, ?, ?, ?, ?, ?, ?)";
//
//                preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1, Uname);
//                preparedStatement.setString(2, email);
//                preparedStatement.setString(3, Fname);
//                preparedStatement.setString(4, Mname);
//                preparedStatement.setString(5, Lname);
//                preparedStatement.setInt(6, 0);
//               // preparedStatement.setString(7, rn);
//                rs = preparedStatement.executeUpdate();
//
//
//
//
//
//
//
//
//            ////Need to display the temp password to the screen
//
//
//
//
//
//
//
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }finally{
//            /*
//             * finally block used to close resources
//             */
//                try {
//                    if (preparedStatement != null) {
//                        preparedStatement.close();
//                    }
//                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
//                }
//                try {
//                    if (connection != null) {
//                        connection.close();
//                    }
//                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
//                }
//
//            }
//        }else if (type==1){
//            try {
//
//            /*
//             *  Get connection from the DataSource
//             */
//
//                connection = dataSource.getConnection();
//
//            /*
//             * Execute the query
//             */
//                String query = " insert into faculty_member (Faculty_Username, Faculty_Email, Faculty_Fname, Faculty_Mname, Faculty_Lname, Faclty_Password)" + " values (?, ?, ?, ?, ?, ?)";
//
//                preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1, Uname);
//                preparedStatement.setString(2, email);
//                preparedStatement.setString(3, Fname);
//                preparedStatement.setString(4, Mname);
//                preparedStatement.setString(5, Lname);
//                preparedStatement.setString(6, rn);
//                rs = preparedStatement.executeUpdate();
//
//
//
//
//
//
//
//
//                ////Need to display the temp password to the screen
//
//
//
//
//
//
//
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }finally{
//            /*
//             * finally block used to close resources
//             */
//                try {
//                    if (preparedStatement != null) {
//                        preparedStatement.close();
//                    }
//                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
//                }
//                try {
//                    if (connection != null) {
//                        connection.close();
//                    }
//                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
//                }
//
//            }
//        }else if(type==2){
//            try {
//
//            /*
//             *  Get connection from the DataSource
//             */
//
//                connection = dataSource.getConnection();
//
//            /*
//             * Execute the query
//             */
//                String query = " insert into evaluator (E_Username, E_Fname, E_Mname, E_Lname, E_Password)" + " values (?, ?, ?, ?, ?)";
//
//                preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1, Uname);
//                preparedStatement.setString(2, Fname);
//                preparedStatement.setString(3, Mname);
//                preparedStatement.setString(4, Lname);
//                preparedStatement.setString(5, rn);
//                rs = preparedStatement.executeUpdate();
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }finally{
//            /*
//             * finally block used to close resources
//             */
//                try {
//                    if (preparedStatement != null) {
//                        preparedStatement.close();
//                    }
//                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
//                }
//                try {
//                    if (connection != null) {
//                        connection.close();
//                    }
//                } catch (SQLException sqlException) {
//                    sqlException.printStackTrace();
//                }
//
//            }
//        }
//    }
//
//    public void delete(String sql) throws ClassNotFoundException, SQLException {
//
//        connect();
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        int rs = 0;
//        try {
//
//            /*
//             *  Get connection from the DataSource
//             */
//
//            connection = dataSource.getConnection();
//
//            /*
//             * Execute the query
//             */
//            String query = "delete from university where Uni_name = ?";
//            preparedStatement = connection.prepareStatement(query);
//            //preparedStatement.setString(1, name);
//
//            rs = preparedStatement.executeUpdate();
//
//
//
//
//
//
//
//
//            ////Need to display the temp password to the screen
//
//
//
//
//
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            /*
//             * finally block used to close resources
//             */
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//
//        }
//
//    }
}
