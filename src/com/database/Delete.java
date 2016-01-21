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
@WebServlet(name = "Delete",
<<<<<<< HEAD:src/com/database/Delete.java
        urlPatterns = {"/Delete"})
public class Delete extends HttpServlet {
=======
        urlPatterns = {"Delete1"})
 public class Delete extends HttpServlet {
>>>>>>> fafb41f6f6453ffb2c87d123e10635d2b3f6a1ae:src/com/database/Delete.java
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        DBAccess dba=new DBAccess();
        try {
            dba.delete(request.getParameter("name"),request.getParameter("logo"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        out.println("Delete");


        response.sendRedirect("http://localhost:8081/");
        // New location to be redirected
        // String site = new String("http://localhost:8081/");
        // response.setHeader("Location", site);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
