package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ibrahim Abuaqel on 2/7/2016.
 */
@WebServlet(name = "AddTerm",
        urlPatterns = {"/AddTerm"})
public class AddTerm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");

            AS_Insert dba = new AS_Insert();
            String id = request.getParameter("cycleId");
            int Termid = 0;
            try {

                Termid = dba.addTerm(request.getParameter("termName"),request.getParameter("termYear"),request.getParameter("cycleId"));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
            System.out.println("ttrttttttttttttttttttttttttt  id          " +String.valueOf(id)+"ttrttttttttttttttttttttttttt  Termid          " +String.valueOf(Termid));
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
        try {
            request.getSession().setAttribute("Termid",String.valueOf(Termid));
            request.getRequestDispatcher("/cycle/index.jsp?page=includeCourse").forward(request,response);
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
