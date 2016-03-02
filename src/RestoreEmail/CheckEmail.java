package RestoreEmail;

import RestoreEmail.loginDB;
import mulhim.PassCodeMap;

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
    public static Boolean selectEmail(loginDB loginDB, String d) throws ClassNotFoundException, SQLException {
        Random rand = new Random();

        String currentTime = new SimpleDateFormat("dd HH:mm").format(new Date());
         String selEmail ="";

        loginDB.connect();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet rs = null;
        try {


            String query = "select Super_Email from superuser where Super_Email= ?";

            /*
             *  Get connection from the DataSource
             */

            connection = loginDB.dataSource.getConnection();

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, d);

            rs = preparedStatement.executeQuery();

            //



            while (rs.next()){
                selEmail=rs.getString(1);
                System.out.println(rs.getString(1));


            }
if(PassCodeMap.checkKey(selEmail)==false && !"".equals(selEmail)){
    String randNumber= String.valueOf(rand.nextInt(100000));
    System.out.println("This is the random number "+randNumber);
    PassCodeMap.setPassCode(selEmail,randNumber);


    System.out.println(PassCodeMap.getMapSize());
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
