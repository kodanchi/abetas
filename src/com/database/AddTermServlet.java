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
@WebServlet(name = "AddTermServlet",
        urlPatterns = {"/61"})
public class AddTermServlet extends HttpServlet {
    ASDB dba;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<!doctype html>\n" +
                "\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n" +
                "\n" +
                "    <title>Add Courses</title>\n" +
                "\n" +
                "    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\" />\n" +
                "\n" +
                "    <link href=\"css/bootstrap.css\" rel=\"stylesheet\" />\n" +
                "    <link href=\"css/ct-paper.css\" rel=\"stylesheet\" />\n" +
                "    <link href=\"css/demo.css\" rel=\"stylesheet\" />\n" +
                "\n" +
                "    <!--     Fonts and icons     -->\n" +
                "    <link href=\"http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\" rel=\"stylesheet\">\n" +
                "    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>\n" +
                "    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div id=\"header\"></div>\n" +
                "\n" +
                "    <div class=\"main\">\n" +
                "        <div class=\"section\">\n" +
                "            <div class=\"container\">\n" +
                "                <!-- what is row -->\n" +
                "                <div class=\"row tim-row\">\n" +
                "                    <h2 class=\"text-center\">Cycle Configuration</h2>\n" +
                "                    <legend></legend>\n" +
                "                    <div class=\"col-md-10 col-md-offset-1\">\n" +
                "                        <p class=\"text-center\">You need to enter the terms of the cycle</p>\n" +
                "\n" +
                "                        <div>\n" +
                "                            <!-- Default panel contents -->\n" +
                "\n" +
                "                            <!-- Table -->\n" +
                "                            <div class=\"row tim-row\">\n" +
                "                                <div class=\"col-md-6\">\n" +
                "                                    <form>\n" +
                "                                        <div class=\"row tim-row\">\n" +
                "                                            <div class=\"col-md-6\">\n" +
                "                                                <div class=\"form-group\">\n" +
                "                                                    <label>Name</label>\n" +
                "                                                    <input type=\"text\" name=\"termsname\" class=\"form-control\" required>\n" +
                "\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "\n" +
                "                                            <div class=\"col-md-6\">\n" +
                "                                                <div class=\"form-group\">\n" +
                "                                                    <label>Year</label>\n" +
                "                                                    <div class=\"dropdown\">\n" +
                "                                                        <a href=\"#\" class=\"btn dropdown-toggle\" data-toggle=\"dropdown\">\n" +
                "                      Year \n" +
                "                      <b class=\"caret\"></b>\n" +
                "                  </a>\n" +
                "                                                        <ul class=\"dropdown-menu\">\n" +
                "                                                            <li><a href=\"#\">2014-2015</a></li>\n" +
                "                                                            <li><a href=\"#\">2015-2016</a></li>\n" +
                "                                                        </ul>\n" +
                "                                                    </div>\n" +
                "\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "\n" +
                "                                        </div>\n" +
                "                                        <button type=\"submit\" class=\"btn btn-success\">Add</button>\n" +
                "\n" +
                "                                    </form>\n" +
                "                                </div>\n" +
                "\n" +
                "                                <div class=\"col-md-6 panel panel-default\">\n" +
                "                                    <table class=\"table\">\n" +
                "                                        <tr>\n" +
                "                                            <th>Name</th>\n" +
                "                                            <th>Year</th>\n" +
                "                                            <th>edit</th>\n" +
                "                                            <th>delete</th>\n" +
                "                                        </tr>\n" +
                "                                        <tr>" );



        dba=new ASDB();
        try {
            ArrayList<ArrayList<String>> rs = dba.selectAddTerm();
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





















        out.println("</tr>\n" +
                "                                    </table>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                        </div>\n" +
                "                        <button class=\"btn btn-success pull-right\">Cancel</button>\n" +
                "\n" +
                "                        <button class=\"btn btn-success pull-right\">Next</button>\n" +
                "\n" +
                "\n" +
                "                        <!-- End of col -->\n" +
                "                    </div>\n" +
                "\n" +
                "                    <!-- End of row -->\n" +
                "                </div>\n" +
                "\n" +
                "\n" +
                "                <!-- Modal Bodies come here -->\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <!--   end modal  -->\n" +
                "\n" +
                "    <div id=\"footer\"></div>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "<script src=\"js/jquery-1.10.2.js\" type=\"text/javascript\"></script>\n" +
                "<script src=\"js/jquery-ui-1.10.4.custom.min.js\" type=\"text/javascript\"></script>\n" +
                "<script src=\"js/bootstrap.js\" type=\"text/javascript\"></script>\n" +
                "\n" +
                "<!--  Plugins -->\n" +
                "<script src=\"js/ct-paper-checkbox.js\"></script>\n" +
                "<script src=\"js/ct-paper-radio.js\"></script>\n" +
                "<script src=\"js/bootstrap-select.js\"></script>\n" +
                "<script src=\"js/bootstrap-datepicker.js\"></script>\n" +
                "\n" +
                "<script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js?v=3.exp\"></script>\n" +
                "\n" +
                "<script src=\"js/ct-paper.js\"></script>\n" +
                "<script>\n" +
                "    $(\"#slider-range\").slider({\n" +
                "        range: true,\n" +
                "        min: 0,\n" +
                "        max: 500,\n" +
                "        values: [75, 300],\n" +
                "    });\n" +
                "    $(\"#slider-default\").slider({\n" +
                "        value: 70,\n" +
                "        orientation: \"horizontal\",\n" +
                "        range: \"min\",\n" +
                "        animate: true\n" +
                "    });\n" +
                "    $('.btn-tooltip').tooltip('show');\n" +
                "    $('.radio').on('toggle', function () {});\n" +
                "\n" +
                "\n" +
                "    $(function () {\n" +
                "        $(\"#header\").load(\"header.html\");\n" +
                "        $(\"#footer\").load(\"footer.html\");\n" +
                "    });\n" +
                "</script>\n" +
                "\n" +
                "</html>");

    }
}
