package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteIC",
        urlPatterns = {"/DeleteIC"})
public class DeleteIC extends HttpServlet {
    /**
     * connect to the database and call delete method to delete include course by ID, then add to system log and add this event to the system log.
     * Redirect to the cycle course list.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        C_AS_Delete dba = new C_AS_Delete();
        String id = request.getParameter("cycle");
        String Termid = request.getParameter("term");


        try {
                dba.deleteIC(request.getParameter("Code"),Integer.parseInt(Termid));
            Auditor.add((String)request.getSession().getAttribute("username"),"Excluded course from term (Course ID : "+request.getParameter("Courseid")+")");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            response.sendRedirect("/cycle/index.jsp?page=includeCourse&cycle="+id+"&term="+Termid+"&programID="+request.getParameter("programID"));

        } catch (NullPointerException e) {
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
