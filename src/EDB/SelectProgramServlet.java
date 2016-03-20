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
            ArrayList<ArrayList<String>> pIListS = dbs.selectPIofSummativeToEvaluate(tid, pid);

            PrintWriter out = response.getWriter();
            for (ArrayList<String> PI : pIListS){


                out.print("<a href=\"index.jsp?page=showGraph&id="+PI.get(0)+"&tid="+tid+"&pid="+pid+"&dataType="+PI.get(2)+"&piname="+EncDec.getEncr(PI.get(1))+"\" class=\"list-group-item\">"+PI.get(1)+"</br><div class=\"pull-right\" >"+PI.get(2)+"</div></br></a>");
            }


            ArrayList<ArrayList<String>> pIListF = dbs.selectPIofFormativeToEvaluate(tid, pid);

            for (ArrayList<String> PI : pIListF){
                out.print("<a href=\"index.jsp?page=showForm&id="+ EncDec.getEncr(PI.get(3)) + "&type="+EncDec.getEncr("formative")+"\" class=\"list-group-item\">" + PI.get(1) + "</br><div class=\"pull-right\" >" + PI.get(2) + "</div></br></br></a>");
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
