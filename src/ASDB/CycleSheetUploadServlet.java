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
@WebServlet(name = "CycleSheetUploadServlet", urlPatterns = {"/upload/cycle"})
public class CycleSheetUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isValid = true;

        String cycle = "";
        String term = "";
        String programID = "";
        String courseCode = "";
        String courseName = "";
        String section = "";
        String dataType = "";
        String thresh="";

        if(request.getParameter("cycle") != null && request.getParameter("term") != null && request.getParameter("data-type") != null){
            cycle = request.getParameter("cycle");
            term = request.getParameter("term");
            programID = request.getParameter("programID");
            courseCode = request.getParameter("courseCode");
            courseName = request.getParameter("courseName");
            section = request.getParameter("section");
            dataType = request.getParameter("data-type");
            thresh = request.getParameter("Thresh");
        }
        //String dataId = request.getParameter("file");
        Object obj = request.getSession().getAttribute("sheetData");
        ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) obj;
        ArrayList<String> dataRow;

        System.out.println("dataArr"+ dataArr);
        C_AS_Insert dba = new C_AS_Insert();
        C_AS_Select dbs = new C_AS_Select();
        try {
            //cycle=dbaS.selectProgram(request.getParameter("Pname"));

            for(int i=0;i<dataArr.size();i++) {
                dataRow = dataArr.get(i);

                if(dataType.equals("students")){
                    System.out.println("dataType : "+dataType);
                    dba.addStudent(dataRow.get(1), Integer.parseInt(dataRow.get(0)), Integer.parseInt(section));
                }else if(dataType.equals("pis")){
                    if(dbs.isPIExist(dataRow.get(0), Integer.parseInt(programID), Integer.parseInt(cycle))){
                        isValid = false;
                        sendErrorMsg(dataRow.get(0)+ " is already existed.",dataType,cycle,term,programID,courseCode,courseName,section,response,request);
                        break;
                    }else {
                        dba.addPI(dataRow.get(0), Integer.parseInt(programID), Integer.parseInt(thresh), Integer.parseInt(cycle));
                    }
                }else if(dataType.equals("courses")){
                    dba.addCourse(dataRow.get(1),dataRow.get(0), Integer.parseInt(dataRow.get(2)),0,Integer.parseInt(cycle));
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

            if(isValid){
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                if(dataType.equals("students")) {
                    //response.setHeader("Location", "/program/index.jsp?page=ObjList&term=" + term + "&cycle=" + cycle + "&status=Success");
                    response.setHeader("Location", "/cycle/index.jsp?page=studentList&cycle="+ cycle +"&term="+ term  +"&programID=" + programID + "&courseCode=" + courseCode +
                            "&courseName=" + courseName + "&section=" + section +"&status=Success");
                }else if(dataType.equals("pis")){
                    response.setHeader("Location", "/cycle/index.jsp?page=piList&cycle="+ cycle +"&term="+ term  +"&programID=" + programID +"&status=Success");
                }else if(dataType.equals("courses")){
                    response.setHeader("Location", "/program/index.jsp?page=CoursesList&term=" + term + "&cycle=" + cycle + "&status=Success");
                }
            }

            /*response.getWriter().print("<!DOCTYPE HTML>\n" +
                    "<html lang=\"en-US\">\n" +
                    "    <head>\n" +
                    "        <meta charset=\"UTF-8\">\n" +
                    "        <meta http-equiv=\"refresh\" content=\"1;url=/program/index.jsp?term=&cycle=&page="+dataType+"&status=Success\">" +
                    "</head>" +
                    "</html>");*/
        }

        //importer. request.getParameter("file");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void sendErrorMsg(String Error_Msg,String pdataType, String cycle, String term, String programID, String courseCode,
                             String courseName, String section, HttpServletResponse response, HttpServletRequest request) throws IOException {

        request.getSession().setAttribute("errMsg",Error_Msg);

        //response.sendRedirect("/users/index.jsp?cmd=upload&err="+Error_Msg);
        //response.sendRedirect("/cycle/index.jsp?cycle="+ cycle +"&term="+ term +"&cmd=upload&data="+pdataType);
        String url = null;
        if(pdataType.equals("students")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID + "&courseCode=" + courseCode +
                    "&courseName=" + courseName + "&section=" + section +"&cmd=upload&data="+pdataType;
        }else if(pdataType.equals("pis")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID +"&cmd=upload&data="+pdataType;
        }
        response.sendRedirect(url);
        /*RequestDispatcher rd = request.getRequestDispatcher("/program/index.jsp?name="+term+"&id="+cycle+"&cmd=upload&data="+pdataType+"&err="+Error_Msg);

        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }*/
    }
}
