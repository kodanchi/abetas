package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddOut",
        urlPatterns = {"/Add Student Outcome"})
public class AddOut extends HttpServlet {
    /**
     * insert or update student outcome , then redirect to the student outcome list.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("OutValue").equals("null")) {
        P_AS_Insert dba=new P_AS_Insert();
        try {
            dba.addOutcome(request.getParameter("Out"),Integer.parseInt(request.getParameter("id")));
            Auditor.add((String)request.getSession().getAttribute("username"),"added new outcome (ID program: "+request.getParameter("id")+")");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        }
        else {
            P_AS_Update dba = new P_AS_Update();
            try {
                dba.updateOutcome(Integer.parseInt(request.getParameter("Outid")), request.getParameter("Out"));
                Auditor.add((String)request.getSession().getAttribute("username"),"Updated outcome (ID : "+request.getParameter("Outid")+")");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name=" + request.getParameter("name") + "&id=" + request.getParameter("id"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
