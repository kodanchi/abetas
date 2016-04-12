package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddCycle",
        urlPatterns = {"/AddCycle"})
public class AddCycle extends HttpServlet {
    /**
     * insert cycle , then redirect to the cycle list.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            C_AS_Insert dba = new C_AS_Insert();
            int id = 0;
            try {


                id = dba.addCycle();
                Auditor.add((String)request.getSession().getAttribute("username"),"added new cycle (ID: "+id+")");


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        try {
            response.sendRedirect("/cycle/index.jsp?page=piList&cycle="+id);
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
