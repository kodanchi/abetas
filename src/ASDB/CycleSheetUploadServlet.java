package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * CycleSheetUploadServlet is used to insert the imported data from excel file (students or performance indicators) that
 * stored in session variable sheetData after validation.
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

        if(request.getParameter("cycle") != null && request.getParameter("term") != null && request.getParameter("data-type") != null){
            cycle = request.getParameter("cycle");
            term = request.getParameter("term");
            programID = request.getParameter("programID");
            courseCode = request.getParameter("courseCode");
            courseName = request.getParameter("courseName");
            section = request.getParameter("section");
            dataType = request.getParameter("data-type");
        }

        Object obj = request.getSession().getAttribute("sheetData");
        ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) obj;
        ArrayList<String> dataRow;

        C_AS_Insert dba = new C_AS_Insert();
        C_AS_Select dbs = new C_AS_Select();
        try {

            for(int i=0;i<dataArr.size();i++) {
                dataRow = dataArr.get(i);

                if(dataType.equals("students")){
                    if(dbs.isStudentIDExist(dataRow.get(0),Integer.parseInt(section))){
                        isValid = false;
                        sendErrorMsg(dataRow.get(0)+ " is already existed twice in this section.",dataType,cycle,term,
                                programID,courseCode,courseName,section,response,request);
                        break;
                    }else {
                        dba.addStudent(dataRow.get(1), dataRow.get(0), Integer.parseInt(section));
                        Auditor.add((String)request.getSession().getAttribute("username"),"Added new student ("+
                                dataRow.get(1)+") via excel sheet (Section ID : "+section+")");
                    }
                }else if(dataType.equals("pis")){
                    if(dbs.isPIExist(dataRow.get(0), Integer.parseInt(programID), Integer.parseInt(cycle))){
                        isValid = false;
                        sendErrorMsg(dataRow.get(0)+ " is already existed.",dataType,cycle,term,programID,courseCode,
                                courseName,section,response,request);
                        break;
                    }else {
                        dba.addPI(dataRow.get(0), Double.parseDouble(dataRow.get(1)) ,Integer.parseInt(programID),
                                Integer.parseInt(cycle));
                        Auditor.add((String)request.getSession().getAttribute("username"),"Added new performance indicator " +
                                "via excel sheet (Cycle ID : "+cycle+")");
                    }
                }else if(dataType.equals("courses")){
                    dba.addCourse(dataRow.get(1),dataRow.get(0), Integer.parseInt(dataRow.get(2)),0,Integer.parseInt(cycle));
                    Auditor.add((String)request.getSession().getAttribute("username"),"Added new course via excel sheet " +
                            "(Cycle ID : "+cycle+")");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(isValid){
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                if(dataType.equals("students")) {
                    response.setHeader("Location", "/cycle/index.jsp?page=studentList&cycle="+ cycle +"&term="+ term  +
                            "&programID=" + programID + "&courseCode=" + courseCode + "&courseName=" + courseName +
                            "&section=" + section +"&status=Success");
                }else if(dataType.equals("pis")){
                    response.setHeader("Location", "/cycle/index.jsp?page=piList&cycle="+ cycle +"&term="+ term  +
                            "&programID=" + programID +"&status=Success");
                }else if(dataType.equals("courses")){
                    response.setHeader("Location", "/program/index.jsp?page=CoursesList&term=" + term + "&cycle=" +
                            cycle + "&status=Success");
                }
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * send error message through session attribute and redirect the user to the same page (students import or performance
     * indicators import page).
     * @param Error_Msg message that will
     * @param pdataType data type that will be inserted to the database.
     * @param cycle Cycle ID.
     * @param term Term ID.
     * @param programID Program ID.
     * @param courseCode Course Code.
     * @param courseName Course name.
     * @param section Section ID.
     * @param response HttpServletResponse
     * @param request HttpServletRequest
     * @throws IOException
     */
    public void sendErrorMsg(String Error_Msg,String pdataType, String cycle, String term, String programID, String courseCode,
                             String courseName, String section, HttpServletResponse response, HttpServletRequest request) throws IOException {

        request.getSession().setAttribute("errMsg",Error_Msg);
        String url = null;
        if(pdataType.equals("students")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID + "&courseCode=" + courseCode +
                    "&courseName=" + courseName + "&section=" + section +"&cmd=upload&data="+pdataType;
        }else if(pdataType.equals("pis")){
            url ="/cycle/index.jsp?cycle="+ cycle +"&term="+ term  +"&programID=" + programID +"&cmd=upload&data="+pdataType;
        }
        response.sendRedirect(url);
    }
}
