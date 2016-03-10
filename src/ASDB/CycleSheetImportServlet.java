package ASDB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Mojahed on 2/2/2016.
 */
@WebServlet(name = "CycleSheetImportServlet",urlPatterns = {"/import/cycle"})
public class CycleSheetImportServlet extends HttpServlet {
    String upload = "";
    String id ="";
    String name ="";
    String dataType ="";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //Your code
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String[] sheetChecker = {"firstname","middlename","lastname","username","email","level"};

        ImportCycleSheet importer = new ImportCycleSheet(request);
        //PrintWriter out = response.getWriter();


        boolean dataIsValid = false;


        if(importer.sheetVaildation(request,response)){
            try {
                id = importer.getId();
                name = importer.getName();
                dataType = importer.getDataType();

                if(dataType.equals("students")){
                    String[] sheetChecker = {"student ID","student name"};
                    dataIsValid = importer.studentsSheetVaildation(sheetChecker);
                }else if(dataType.equals("pis")){
                    String[] sheetChecker = {"performance indicator"};
                    dataIsValid = importer.PIsSheetVaildation(sheetChecker);
                }else {
                    System.out.println("no data type");
                }
                if(dataIsValid){
                    /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                    response.setHeader("Location","/users/index.jsp?cmd=upload&file="+upload);*/

                    ArrayList<ArrayList<String>> data = importer.getSheetData();

                    HttpSession session = request.getSession();
                    System.out.println("set new session :"+session.getId());
                    String sheetDataId = UUID.randomUUID().toString();
                    System.out.println("sheetDataId :"+sheetDataId);
                    System.out.println("Data is  :"+data);
                    session.setAttribute("sheetData", data);
                    //request.setAttribute("sheetData", sheetDataId);

                    //request.setAttribute("sheetData", data);
                    //request.getRequestDispatcher("/").forward(request, response);

                    importer.sendSuccessMsg(response,request);

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
