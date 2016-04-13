package ASDB;

import login.Password;
import passReset.PassCodeMap;
import passReset.SendEmail;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class U_AS_Insert {

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
     * addUser method used to add user
     * @param type is the type of the user
     * @param Uname is the username
     * @param email is the email
     * @param Fname is the first name
     * @param Mname is the middle name
     * @param Lname is the last name
     * @param E_program is the evaluator program
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */
    public void addUser(int type, String Uname, String email, String Fname, String Mname, String Lname, String E_program) throws ClassNotFoundException, SQLException {


        connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {


            SessionIdentifierGenerator randomPassword = new SessionIdentifierGenerator();

            String userPass = randomPassword.nextSessionId().substring(0, 4) +
                    "abetas"+ randomPassword.nextSessionId().substring(0, 4);

            /*
                 *  Get connection from the DataSource
                 */

            connection = dataSource.getConnection();

                /*
                 * Execute the query
                 */

            int rs = 0;
            if (type == 0) {



                    String query = " insert into superuser (Super_Username, Super_Email, Super_Fname, Super_Mname, Super_Lname," +
                            " Adm_ID, Super_Password)" + " values (?, ?, ?, ?, ?, ?, ?)";

                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, Uname);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, Fname);
                    preparedStatement.setString(4, Mname);
                    preparedStatement.setString(5, Lname);
                    preparedStatement.setInt(6, 0);
                    preparedStatement.setString(7, Password.getSaltedHash(userPass));
                    rs = preparedStatement.executeUpdate();



                    ////Need to display the temp password to the screen

                SendEmail msg = new SendEmail();
                String emailMsg = String.format("Hello Mr.%s,\n" +
                        "We would welcome you to ABETAS (ABET Automation System).\n" +
                        "Here it is your login details: \n" +
                        "Username: \"%s\"\n" +
                        "Password: \"%s\"\n" +
                        "We strongly recommend you to change your password after you log-in for the first time," +
                        " you can do that by going to settings page and change your password.\n" +
                        "\n" +
                        "Thank you,\n" +
                        "ABETS Management.",Fname,Uname,userPass);

                msg.sendMsg(emailMsg,"Welcome to ABETAS",email);



            }else if (type==1){

                    String query = " insert into faculty_member (Faculty_Username, Faculty_Email, Faculty_Fname, Faculty_Mname," +
                            " Faculty_Lname, Faculty_Password)" + " values (?, ?, ?, ?, ?, ?)";

                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, Uname);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, Fname);
                    preparedStatement.setString(4, Mname);
                    preparedStatement.setString(5, Lname);
                    preparedStatement.setString(6, Password.getSaltedHash(userPass));
                    rs = preparedStatement.executeUpdate();


                    ////Need to display the temp password to the screen


                SendEmail msg = new SendEmail();
                String emailMsg = String.format("Hello Mr.%s,\n" +
                        "We would welcome you to ABETAS (ABET Automation System).\n" +
                        "Here it is your login details: \n" +
                        "Username: \"%s\"\n" +
                        "Password: \"%s\"\n" +
                        "We strongly recommend you to change your password after you log-in for the first time," +
                        " you can do that by going to settings page and change your password.\n" +
                        "\n" +
                        "Thank you,\n" +
                        "ABETS Management.",Fname,Uname,userPass);

                msg.sendMsg(emailMsg,"Welcome to ABETAS",email);


            }else if(type==2){

                String calPass = Fname.substring(0,3)+Lname.substring(0,3)+"123";

                /*
                 * Execute the query
                 */
                    String query = " insert into evaluator (E_Username, E_Fname, E_Mname, E_Lname, E_Password, E_program)" +
                            " values (?, ?, ?, ?, ?, ?)";
                System.out.println("E_program         "+E_program);
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, Uname);
                    preparedStatement.setString(2, Fname);
                    preparedStatement.setString(3, Mname);
                    preparedStatement.setString(4, Lname);
                    preparedStatement.setString(5, Password.getSaltedHash(calPass));
                    preparedStatement.setString(6, E_program);
                    rs = preparedStatement.executeUpdate();
System.out.print("E_program         "+E_program);

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
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
     * addTerm method used to add term
     * @param name is the term name
     * @param year is the term year
     * @param C_ID  is the cycle ID
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public void addTerm(String name, String year, String C_ID) throws ClassNotFoundException, SQLException {

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
            String query = " insert into term (T_name, T_year, FK_Cycle_ID)" + " values (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, year);
            preparedStatement.setString(3, C_ID);
            rs = preparedStatement.executeUpdate();


            ////Need to display the temp password to the screen


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
     * audit method used to monitor the system
     * @param userName is the username
     * @param action is the action that the user made
     * @param time the time of action
     * @throws SQLException once the connection to the database aborted or wrong query occurred
     */

    public void audit(String userName, String action, String time) throws ClassNotFoundException, SQLException {

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
            String query = " insert into auditing (userName, action, time) values (?,?,?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, action);
            preparedStatement.setString(3, time);

            rs = preparedStatement.executeUpdate();

            ////Need to display the temp password to the screen

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
