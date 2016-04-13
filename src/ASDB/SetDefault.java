package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SetDefault is used to set the selected term to be default term (current)
 */
@WebServlet(name = "SetDefault",
        urlPatterns = {"/SetDefault"})
public class SetDefault extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("cycle");
        C_AS_Update dba = new C_AS_Update();
        try {
            dba.updateDefault(Integer.parseInt(request.getParameter("term")));
            Auditor.add((String)request.getSession().getAttribute("username"),"Set term to default (Term ID: "+
                    request.getParameter("term")+")");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm&cycle="+id);
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
