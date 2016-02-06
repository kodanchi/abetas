
package ASDB;

import com.database.ASDB;

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

@WebServlet(name = "UpdateUser",
        urlPatterns = {"/updateUser"})
public class UpdateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        AS_Insert idb = new AS_Insert();
        AS_Delete ddb = new AS_Delete();
        AS_Update udb = new AS_Update();
        try {
            String userNewType = request.getParameter("userType");
            String userOldType = request.getParameter("oldLvl");
            System.out.println(userOldType+" = = "+userNewType);

            if(userNewType.equals(userOldType)){ //if the user lvl the same

                //update the table needed
                if(userNewType.equals("Superuser"))
                    udb.updateSuperuser(Integer.parseInt(request.getParameter("id")),request.getParameter("fname"),request.getParameter("mname"),request.getParameter("lname"),request.getParameter("username"),request.getParameter("email"));
                else if (userNewType.equals("Faculty_Member"))
                    udb.updateFaculty(Integer.parseInt(request.getParameter("id")),request.getParameter("fname"),request.getParameter("mname"),request.getParameter("lname"),request.getParameter("username"),request.getParameter("email"));
                else if (userNewType.equals("Evaluator"))
                    udb.updateEvaluator(Integer.parseInt(request.getParameter("id")),request.getParameter("fname"),request.getParameter("mname"),request.getParameter("lname"),request.getParameter("username"));

            }else { //if the user lvl changed

                //delete the userdata form the old table
                if(userOldType.equals("Superuser"))
                    ddb.deleteSuperuser(Integer.parseInt(request.getParameter("id")));
                else if (userOldType.equals("Faculty_Member"))
                    ddb.deleteFaculty(Integer.parseInt(request.getParameter("id")));
                else if (userOldType.equals("Evaluator"))
                    ddb.deleteEvaluator(Integer.parseInt(request.getParameter("id")));

                //insert the new userdata to the new level table
                if(userNewType.equals("Superuser"))
                    idb.addUser(0, request.getParameter("uname"), request.getParameter("uemail"), request.getParameter("fname"), request.getParameter("mname"), request.getParameter("lname"));
                else if (userNewType.equals("Faculty_Member"))
                    idb.addUser(1, request.getParameter("uname"), request.getParameter("uemail"), request.getParameter("fname"), request.getParameter("mname"), request.getParameter("lname"));
                else if (userNewType.equals("Evaluator"))
                    idb.addUser(2, request.getParameter("uname"), null, request.getParameter("fname"), request.getParameter("mname"), request.getParameter("lname"));




            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        out.println("update");


        response.sendRedirect("/users/index.jsp?status=userUpdated");
        // New location to be redirected
        // String site = new String("http://localhost:8081/");
        // response.setHeader("Location", site);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

