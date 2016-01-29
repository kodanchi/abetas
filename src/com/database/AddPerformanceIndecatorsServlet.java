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

        out.println("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\" />\n" +
                "\t<link rel=\"icon\" type=\"image/png\" href=\"assets/paper_img/favicon.ico\">\n" +
                "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n" +
                "\t\n" +
                "    <title>Add Performance Indicatorstitle</title>\n" +
                "\n" +
                "\t<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\" />\n" +
                "    \n" +
                "    <link href=\"css/bootstrap.css\" rel=\"stylesheet\" />\n" +
                "    <link href=\"css/ct-paper.css\" rel=\"stylesheet\"/>\n" +
                "    <link href=\"css/demo.css\" rel=\"stylesheet\" /> \n" +
                "        \n" +
                "    <!--     Fonts and icons     -->\n" +
                "    <link href=\"http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\" rel=\"stylesheet\">\n" +
                "    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>\n" +
                "    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>\n" +
                "      \n" +
                "</head>\n" +
                "<body>\n" +
                " \n" +
                " <!-- Navbar start -->\n" +
                " <nav class=\"navbar navbar-ct-danger\" role=\"navigation-demo\" id=\"demo-navbar\">\n" +
                "  <div class=\"container\">\n" +
                "    <!-- Brand and toggle get grouped for better mobile display -->\n" +
                "    <div class=\"navbar-header\">\n" +
                "      <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#navigation-example-2\">\n" +
                "        <span class=\"sr-only\">Toggle navigation</span>\n" +
                "        <span class=\"icon-bar\"></span>\n" +
                "        <span class=\"icon-bar\"></span>\n" +
                "        <span class=\"icon-bar\"></span>\n" +
                "      </button>\n" +
                "      <a href=\"http://www.uod.edu.sa\">\n" +
                "           <div class=\"logo-container\">\n" +
                "                <!--\n" +
                "                <div class=\"logo\">\n" +
                "                    <img src=\"assets/paper_img/new_logo.png\" alt=\"Creative Tim Logo\">\n" +
                "                </div>\n" +
                "                -->\n" +
                "                <div class=\"brand\">\n" +
                "                    University of Dammam\n" +
                "                </div>\n" +
                "            </div>\n" +
                "      </a>\n" +
                "    </div>\n" +
                "\n" +
                "<!-- Collect the nav links, forms, and other content for toggling -->\n" +
                "    <div class=\"collapse navbar-collapse\" id=\"navigation-example-2\">\n" +
                "      <ul class=\"nav navbar-nav navbar-right\">\n" +
                "          <li>\n" +
                "            <a href=\"#\" class=\"btn btn-default btn-simple\">Home</a>\n" +
                "          </li>\n" +
                "          <li>\n" +
                "            <a href=\"#\" class=\"btn btn-default btn-simple\">Setting</a>\n" +
                "          </li>\n" +
                "          <li>\n" +
                "            <a href=\"#\" class=\"btn btn-default btn-fill\">Logout</a>\n" +
                "          </li>\n" +
                "       </ul>\n" +
                "    </div><!-- /.navbar-collapse -->\n" +
                "  </div><!-- /.container-->\n" +
                "</nav>\n" +
                "\n" +
                "     <div class=\"main\">\n" +
                "    <div class=\"section\">\n" +
                "        <div class=\"container\">\n" +
                "<!--         what is row -->\n" +
                "            <div class=\"row tim-row\">\n" +
                "                <h2 class=\"text-center\">Add Performance Indicators</h2>\n" +
                "                <legend></legend>\n" +
                "                <div class=\"col-md-8 col-md-offset-2\">\n" +
                "                                   <p>Click \"Add\" to enter Performance Indicator</p>\n" +
                "\n" +
                "        <div class=\"panel panel-default\">\n" +
                "            <!-- Default panel contents -->\n" +
                "\n" +
                "            <!-- Table -->\n" +
                "            <table class=\"table\">\n" +
                "                <tr>\n" +
                "                    <th>PI ID</th>\n" +
                "                    <th>Outcome ID</th>\n" +
                "                    <th>Edit</th>\n" +
                "                    <th>Delete</th>\n" +
                "                </tr>\n" +
                "                <tr>" );


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
                out.print("<td><a class=\"btn btn-warning btn-simple\" href=\"#\"><i class=\"fa fa-pencil fa-2x\"></i></a></td>\n");
                out.print("<td><a class=\"btn btn-danger btn-simple\" href=\"#\"><i class=\"fa fa-trash-o fa-2x\"></i></a></td>\n");
                out.print("</tr>");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }






        out.print( " </tr>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "        <button class=\"btn btn-success btn-fill\">Add</button>\n" +
                "        <button class=\"btn btn-primary\">Home Page</button>\n" +
                "        <button class=\"btn btn-primary pull-right\">Finish</button>\n" +
                "\n" +
                "                   \n" +
                "                    <!-- End of col -->\n" +
                "                </div>\n" +
                "                \n" +
                "                <!-- End of row -->\n" +
                "            </div>\n" +
                "\n" +
                "\n" +
                "<!-- Modal Bodies come here -->\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<!--   end modal  -->\n" +
                "\n" +
                "<footer class=\"footer-demo section-dark footer-sticky\">\n" +
                "    <div class=\"container row\">\n" +
                "        \n" +
                "        <div class=\"copyright col-md-8 pull-left\">\n" +
                "           <p class=\"text-left\">Designed and built as a graduation project in <a href=\"http://www.uod.edu.sa\">UOD</a>. All Rights Reversed to ABETAS<br><a href=\"#\">Back to top</a></p>\n" +
                "            \n" +
                "        </div>\n" +
                "        <div class=\"col-md-2 center-block pull-right\"><img class=\"img-responsive \" alt=\"logo\" src=\"img/logo.png\" style=\"margin:10px;\"></div>\n" +
                "\n" +
                "    </div>\n" +
                "</footer>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "    <script src=\"js/jquery-1.10.2.js\" type=\"text/javascript\"></script>\n" +
                "\t<script src=\"js/jquery-ui-1.10.4.custom.min.js\" type=\"text/javascript\"></script>\n" +
                "\n" +
                "\t<script src=\"bootstrap3/js/bootstrap.js\" type=\"text/javascript\"></script>\n" +
                "\t\n" +
                "\t<!--  Plugins -->\n" +
                "\t<script src=\"js/ct-paper-checkbox.js\"></script>\n" +
                "\t<script src=\"js/ct-paper-radio.js\"></script>\n" +
                "\t<script src=\"js/bootstrap-select.js\"></script>\n" +
                "\t<script src=\"js/bootstrap-datepicker.js\"></script>\n" +
                "    <script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js?v=3.exp\"></script>\n" +
                "\t\n" +
                "\t<script src=\"js/ct-paper.js\"></script>    \n" +
                "</html>");

    }
}
