
package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@WebServlet(name = "DeleteUser",
        urlPatterns = {"/deleteUser"})
public class DeleteUser extends HttpServlet {
    /**
     * connect to the database and call delete method to delete user by ID, then add to system log and add this event to the system log.
     * Redirect to the user list.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        U_AS_Delete ddb = new U_AS_Delete();
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


                Auditor.add((String)request.getSession().getAttribute("username"),"Deleted user of type "+userType+" (ID : "+request.getParameter("id")+")");

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




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

