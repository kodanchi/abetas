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

//        try {
//            select("","");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        loginDB d = new loginDB();


        try {
            d.selectEmail(getFromUser);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("enterPasscode.jsp");
    }
    public void select(String name, String logo) throws ClassNotFoundException, SQLException {
    DBAccess co =new DBAccess();
        Random rand = new Random();
        int randNumber=randNumber=rand.nextInt(100000);
        //Map<String,Integer> myMap = new HashMap<String,Integer>();
        String inp;

    System.out.print("Please enter the email");
        //inp=in.nextLine();
        co.connect();
        //stmt=conn.createStatement();
        //result=stmt.executeQuery("select * from university");
        String query = "select Uni_name from university where Uni_logo='i'";

        PreparedStatement preparedStmt = co.conn.prepareStatement(query);
        co.result=preparedStmt.executeQuery();

        String re="";
        while (co.result.next()){
            re= co.result.getString(1);
            System.out.println(re);
           // ArrayList al= new ArrayList();

               // al.add(re);
           // myMap.put(re,randNumber);
            //System.out.println(myMap.size());



        }
       // System.out.println(re);
        System.out.println(randNumber);

        co.conn.close();
    }

}
