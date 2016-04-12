package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddObj",
        urlPatterns = {"/Add Program Objective"})
public class AddObj extends HttpServlet {
    /**
     * insert or update objective , then redirect to the objective list.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("ObjValue").equals("null")) {

            P_AS_Insert dba = new P_AS_Insert();
            try {
                dba.addObject(request.getParameter("Obj"), Integer.parseInt(request.getParameter("id")));
                Auditor.add((String)request.getSession().getAttribute("username"),"added new objective (Program ID: "+request.getParameter("id")+")");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/program/index.jsp?page=ObjList&name=" + request.getParameter("name") +
                    "&id=" + request.getParameter("id")+"&status=Objective has been added ");
        }
        else {

            P_AS_Update dba = new P_AS_Update();
            try {
                dba.updateObjective(Integer.parseInt(request.getParameter("Objid")), request.getParameter("Obj"));
                Auditor.add((String)request.getSession().getAttribute("username"),"Updated objective (ID: "+request.getParameter("Objid")+")");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/program/index.jsp?page=ObjList&name=" + request.getParameter("name") +
                    "&id=" + request.getParameter("id")+"&status=Objective has been updated ");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
