package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCycle",
        urlPatterns = {"/DeleteCycle"})
public class DeleteCycle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        C_AS_Delete dba=new C_AS_Delete();
        try {
            dba.deleteCycle(Integer.parseInt(request.getParameter("id")));
            Auditor.add((String)request.getSession().getAttribute("username"),"Deleted cycle (Course ID : "+request.getParameter("id")+")");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/cycle/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
