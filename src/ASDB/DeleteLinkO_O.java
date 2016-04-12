package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteLinkO_O",
        urlPatterns = {"/Delete Link Objective and Outcome"})
public class DeleteLinkO_O extends HttpServlet {
    /**
     * connect to the database and call delete method to delete link by ID, then add to system log and add this event to the system log.
     * Redirect to the cycle link list.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        C_AS_Delete dba=new C_AS_Delete();
        try {
            dba.deleteLinkO_O(Integer.parseInt(request.getParameter("Linkid")));
            Auditor.add((String)request.getSession().getAttribute("username"),"Deleted Outcome/Objective link (ID : "+request.getParameter("Linkid")+")");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setHeader("Location", "/program/index.jsp?page=LinkOutObj&name=" + request.getParameter("name") +
                "&id=" + request.getParameter("id"));
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
