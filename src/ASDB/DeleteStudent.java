package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteStudent",
        urlPatterns = {"/DeleteStudent"})
public class DeleteStudent extends HttpServlet {
    /**
     * connect to the database and call delete method to delete student by ID, then add to system log and add this event to the system log.
     * Redirect to the cycle student list.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        C_AS_Delete dba=new C_AS_Delete();
        try {
            dba.deleteStudent(Integer.parseInt(request.getParameter("S_ID")));
            Auditor.add((String)request.getSession().getAttribute("username"),"Deleted student (ID : "+request.getParameter("S_ID")+")");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+request.getParameter("cycle")+"&term="+
                    request.getParameter("term")+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+
                    "&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
