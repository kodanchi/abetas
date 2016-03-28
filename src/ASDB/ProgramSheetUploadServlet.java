package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mojahed on 2/3/2016.
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
        //String dataId = request.getParameter("file");
        Object obj = request.getSession().getAttribute("sheetData");
        ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) obj;
        ArrayList<String> dataRow;

        System.out.println("dataArr"+ dataArr);
        P_AS_Insert dba = new P_AS_Insert();
        //P_AS_Select dbaS=new P_AS_Select();
        try {
            //id=dbaS.selectProgram(request.getParameter("Pname"));

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
            //response.setStatus();
            //request.getRequestDispatcher("/users/index.jsp?status=Success").forward(request,response);
            //response.setStatus(HttpServletResponse.SC_CONTINUE);
            //response.setHeader("Location","/users/index.jsp?status=Success");
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            if(dataType.equals("obj")) {
                response.setHeader("Location", "/program/index.jsp?page=ObjList&name=" + name + "&id=" + id + "&status=Success");
            }else if(dataType.equals("outcomes")){
                response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name=" + name + "&id=" + id + "&status=Success");
            }else if(dataType.equals("courses")){
                response.setHeader("Location", "/program/index.jsp?page=CoursesList&name=" + name + "&id=" + id + "&status=Success");
            }

            /*response.getWriter().print("<!DOCTYPE HTML>\n" +
                    "<html lang=\"en-US\">\n" +
                    "    <head>\n" +
                    "        <meta charset=\"UTF-8\">\n" +
                    "        <meta http-equiv=\"refresh\" content=\"1;url=/program/index.jsp?name=&id=&page="+dataType+"&status=Success\">" +
                    "</head>" +
                    "</html>");*/
        }

        //importer. request.getParameter("file");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
