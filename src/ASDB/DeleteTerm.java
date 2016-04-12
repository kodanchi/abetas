package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteTerm",
        urlPatterns = {"/DeleteTerm"})
public class DeleteTerm extends HttpServlet {
    /**
     * connect to the database and call delete method to delete term by ID, then add to system log and add this event to the system log.
     * Redirect to the cycle term list.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cid= request.getParameter("cycle");
        C_AS_Delete dba=new C_AS_Delete();
        try {
            dba.deleteTerm(Integer.parseInt(request.getParameter("term")));
            Auditor.add((String)request.getSession().getAttribute("username"),"Deleted term (ID : "+request.getParameter("term")+")");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect("/cycle/index.jsp?page=addTerm&cycle="+cid);
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
