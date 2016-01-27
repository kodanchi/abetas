package mulhim;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
            checkPassCode(getFromUser2,getFromUser3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void checkPassCode(String d,int n) throws ClassNotFoundException, SQLException {

       //System.out.println(d);
        //System.out.println(n);
        System.out.println("Hello from checkPassCode");
        System.out.println("The size is "+PassCodeMap.getMapSize());
        //System.out.println(d);
        //System.out.println(n);
        //DBAccess co =new DBAccess();
  //  System.out.println(PassCodeMap.getpassKey(d));
//        if(PassCodeMap.checkKey(d)){
//
//            System.out.println("Correct" +d);
//        }
//        else {
//            System.out.println("Wrong !!!!!   "+d);
//           // PassCodeMap.setPassCode(d.trim(),n);
//        }



    }



}
