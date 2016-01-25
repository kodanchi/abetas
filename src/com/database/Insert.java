
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

@WebServlet(name = "Insert",
        urlPatterns = {"/Insert"})
public class Insert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        ASDB dba=new ASDB();
        try {
            dba.initialization(request.getParameter("uname"), request.getParameter("cname"), request.getParameter("universityLogo"), request.getParameter("adminFirstName"), request.getParameter("adminMiddleName"), request.getParameter("adminLastName"), request.getParameter("adminUsername"), request.getParameter("adminpassword"), request.getParameter("adminemail"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        out.println("Insert");

        response.sendRedirect("http://localhost:8080/");
        // New location to be redirected
        // String site = new String("http://localhost:8081/");
        // response.setHeader("Location", site);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
