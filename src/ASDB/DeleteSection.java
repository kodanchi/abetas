package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteSection",
        urlPatterns = {"/DeleteSection"})
public class DeleteSection extends HttpServlet {
    /**
     * connect to the database and call delete method to delete section by ID, then add to system log and add this event to the system log.
     * Redirect to the cycle section list.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        C_AS_Delete dba=new C_AS_Delete();
        try {
            dba.deleteSection(Integer.parseInt(request.getParameter("section")));
            Auditor.add((String)request.getSession().getAttribute("username"),"Deleted section (ID : "+request.getParameter("section")+")");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect("/cycle/index.jsp?page=CourseInfo&cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                    "&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode"));
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
