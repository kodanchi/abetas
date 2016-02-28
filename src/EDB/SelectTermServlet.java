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
@WebServlet(name = "SelectTermServlet", urlPatterns = {"/SelectTermServlet"})
public class SelectTermServlet extends HttpServlet {
    private E_Select dbs;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/////////////////////////SelectTermServlet ");
        response.setContentType("text/plain");
        dbs = new E_Select();
        int tid = Integer.parseInt(request.getParameter("tid"));
        try {
            ArrayList<ArrayList<String>> pIList = dbs.selectPIToEvaluate(tid);

            PrintWriter out = response.getWriter();
            for (ArrayList<String> PI : pIList){
                out.print("<a href=\"index.jsp?page=showGraph&id="+PI.get(0)+"\" class=\"list-group-item\">"+PI.get(1)+"<div class=\"pull-right\" >"+PI.get(2)+"</div></a>");
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
