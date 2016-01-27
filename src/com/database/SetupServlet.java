package com.database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Mojahed on 1/27/2016.
 */
@WebServlet(name = "SetupServlet", urlPatterns = "/setup")
public class SetupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("this is post");
        if(request.getParameter("dbRemove")!=null)
        {
            System.out.println(request.getParameter("dbRemove"));
            switch (request.getParameter("dbRemove")){

                case "yes":
                    response.sendRedirect("/setup/install.jsp");
                    break;
                case "no":
                    response.sendRedirect("http://localhost:8080/");
                    break;
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is get");
        InstallDB dbcon = new InstallDB();
        PrintWriter out = response.getWriter();
        try {
            if(dbcon.setUpChk()){
                out.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <!-- Bootstrap -->\n" +
                        "    <link href=\"/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                        "    <link href=\"/css/bootstrap-theme.min.css\" rel=\"stylesheet\">\n" +
                        "    <title>ERROR</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <form method=\"post\" action=\"#\">\n" +
                        "        <div class=\"panel panel-danger\">\n" +
                        "            <div class=\"panel-heading\">\n" +
                        "                <h3 class=\"panel-title\">Database Does exist!</h3>\n" +
                        "            </div>\n" +
                        "            <div class=\"panel-body\">\n" +
                        "                <h4>Do you want to delete the current database!?</h4>\n" +
                        "                <p>\n" +
                        "                <label for=\"selectRes\" >Delete database!? </label>\n" +
                        "                    <select id=\"selectRes\" name=\"dbRemove\">\n" +
                        "                        <option value=\"yes\">Yes</option>\n" +
                        "                        <option value=\"no\">No</option>\n" +
                        "                    </select>\n" +
                        "                </p>\n" +
                        "                <button class=\"btn btn-default\" type=\"submit\">Go</button>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </form>\n" +
                        "</body>\n" +
                        "</html>");
            }else {
                response.sendRedirect("/setup/install.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
