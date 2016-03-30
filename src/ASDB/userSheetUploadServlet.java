package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Mojahed on 2/3/2016.
 */
@WebServlet(name = "userSheetUploadServlet", urlPatterns = {"/upload/users"})
public class userSheetUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /*if(request.getParameter("file")== null){
            response.getHeader("/user/index.jsp");
        }*/
        String dataId = request.getParameter("file");
        Object obj = request.getSession().getAttribute(dataId);
        ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) obj;
        ArrayList<String> dataRow;

        System.out.println(dataArr);
        U_AS_Insert db = new U_AS_Insert();

        try {
            for(int i=0;i<dataArr.size();i++){
                dataRow = dataArr.get(i);
                if(dataRow.get(5).equalsIgnoreCase("superuser")){
                    db.addUser(0,dataRow.get(3),dataRow.get(4),dataRow.get(0)+"",dataRow.get(1)+"",dataRow.get(2)+"",null);
                }else if(dataRow.get(5).equalsIgnoreCase("faculty")){
                    db.addUser(1,dataRow.get(3),dataRow.get(4),dataRow.get(0)+"",dataRow.get(1)+"",dataRow.get(2)+"",null);
                }else if(dataRow.get(5).equalsIgnoreCase("evaluator")){
                    db.addUser(2,dataRow.get(3),dataRow.get(4),dataRow.get(0)+"",dataRow.get(1)+"",dataRow.get(2)+"",null);
                }

                Auditor.add((String) request.getSession().getAttribute("username"),"added new "+dataRow.get(5)+" user ("+
                        dataRow.get(4)+") via excel sheet");
            }

            response.sendRedirect("/users/index.jsp?status=Success");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //response.setStatus();
            //request.getRequestDispatcher("/users/index.jsp?status=Success").forward(request,response);
            //response.setStatus(HttpServletResponse.SC_CONTINUE);
            //response.setHeader("Location","/users/index.jsp?status=Success");

            /*response.getWriter().print("<!DOCTYPE HTML>\n" +
                    "<html lang=\"en-US\">\n" +
                    "    <head>\n" +
                    "        <meta charset=\"UTF-8\">\n" +
                    "        <meta http-equiv=\"refresh\" content=\"1;url=\">" +
                    "</head>" +
                    "</html>");*/
        }

        //importer. request.getParameter("file");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
