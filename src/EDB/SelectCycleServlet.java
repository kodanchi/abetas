package EDB;

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
 * Created by Mojahed on 2/27/2016.
 */
@WebServlet(name = "SelectCycleServlet", urlPatterns = {"/SelectCycleServlet"})
public class SelectCycleServlet extends HttpServlet {
    private E_Select dbs;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        dbs = new E_Select();
        int cid = Integer.parseInt(request.getParameter("cid"));
        try {
            ArrayList<ArrayList<String>> termList = dbs.selectTermToEvaluate(cid);

            PrintWriter out = response.getWriter();
            for (ArrayList<String> term : termList){
                out.print("<a href=\"#\" class=\"list-group-item \"" +
                        "onclick=\"new function (){\n" +
                        "                    show('page', false);\n" +
                        "                    show('loading', true);\n" +
                        "                    $.ajax({\n" +
                        "                       type: 'POST',\n" +
                        "                       data:{" +
                        "                           tid: "+term.get(0)+
                        "                       },\n" +
                        "                       url:'/SelectTermServlet',\n" +
                        "                       success: function(result){\n" +
                        "                        $('#pIList').html(result);\n" +
                        "                        show('page', true);\n" +
                        "                        show('loading', false);\n" +
                        "                       }\n" +
                        "                    })\n" +
                        "                }\"" +
                        "\">"+term.get(1)+" "+term.get(2)+"</a>");
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
