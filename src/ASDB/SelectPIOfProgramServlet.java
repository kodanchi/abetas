package ASDB;

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
 * Created by Mojahed on 3/13/2016.
 */
@WebServlet(name = "SelectPIOfProgramServlet" , urlPatterns = {"/selectPI"})
public class SelectPIOfProgramServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/////////////////////////SelectPIOfProgramServlet ");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        C_AS_Select bselect = new C_AS_Select();
        try {
            String id = request.getParameter("cid");
            String pid = request.getParameter("pid");
            System.out.println("cid : "+id);
            System.out.println("pid : "+pid);

            ArrayList<String> rs = bselect.selectPerformanceIndicatorsForCycle(Integer.parseInt(pid), Integer.parseInt(id));

            out.print("<p>Click \"Add\" to add new performance indicator for ");
            C_AS_Select ssselect = new C_AS_Select();
            try {
                String Pname = ssselect.selectProgramName(Integer.parseInt(pid));
                out.print(Pname);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.print(" program</p>");
            int size = 0;

            out.print("<div class=\"panel panel-default\">\n" +
                    "                            <!-- Default panel contents -->\n" +
                    "\n" +
                    "                            <!-- Table -->\n" +
                    "                            <table class=\"table table-hover table-striped table-bordered text-center\">\n" +
                    "                                <tr>\n" +
                    "\n" +
                    "                                    <th>Label</th>\n" +
                    "                                    <th>Performance Indicator</th>\n" +
                    "                                    <th>Edit</th>\n" +
                    "                                    <th>Delete</th>\n" +
                    "\n" +
                    "                                </tr>" +
                    "" +
                    "" +
                    "");


            C_AS_Select aselect = new C_AS_Select();
            ArrayList<ArrayList<String>> rsss = aselect.selectPerformanceIndicators(Integer.parseInt(pid), Integer.parseInt(id));
            ArrayList<String> rsRow;

            for (int i = 0; i < rsss.size(); i++) {
                //rsRow = new ArrayList<String>();
                rsRow = rsss.get(i);
                size = rsss.size();
                out.print("<tr>");
                for (int j = 0; j < rsRow.size(); j++) {
                    out.print("<td>" + rsRow.get(j) + "</td>");
                }
                out.print("<td>" +
                        "                            <form method=\"post\" action=\"index.jsp\">\n" +
                        "                            <input name=\"page\" value=\"updatePI\" hidden />\n" +
                        "                            <input name=\"PILabel\" value=\"" + rsRow.get(0) + "\" hidden />\n" +
                        "                            <input name=\"PIValue\" value=\"" + rsRow.get(1) + "\" hidden />\n" +
                        "                            <input name=\"cycle\" value=\"" + id + "\" hidden />\n" +
                        "                            <input name=\"programID\" value=\"" + pid + "\" hidden />\n" +
                        "                            <button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button>\n" +
                        "                               </td>" +
                        "                            <td></form>" +
                        "                            <form method=\"post\" class=\"delForm\" action=\"/DeletePI\">\n" +
                        "                            <input name=\"page\" id=\"page\" value=\"delete\" hidden />\n" +
                        "                            <input name=\"PILabel\" value=\"" + rsRow.get(0) + "\" hidden />\n" +
                        "                            <input name=\"cycle\" value=\"" + id + "\" hidden />\n" +
                        "                            <input name=\"programID\" value=\"" + pid + "\" hidden />\n" +
                        "                            <button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button>\n" +
                        "                        </form></td>" +
                        "</tr>");
            }

            out.print("</table></div>\n");




            out.print(" <a class=\"btn btn-primary btn-fill pull-left\"  onclick=\"importPopup('index.jsp?page=addPI&cycle="+id+"&programID="+pid+"'," +
                    "'index.jsp?&cycle="+id+"&programID="+pid+"&page=import&data=pis'," +
                    "'Add Performance Indicators'," +
                    "'If you have the Performance Indicators details in an Excel sheet, you can import the file to add them all at once');\"  >Add</a>" +
                    "<input type=\"hidden\" name=\"programID\" value=\""+pid+"\">");

            if(size>0) {
                out.print("<a class=\"btn btn-primary btn-fill pull-right\" href=\"index.jsp?page=rubricNames&cycle=" + id + "&programID=" + pid + "\">Next</a>");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
