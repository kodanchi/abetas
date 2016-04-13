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

/**
 * CycleSheetImportServlet is used receive any import form of post method and multipart content under cycle folder which
 * will initiate new object of ImportCycleSheet class to validate the data inside the imported sheet, then getting the
 * request parameters of inputs (cycle id, term id, data type) and finally based on dataType (students,performance indicators)
 * the columns names will be set in a string array and file data will be validated.
 *
 * After validation the data will be sent to the upload page otherwise, the user will be redirect to import page with
 * error message.
 */
@WebServlet(name = "CycleSheetImportServlet",urlPatterns = {"/import/cycle"})
public class CycleSheetImportServlet extends HttpServlet {
    String upload = "";
    String id ="";
    String name ="";
    String dataType ="";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ImportCycleSheet importer = new ImportCycleSheet();


        boolean dataIsValid = false;


        if(importer.sheetValidation(request)){
            try {
                id = importer.getId();
                name = importer.getName();
                dataType = importer.getDataType();

                if(dataType.equals("students")){
                    String[] sheetChecker = {"student ID","student name"};
                    dataIsValid = importer.studentsSheetValidation(sheetChecker);
                }else if(dataType.equals("pis")){
                    String[] sheetChecker = {"performance indicator","threshold"};
                    dataIsValid = importer.PIsSheetValidation(sheetChecker);
                }

                if(dataIsValid){
                    ArrayList<ArrayList<String>> data = importer.getSheetData();
                    HttpSession session = request.getSession();
                    session.setAttribute("sheetData", data);
                    importer.sendSuccessMsg(response);

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
