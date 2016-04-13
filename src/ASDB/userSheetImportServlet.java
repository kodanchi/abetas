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

/**
 * userSheetImportServlet is used receive import form of method post and multipart content under cycle folder which
 * will initiates new object of ImportCycleSheet class to validate the data inside the imported sheet, then getting the
 * request parameters of inputs (cycle id, term id, data type) and finally based on dataType (students,performance indicators)
 * the columns names will be set in a string array and file data will be validated.
 *
 * After validation the data will be sent to the upload page otherwise, the user will be redirect to import page with
 * error message.
 */
@WebServlet(name = "userSheetImportServlet",urlPatterns = {"/import/users"})
public class userSheetImportServlet extends HttpServlet {

    /**
     *  called when servlet initiated
     * @param config ServletConfig
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] sheetChecker = {"firstname","middlename","lastname","username","level","email"};
        ImportUserSheet importer = new ImportUserSheet();
        if(importer.sheetValidation(request)){
            try {
                if(importer.UserSheetValidation(sheetChecker)){

                    ArrayList<ArrayList<String>> data = importer.getSheetData();

                    HttpSession session = request.getSession();
                    session.setAttribute("sheetData", data);
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
