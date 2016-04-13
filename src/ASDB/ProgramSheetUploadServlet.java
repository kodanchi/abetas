package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ProgramSheetUploadServlet is used to insert the imported data from excel file (objectives/outcomes/courses) that
 * stored in session variable sheetData after validation.
 */
@WebServlet(name = "sheetUploadServlet", urlPatterns = {"/upload/program"})
public class ProgramSheetUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = "";
        String name = "";
        String dataType = "";

        if(request.getParameter("id") != null && request.getParameter("name") != null && request.getParameter("data-type") != null){
            id = request.getParameter("id");
            name = request.getParameter("name");
            dataType = request.getParameter("data-type");
        }
        Object obj = request.getSession().getAttribute("sheetData");
        ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) obj;
        ArrayList<String> dataRow;

        P_AS_Insert dba = new P_AS_Insert();
        try {

            for(int i=0;i<dataArr.size();i++) {
                dataRow = dataArr.get(i);

                if(dataType.equals("obj")){
                    System.out.println("dataType : "+dataType);
                    dba.addObject(dataRow.get(0), Integer.parseInt(id));
                    Auditor.add((String)request.getSession().getAttribute("username"),"added new objective (Program ID: "+id+")");
                }else if(dataType.equals("outcomes")){
                    dba.addOutcome(dataRow.get(0), Integer.parseInt(id));
                    Auditor.add((String)request.getSession().getAttribute("username"),"added new outcome (Program ID: "+id+")");

                }else if(dataType.equals("courses")){
                    dba.addCourse(dataRow.get(1),dataRow.get(0), Integer.parseInt(dataRow.get(2)),0,Integer.parseInt(id));
                    Auditor.add((String)request.getSession().getAttribute("username"),"added new objective (Program ID: "+id+")");

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            if(dataType.equals("obj")) {
                response.setHeader("Location", "/program/index.jsp?page=ObjList&name=" + name + "&id=" + id + "&status=Success");
            }else if(dataType.equals("outcomes")){
                response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name=" + name + "&id=" + id + "&status=Success");
            }else if(dataType.equals("courses")){
                response.setHeader("Location", "/program/index.jsp?page=CoursesList&name=" + name + "&id=" + id + "&status=Success");
            }

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
