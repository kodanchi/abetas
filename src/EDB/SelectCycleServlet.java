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

            out.print("<a href=\"#terms"+"-"+cid+"\" class=\"list-group-item list-group-item-warning\" data-toggle=\"collapse\"><i class=\"glyphicon glyphicon-chevron-right\"></i>Terms</a>");
            out.print(" <div class=\"list-group  collapse \" id=\"terms"+"-"+cid+"\" >");
            for (ArrayList<String> term : termList){

                out.print("<a href=\"#term-"+term.get(0)+"-"+cid+"\" class=\"list-group-item  \" " +
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
                        "                        $('#term-"+term.get(0)+"-"+cid+"').html(result);\n" +
                        "                        show('page', true);\n" +
                        "                        show('loading', false);\n" +
                        "                        scrollTo('term-"+term.get(0)+"-"+cid+"');" +
                        "                       }\n" +
                        "                    })\n" +
                        "                }\"" +
                        "data-toggle=\"collapse\">\n" +
                        "                            <i class=\"glyphicon glyphicon-chevron-right\"></i>"+term.get(1)+" "+term.get(2)+"\n" +
                        "                        </a>\n" +
                        "                        <div class=\"list-group collapse\" id=\"term-"+term.get(0)+"-"+cid+"\">\n" +
                        "                        </div>" );
            }
            out.print("</div>");

            out.print("<script>");
            out.print("$(function() {\n" +
                    "\n" +
                    "                    $('.list-group-item').on('click', function() {\n" +
                    "                        $('.glyphicon', this)\n" +
                    "                                .toggleClass('glyphicon-chevron-right')\n" +
                    "                                .toggleClass('glyphicon-chevron-down');\n" +
                    "                    });\n" +
                    "\n" +
                    "                });");
            out.print("</script>");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
