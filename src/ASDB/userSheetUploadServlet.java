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
 * userSheetUploadServlet is used to insert the imported data from excel file (users data) that stored in session variable
 * sheetData after validation.
 */
@WebServlet(name = "userSheetUploadServlet", urlPatterns = {"/upload/users"})
public class userSheetUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String dataId = request.getParameter("file");
        Object obj = request.getSession().getAttribute(dataId);
        ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) obj;
        ArrayList<String> dataRow;

        U_AS_Insert db = new U_AS_Insert();

        try {
            for(int i=0;i<dataArr.size();i++){
                dataRow = dataArr.get(i);
                if(dataRow.get(4).equalsIgnoreCase("superuser")){
                    db.addUser(0,dataRow.get(3),dataRow.get(5),dataRow.get(0)+"",dataRow.get(1)+"",dataRow.get(2)+"",null);
                }else if(dataRow.get(4).equalsIgnoreCase("faculty")){
                    db.addUser(1,dataRow.get(3),dataRow.get(5),dataRow.get(0)+"",dataRow.get(1)+"",dataRow.get(2)+"",null);
                }else if(dataRow.get(4).equalsIgnoreCase("evaluator")){
                    db.addUser(2,dataRow.get(3),dataRow.get(5),dataRow.get(0)+"",dataRow.get(1)+"",dataRow.get(2)+"",null);
                }

                Auditor.add((String) request.getSession().getAttribute("username"),"added new "+dataRow.get(5)+" user ("+
                        dataRow.get(4)+") via excel sheet");
            }

            response.sendRedirect("/users/index.jsp?status=The users' data were added successfully");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
