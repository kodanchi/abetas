
package com.database;

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

@WebServlet(name = "Update",
        urlPatterns = {"/Update"})
public class Update extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        ASDB dba=new ASDB();
        try {
            if(request.getParameter("userType").equals("Superuser"))
            dba.addUser(0, request.getParameter("uname"), request.getParameter("email"), request.getParameter("fname"), request.getParameter("mname"), request.getParameter("lname"));
            else if (request.getParameter("userType").equals("Faculty_Member"))
                dba.addUser(1, request.getParameter("uname"), request.getParameter("email"), request.getParameter("fname"), request.getParameter("mname"), request.getParameter("lname"));
            else if (request.getParameter("userType").equals("Evaluator"))
                dba.addUser(2, request.getParameter("uname"), null, request.getParameter("fname"), request.getParameter("mname"), request.getParameter("lname"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        out.println("update");


        response.sendRedirect("http://localhost:8081/");
        // New location to be redirected
        // String site = new String("http://localhost:8081/");
        // response.setHeader("Location", site);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

