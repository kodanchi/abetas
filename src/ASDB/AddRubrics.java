package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ibrahim Abuaqel on 2/8/2016.
 */
@WebServlet(name = "AddRubrics",
        urlPatterns = {"/AddRubrics"})
public class AddRubrics extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");

        AS_Insert dba = new AS_Insert();
        String id = request.getParameter("cycleId");
        try {

            dba.addRubric(request.getParameter("firstR"),request.getParameter("firstD"));
            dba.addRubric(request.getParameter("secondR"),request.getParameter("secondD"));
            dba.addRubric(request.getParameter("thirdR"),request.getParameter("thirdD"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
        System.out.println("ttrttttttttttttttttttttttttt  id          " +String.valueOf(id)+"ttrttttttttttttttttttttttttt           " );
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
        try {
            request.getRequestDispatcher("/cycle/index.jsp?page=piList").forward(request,response);
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
