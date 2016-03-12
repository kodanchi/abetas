package passRestore;

import passReset.PassCodeMap;
import passReset.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * Created by Mohammed on 1/21/2016.
 */
@WebServlet(name = "reset",
        urlPatterns = {"/Reset"})
public class resetPassword extends HttpServlet {
    Scanner in = new Scanner(System.in);

    /**
     *
     *
     * @param request used to get the servler request from the uri which has the user data
     * @param response used to respone to the servlet site either by redirect the site to another url or show some output to the user
     * @throws ServletException used to handle if the error occurs from uri (web server)
     * @throws IOException used to handle any inpout/output operation in dealing with windows operations
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#######################################################");
/**
 * String getFromUser will Get the email that the user entered to reset the password
 */
        String getFromUser=request.getParameter("exampleInputEmail1");

        loginDB d = new loginDB();


        PrintWriter out=response.getWriter();
        try {
            /**
             * if the user has iniate the reset password operation correctly then send the passcode to his/her email
             * and then redirect him/her to enterpasscode jsp page to enter the code that he/she recieved
             * through the email
             * otherwise message will appear to the user to tell him/her the email doesn't exist
             */
            if(CheckEmail.selectEmail(d, getFromUser)){
                SendEmail msg = new SendEmail();

                msg.mu("The code is  "+ PassCodeMap.getpassKey(getFromUser),getFromUser);
                response.sendRedirect("enterPasscode.jsp");



            }
            else{

                out.print("false");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            out.print("false");

        }

    }


}
