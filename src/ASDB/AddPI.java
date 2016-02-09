package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 2/9/2016.
 */
@WebServlet(name = "AddPI",
        urlPatterns = {"/AddPI"})
public class AddPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################      "+request.getParameter("PIValue"));

        if (request.getParameter("PIValue").equals("null")) {
            System.out.println("#########################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

            AS_Insert dba = new AS_Insert();
            AS_Select sdba = new AS_Select();
            String id = request.getParameter("cycleId");
            String Termid = request.getParameter("Termid");
            int programID = 0;

            try {
                System.out.println("ttrttttttttttttttttttttttttt  Program name          " + request.getParameter("programName") + "ttrttttttttttttttttttttttttt           ");

                programID=sdba.selectProgramID(request.getParameter("programName"));
                if (programID!=0) {
                    dba.addPI(request.getParameter("PI"), programID);
                }else {
                    //Display error message.
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
            System.out.println("ttrttttttttttttttttttttttttt  Programid          " + programID + "ttrttttttttttttttttttttttttt           ");
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            try {
                request.getRequestDispatcher("/cycle/index.jsp?page=piList").forward(request, response);
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        } else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            AS_Update dba = new AS_Update();
            String id = request.getParameter("cycleId");
            String Termid = request.getParameter("Termid");
            System.out.println("ttrttttttttttttttttttttttttt  PI name          " + request.getParameter("PI") + "   ttrttttttttttttttttttttttttt           ");
            System.out.println("ttrttttttttttttttttttttttttt  PI id          " + Integer.parseInt(request.getParameter("PILabel")) + "   ttrttttttttttttttttttttttttt           ");
            try {

                dba.updatePI(request.getParameter("PI"),Integer.parseInt(request.getParameter("PILabel")));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/


            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            try {
                request.getRequestDispatcher("/cycle/index.jsp?page=piList").forward(request, response);
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
