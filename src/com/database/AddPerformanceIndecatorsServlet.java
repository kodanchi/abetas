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
 * Created by Abdullah on 1/27/2016.
 */
@WebServlet(name = "AddPerformanceIndecatorsServlet",
        urlPatterns = {"/63"})
public class AddPerformanceIndecatorsServlet extends HttpServlet {
    ASDB dba;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>\n" +
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
                "        <p>Cycle >> Term >> Rubrics >> performance indicator >></p>\n" +
                "        <h1>Add Performance Indicators</h1>\n" +
                "        <p>Click add to enter Performance Indicator.</p>\n" +
                "        <table class=\"table table-bordered\">\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th>PI ID</th>\n" +
                "                <th>Outcome ID</th>\n" +
                "                <th>Edit</th>\n" +
                "                <th>Delete</th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "            <tr>" );


        dba=new ASDB();
        try {
            ArrayList<ArrayList<String>> rs = dba.selectAddPerformanceIndicators();
            ArrayList<String> rsRow ;

            for (int i=0; i<rs.size();i++){
                rsRow = new ArrayList<String>();
                rsRow = rs.get(i);
                out.print("<tr>");
                for (int j=0; j<rsRow.size();j++) {
                    out.print("<td>"+rsRow.get(j)+"</td>");

                }
                out.print("<td><span class=\"glyphicon glyphicon-edit\"></span>\n </td>");
                out.print("<td><span class=\"glyphicon glyphicon-remove\"></span>\n</td>");
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
                "        <p><a class=\"btn btn-lg btn-success\" href=\"#\" role=\"button\">Add</a></p>\n" +
                "        <p><a class=\"btn btn-lg btn-success\" href=\"#\" role=\"button\">Cancel</a>\n" +
                "        <a class=\"btn btn-lg btn-success\" href=\"#\" role=\"button\">Finish</a></p>\n" +
                "\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

    }
}
