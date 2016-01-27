package mulhim;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
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
        int getFromUser3= Integer.parseInt(request.getParameter("emailPassCode"));


        try {
            checkPassCode(getFromUser2,getFromUser3,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //response.
    }
    public void checkPassCode(String d, int n, HttpServletResponse r) throws ClassNotFoundException, SQLException, IOException {

       //System.out.println(d);
        //System.out.println(n);
        System.out.println("Hello from checkPassCode");
        System.out.println("The size is "+PassCodeMap.getMapSize());
        //System.out.println(d);
        //System.out.println(n);
        //DBAccess co =new DBAccess();
  //  System.out.println(PassCodeMap.getpassKey(d));

        if(PassCodeMap.checkKey(d)){
if(PassCodeMap.getpassKey(d)==n)
            System.out.println("Correct  " +d +n);
System.out.print("");
            r.sendRedirect("newPassword.jsp?code="+d+"&email="+n);
        }
        else {
            System.out.println("Wrong !!!!!   "+d);
           // PassCodeMap.setPassCode(d.trim(),n);
        }



    }



}
