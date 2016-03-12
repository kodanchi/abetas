package passReset;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Mohammed on 1/26/2016.
 */
@WebServlet(name = "passcode",
        urlPatterns = {"/Passcode"})
public class passCode extends HttpServlet {
    /**
     *
     @param request used to get the servler request from the uri which has the user data
      * @param response used to respone to the servlet site either by redirect the site to another url or show some output to the user
     * @throws ServletException used to handle if the error occurs from uri (web server)
     * @throws IOException used to handle any inpout/output operation in dealing with windows operations
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#######################################################");
        /**
         * getFromUser2 and getFromUser3 will take the email of the user and the passcode that he/she received
         * after that it will check in checkPasscode if the passcode matched or not
         */
        String getFromUser2=request.getParameter("emailReset");
        String getFromUser3= request.getParameter("emailPassCode");


        try {
            checkPassCode(getFromUser2,getFromUser3,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //response.
    }

    /**
     *
     * @param d  will take the email to check if any request occurs before for reset password
     * @param n will take the passcode that the user entered
     * @param r used to response to the servlet site either by redirect the site to another url or show some output to the user
     * @throws ClassNotFoundException
     * @throws  IOException used to handle any inpout/output operation in dealing with windows operations
     */
    public void checkPassCode(String d, String n, HttpServletResponse r) throws ClassNotFoundException, SQLException, IOException {

        PrintWriter out = r.getWriter();

        /**
         * It will check whether the email exist in the hash map or not
         * after that it will compare the passcode that the user entered with the one which is in the hash map
         * if both matches it will redirect the user to jsp page that request to enter the new password
         * otherwise it will alert the user with warning message
         */
        if (PassCodeMap.checkKey(d)) {


                if (Integer.parseInt(n) == Integer.parseInt(PassCodeMap.getpassKey(d))) {

                    // out.print("Correct  " +d +n);
                    out.print("<html>\n" +
                            "\n" +
                            "<head>\n" +
                            "    <meta charset=\"utf-8\">\n" +
                            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                            "    <title>ABETAS</title>\n" +
                            "    <meta name=\"description\" content=\"An interactive getting started guide for Brackets.\">\n" +
                            "\n" +
                            "    <link rel=\"stylesheet\" href=\"css/loginPage.css\">\n" +
                            "    <link rel=\"stylesheet\" href=\"css/bootstrap.css\">\n" +
                            "    <link rel=\"stylesheet\" href=\"css/bootstrap.css\">\n" +
                            "\n" +
                            "\n" +
                            "</head>\n" +
                            "\n" +
                            "<body>\n" +
                            "<div class=\"row\">\n" +
                            "    <div class=\"col-md-6 col-xs-6 col-md-offset-3 well base\">\n" +
                            "        <h1>ABETAS</h1>\n" +
                            "        <p>Enter the new password two times please.</p>\n" +
                            "\n" +
                            "        <form>\n" +
                            "            <div class=\"form-group\">\n" +
                            "                <input type=\"password\" class=\"form-control\" name=\"newPassword\" placeholder=\"New password\" size=\"25\">\n" +
                            "                <input type=\"password\" class=\"form-control\" name=\"newPasswordconfirm\" placeholder=\"Re-enter new password\" size=\"25\">\n" +
                            "\n" +
                            "            </div>\n" +
                            "            <button type=\"submit\" class=\"btn btn-warning\">Cancel</button>\n" +
                            "            <button type=\"submit\" class=\"btn btn-success\">Login</button>\n" +
                            "        </form>\n" +
                            "\n" +
                            "\n" +
                            "    </div>\n" +
                            "\n" +
                            "\n" +
                            "</div>\n" +
                            "\n" +
                            "\n" +
                            "</body>\n" +
                            "\n" +
                            "</html>");
                    System.out.println("Done");
                }
                // r.sendRedirect("newPassword.jsp?code="+d+"&email="+n);
                else {
                    out.print("<script language=\"javaScript\">\n" +


                            "  alert('Wrong  passcode');\n" +

                            "</script>\n");


                }


    }
        else{
            out.print("<script language=\"javaScript\">\n" +


                    "  alert('Wrong email or passcode');\n" +

                    "</script>\n");
            System.out.println(" Email or passcode !!!!!   "+d);

        }



    }



}
