package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddRubricNames",
        urlPatterns = {"/AddRubricNames"})
public class AddRubricNames extends HttpServlet {
    /**
     * insert or update rubric names , then redirect to the term list.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("RubricID").equals("null")) {
            String id = request.getParameter("cycle");
            C_AS_Insert dba=new C_AS_Insert();
            try {
                dba.addRubricNames(request.getParameter("firstR"),request.getParameter("secondR"),request.getParameter("thirdR"),request.getParameter("forthR"),Integer.parseInt(id));
                Auditor.add((String)request.getSession().getAttribute("username"),"Added Rubrics names (Cycle ID : "+id+")");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.sendRedirect("/cycle/index.jsp?page=addTerm&cycle="+id+"&programID="+request.getParameter("programID"));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
