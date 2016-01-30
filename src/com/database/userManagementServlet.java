package com.database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Abdullah on 1/24/2016.
 */
@WebServlet(name = "userManagementServlet",
        urlPatterns = {"/22"})
public class userManagementServlet extends HttpServlet {
    ASDB dba;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        out.println("\n" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Bootstrap Example</title>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>\n" +
                "    <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"container\">\n" +
                "    <div>\n" +
                "\n" +
                "        <h1>User Management</h1>\n" +
                "        <table class=\"table table-bordered\">\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th>Firstname</th>\n" +
                "                <th>Middlename</th>\n" +
                "                <th>Lastname</th>\n" +
                "                <th>Username</th>\n" +
                "                <th>Email</th>\n" +
                "                <th>Access level</th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "            <tr>\n" );


                dba=new ASDB();
        try {
            ArrayList<ArrayList<String>> rs = dba.selectAllSuperusers();
            ArrayList<String> rsRow ;

            for (int i=0; i<rs.size();i++){
                rsRow = new ArrayList<String>();
                rsRow = rs.get(i);
                out.print("<tr>");
                for (int j=0; j<rsRow.size();j++) {
                    out.print("<td>"+rsRow.get(j)+"</td>");
                }
                out.print("</tr>");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }






               out.print( "</tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <p><a class=\"btn btn-lg btn-success\" href=\"#\" role=\"button\">Add user</a></p>\n" +
                "        <P><a class=\"btn btn-lg btn-success\" href=\"#\" role=\"button\">Back</a></p>\n" +
                "\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

    }
}
