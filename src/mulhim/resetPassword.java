package mulhim;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
/**
 * Created by Mohammed on 1/21/2016.
 */
@WebServlet(name = "reset",
        urlPatterns = {"/Reset"})
public class resetPassword extends HttpServlet {
    Scanner in = new Scanner(System.in);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#######################################################");

        String getFromUser=request.getParameter("exampleInputEmail1");

        loginDB d = new loginDB();


        PrintWriter out=response.getWriter();
        try {
            if(d.selectEmail(getFromUser)){
                SendEmail msg = new SendEmail();

                msg.mu("The code is  "+PassCodeMap.getpassKey(getFromUser),getFromUser);
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
        catch (java.lang.NullPointerException e){
            out.print("false");

        }

    }


}
