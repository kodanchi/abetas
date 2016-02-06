
package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Ibrahim Abuaqel on 1/19/2016.
 */

@WebServlet(name = "DeleteUser",
        urlPatterns = {"/deleteUser"})
public class DeleteUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("########################Delete#######################");
        AS_Delete ddb = new AS_Delete();
        try {


            String userType = request.getParameter("type");
            String userId = request.getParameter("id");
            if(userId != null && userType != null) {
                if (userType.equals("Superuser"))
                    ddb.deleteSuperuser(Integer.parseInt(request.getParameter("id")));
                else if (userType.equals("Faculty_Member"))
                    ddb.deleteFaculty(Integer.parseInt(request.getParameter("id")));
                else if (userType.equals("Evaluator"))
                    ddb.deleteEvaluator(Integer.parseInt(request.getParameter("id")));

                response.sendRedirect("/users/index.jsp?status=userDeleted");

            }else {
                System.out.println("id & type = null");
                response.sendRedirect("/users/");

            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        out.println("update");


        // New location to be redirected
        // String site = new String("http://localhost:8081/");
        // response.setHeader("Location", site);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

