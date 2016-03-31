package EDB;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mojahed on 2/27/2016.
 *
 * http://stackoverflow.com/questions/23947992/how-to-encrypt-encode-url-parameters-in-jsp
 */
@WebServlet(name = "SelectProgramServlet", urlPatterns = {"/SelectProgramServlet"})
public class SelectProgramServlet extends HttpServlet {
    private E_Select dbs;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/////////////////////////SelectProgramServlet ");
        response.setContentType("text/plain");
        dbs = new E_Select();
        int tid = Integer.parseInt(request.getParameter("tid"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        try {
            PrintWriter out = response.getWriter();

            //ArrayList<ArrayList<String>> pIListF = dbs.selectOutcomesToEvaluate(tid, pid);



            ArrayList<ArrayList<String>> outcomeList =  dbs.selectOutcomesToEvaluate(tid, pid);
            out.print("<a href=\"#pis"+"-"+tid+"\" class=\"list-group-item list-group-item-warning\" data-toggle=\"collapse\"><i class=\"glyphicon glyphicon-chevron-right\"></i>Outcomes</a>");
            out.print(" <div class=\"list-group collapse \" id=\"pis"+"-"+tid+"\" >");
            for (ArrayList<String> outcome : outcomeList){
                out.print("<a href=\"#outcomeList\"  class=\"list-group-item \" " +
                        "onclick=\"new function (){\n" +
                        "                    show('page', false);\n" +
                        "                    show('loading', true);\n" +
                        "                    $.ajax({\n" +
                        "                       type: 'POST',\n" +
                        "                       data:{" +
                        "                           tid: "+tid+"," +
                        "                           pid: "+pid+"," +
                        "                           oid: "+outcome.get(0)+"," +
                        "                           oName:'"+ EncDec.getEncr(outcome.get(1))+"'"+
                        "                       },\n" +
                        "                       url:'/SelectOutcomeServlet',\n" +
                        "                       success: function(result){\n" +
                        "                        $('#outcomeList').html(result);\n" +
                        "                        show('page', true);\n" +
                        "                        show('loading', false);\n" +
                        "                        scrollTo('outcomeList');" +
                        "                       }\n" +
                        "                    });\n" +
                        "                }\"" +
                        ">" +outcome.get(1)+"\n" +
                        "                        </a>\n"
                         );
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
