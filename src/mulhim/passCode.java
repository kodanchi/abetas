package mulhim;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Mohammed on 1/26/2016.
 */
@WebServlet(name = "passcode",
        urlPatterns = {"/Passcode"})
public class passCode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#######################################################");
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
    public void checkPassCode(String d, String n, HttpServletResponse r) throws ClassNotFoundException, SQLException, IOException {
        PrintWriter out=r.getWriter();
       //System.out.println(d);
        //System.out.println(n);
        System.out.println("Hello from checkPassCode");
        System.out.println("The size is "+PassCodeMap.getMapSize());
        System.out.println(d);
        //System.out.println(n);
        //DBAccess co =new DBAccess();
  //  System.out.println(PassCodeMap.getpassKey(d));

        if(PassCodeMap.checkKey(d)){
            System.out.println("This is from the first if condition");
Integer.parseInt(PassCodeMap.in.get(0));
if(Integer.parseInt(n)==Integer.parseInt(PassCodeMap.in.get(0))) {

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
        else {
            out.print("<script language=\"javaScript\">\n" +


                    "  alert('Wrong email or passcode');\n" +

                    "</script>\n");
            System.out.println("Wrong !!!!!   "+d);

        }



    }



}
