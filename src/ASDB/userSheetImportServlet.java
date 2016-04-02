package ASDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Mojahed on 2/2/2016.
 */
@WebServlet(name = "userSheetImportServlet",urlPatterns = {"/import/users"})
public class userSheetImportServlet extends HttpServlet {
    String upload = "";
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //Your code
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] sheetChecker = {"firstname","middlename","lastname","username","level","email"};
        ImportUserSheet importer = new ImportUserSheet(request);
        //PrintWriter out = response.getWriter();

        if(importer.sheetVaildation(request,response)){
            try {
                if(importer.UserSheetVaildation(sheetChecker)){
                    /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                    response.setHeader("Location","/users/index.jsp?cmd=upload&file="+upload);*/

                    ArrayList<ArrayList<String>> data = importer.getSheetData();

                    HttpSession session = request.getSession();
                    System.out.println("set new session :"+session.getId());
                    String sheetDataId = UUID.randomUUID().toString();
                    System.out.println("sheetDataId :"+sheetDataId);
                    session.setAttribute("sheetData", data);
                    //request.setAttribute("sheetData", sheetDataId);

                    //request.setAttribute("sheetData", data);
                    //request.getRequestDispatcher("/").forward(request, response);
                    RequestDispatcher rd = request.getRequestDispatcher("/users/index.jsp?cmd=confirm");

                    try {
                        importer.deleteFile();
                        rd.forward(request, response);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }

                }else {
                    importer.sendErrorMsg(response,request);


                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else{
            importer.sendErrorMsg(response,request);


        }






    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
