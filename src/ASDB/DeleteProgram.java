package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteProgram",
        urlPatterns = {"/DeleteProgram"})
public class DeleteProgram extends HttpServlet {
    /**
     * connect to the database and call delete method to delete program by ID, then add to system log and add this event to the system log.
     * Redirect to the program list.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        P_AS_Delete dba=new P_AS_Delete();
        try {
            dba.deleteProgram(Integer.parseInt(request.getParameter("id")));
            Auditor.add((String)request.getSession().getAttribute("username"),"Deleted program (ID : "+request.getParameter("id")+")");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setHeader("Location", "/program/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
