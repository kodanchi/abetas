package passRestore;

import passReset.PassCodeMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Mohammed on 3/2/2016.
 */
public class CheckEmail {
    /**
     * selectEmail method will first check in the database if the email exist or not
     * if it is exist will generate random number to reset the password
     * @param loginDB used to connect to the database using loginDB class
     * @param email used to query the email that used to check if it is exist in the database or not
     * @return true if the email exist in the database otherwise it will return false
     * @throws SQLException if the connection to the database not successfully done it will be handled by the exception
     */

    public static Boolean selectEmail(loginDB loginDB, String email) throws ClassNotFoundException, SQLException {

        Random rand = new Random();

        String currentTime = new SimpleDateFormat("dd HH:mm").format(new Date());
         String selEmail ="";
/**
 * make the connection to the database
 * */
        loginDB.connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "select email from abetasdb.users where email= ?";

            /*
             *  Get connection from the DataSource
             */

            connection = loginDB.dataSource.getConnection();

            /**
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            rs = preparedStatement.executeQuery();

            //



            while (rs.next()){
                selEmail=rs.getString(1);



            }
            /**
             * if the hash map doesn't have the email as key and the email exist in the database
             * then initiate the random number and assign the email ass key in the hash map
             * and return true
             * otherwise if it is exist in the hash map then tell the user to check his/her email
             * lastly if the email is not exist in the database tell the user that the email is not exist in the database
             */
            if(PassCodeMap.checkKey(selEmail)==false && !"".equals(selEmail)){
    String randNumber= String.valueOf(rand.nextInt(100000));
    System.out.println("This is the random number "+randNumber);
    PassCodeMap.setPassCode(selEmail,randNumber);



    System.out.println("The code is from loginDB "+PassCodeMap.getpassKey(selEmail));
    System.out.println(true);
    return true;

}
          else  if(PassCodeMap.checkKey(selEmail)){

    System.out.println("Exist !!!!    " + selEmail);
      System.out.println("The code is "+PassCodeMap.getpassKey(selEmail));
    System.out.println(false);
    //System.out.println(PassCodeMap.obj);
    return false;

            }
            else if(selEmail==""){
System.out.print("The email is not exist");
                return false;
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


        }
        System.out.println("Wrong not entered");

        return false;
    }
}
