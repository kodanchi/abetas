package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddCourses",
        urlPatterns = {"/Add Courses"})
public class AddCourses extends HttpServlet {
    String[] courseVal;

    /**
     * insert or update the course details, then redirect to the program course list.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("CourseName").equals("null")) {
        P_AS_Insert dba=new P_AS_Insert();
        P_AS_Select dbaS=new P_AS_Select();
        try {
            courseVal = new String[]{request.getParameter("Code"),request.getParameter("Cname"),request.getParameter("level")};
            if(request.getParameter("Code").equals("") || request.getParameter("Cname").equals("")){
                sendErrMsg("You must fill the course name and code",request.getParameter("name"),request.getParameter("id"),request,response);
            }else if(!dbaS.isCoursesCodeExist(request.getParameter("Code"))){
                dba.addCourse(request.getParameter("Code"),request.getParameter("Cname"),Integer.parseInt(request.getParameter("level")),0,Integer.parseInt(request.getParameter("id")));
                Auditor.add((String)request.getSession().getAttribute("username"),"Added new course (ID: "+request.getParameter("Code")+")");
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location", "/program/index.jsp?page=CoursesList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));

            }else {
                sendErrMsg("Course code is already exists",request.getParameter("name"),request.getParameter("id"),request,response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        }
        else {
            P_AS_Update dba = new P_AS_Update();
            P_AS_Select dbaS=new P_AS_Select();
            try {
                courseVal = new String[]{request.getParameter("Code"),request.getParameter("Cname"),request.getParameter("level")};
                if(request.getParameter("Code").equals("") || request.getParameter("Cname").equals("")){
                    sendErrMsg("You must fill the course name and code",request.getParameter("name"),request.getParameter("id"),request,response);
                }else {
                    if(!dbaS.isCoursesCodeExistExcept(request.getParameter("Code"),Integer.parseInt(request.getParameter("Cid")))) {
                        dba.updateCourse(Integer.parseInt(request.getParameter("Cid")), request.getParameter("Code"), request.getParameter("Cname"), Integer.parseInt(request.getParameter("level")));
                        Auditor.add((String)request.getSession().getAttribute("username"),"updated course (ID: "+request.getParameter("Code")+")");
                    }else {
                        sendErrMsg("Course code is already exists",request.getParameter("name"),request.getParameter("id"),request,response);

                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/program/index.jsp?page=CoursesList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * send error message
     * @param msg String
     * @param pname String
     * @param pid String
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void sendErrMsg(String msg,String pname,String pid,HttpServletRequest request, HttpServletResponse response){



        request.getSession().setAttribute("errMsg",msg);
        request.getSession().setAttribute("courseVal",courseVal);


        try {
            if(request.getParameter("CourseName").equals("null")) {
                response.sendRedirect("/program/index.jsp?name="+pname+"&id="+pid+"&page=addCourses&status=err");
            }else {

                String Courseid = request.getParameter("Courseid");
                String CourseName = request.getParameter("CourseName");
                String CourseLevel = request.getParameter("CourseLevel");
                String Cid = request.getParameter("Cid");
                response.sendRedirect("/program/index.jsp?page=updateCourses&name="+pname+"&id="+pid+"&Courseid="+ Courseid+"&Cid="+ Cid +"&CourseName="+CourseName+"&CourseLevel="+CourseLevel);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }

}
