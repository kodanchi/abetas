package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ibrahim Abuaqel on 2/11/2016.
 */
@WebServlet(name = "AddPILinks",
        urlPatterns = {"/AddPILinks"})
public class AddPILinks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("OutValue").equals("null")) {
            System.out.println("#########################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE  Course  "+  request.getParameter("Course"));

            AS_Insert dba = new AS_Insert();
            AS_Select sdba = new AS_Select();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            int programID = 0;
            int R=0;
            int Link_id=0;
            String type="";
            System.out.println("##########################################################    "+request.getParameter("Out")+"          "+request.getParameter("PI")+"          "+request.getParameter("programID")+"          "+request.getParameter("Course")+"   "+Termid+"       "+request.getParameter("Type"));

            try {
                System.out.println("ttrttttttttttttttttttttttttt  Program id          " + request.getParameter("programID") + "ttrttttttttttttttttttttttttt           ");
                R=dba.addRubric(request.getParameter("firstR"),request.getParameter("firstD"),request.getParameter("secondR"),request.getParameter("secondD"),request.getParameter("thirdR"),request.getParameter("thirdD"),request.getParameter("forthR"),request.getParameter("forthD"));
                Link_id=dba.addPILink(Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),Integer.parseInt(request.getParameter("programID")), R, request.getParameter("Course"),Integer.parseInt(Termid),request.getParameter("Type"));
                if (Link_id!=0) {
                    System.out.println("in if "+Link_id);

                    type = sdba.selectFormType(Link_id);
                    if (type.equals("Formative")) {
                        System.out.println("in if F");
                        dba.addFormF(Link_id);
                    } else if (type.equals("Summative")) {
                        System.out.println("in if S");
                        dba.addFormS(Link_id, Integer.parseInt(request.getParameter("STshold")));
                    } else {
                        System.out.println("id is not set");
                    }
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
            System.out.println("ttrttttttttttttttttttttttttt  Rubric id          " + R + "ttrttttttttttttttttttttttttt           ");
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            try {
                response.sendRedirect("/cycle/index.jsp?cycle="+id+"&term="+Termid+"&page=LinkPIOutList&programID="+request.getParameter("programID"));
                //request.getRequestDispatcher("/cycle/index.jsp?page=LinkPIOutList&programID="+request.getParameter("programID")).forward(request, response);
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        } else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            AS_Insert dba = new AS_Insert();
            AS_Update dbu = new AS_Update();
            AS_Select sdba = new AS_Select();
            AS_Delete dbd = new AS_Delete();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            int Link_id = Integer.parseInt(request.getParameter("LinkID"));

            System.out.println("ttrttttttttttttttttttttttttt  PI name          " + request.getParameter("PI") + "   ttrttttttttttttttttttttttttt           ");
            System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww  PI id          " + Integer.parseInt(request.getParameter("OutValue")) + "   wwwwwwwwwwwwwwww           ");
            String type="";
            try {
                System.out.println("##########################################################  "+request.getParameter("LinkID")+"    "+request.getParameter("Out")+"          "+request.getParameter("PI")+"          "+request.getParameter("programID")+"          "+request.getParameter("programID")+"      "+request.getParameter("RubricValue")+"  "+request.getParameter("Course")+"   "+Termid+"    "+request.getParameter("Type"));

                dbu.updatePILink(Integer.parseInt(request.getParameter("LinkID")),Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),Integer.parseInt(request.getParameter("programID")),Integer.parseInt(request.getParameter("RubricValue")),request.getParameter("Course"),Integer.parseInt(Termid),request.getParameter("Type"));
                dbu.updateRubrics(request.getParameter("firstR"),request.getParameter("firstD"),request.getParameter("secondR"),request.getParameter("secondD"),request.getParameter("thirdR"),request.getParameter("thirdD"),request.getParameter("forthR"),request.getParameter("forthD"),Integer.parseInt(request.getParameter("RubricValue")));
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");

                type = sdba.selectFormType(Link_id);
                if(!request.getParameter("oldTypeValue").equals(request.getParameter("Type"))){
                    if (type.equals("Formative")) {
                        System.out.println("in if F");
                        dbd.deleteForm(Link_id,1);
                        dba.addFormF(Link_id);
                    } else if (type.equals("Summative")) {
                        System.out.println("in if S");
                        dbd.deleteForm(Link_id,0);
                        dba.addFormS(Link_id, Integer.parseInt(request.getParameter("STshold")));
                    } else {
                        System.out.println("id is not set");
                    }
                }else if (type.equals("Summative")){
                    dbu.updateFormThredshold(Link_id,Integer.parseInt(request.getParameter("STshold")));
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


            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            try {
                response.sendRedirect("/cycle/index.jsp?cycle="+id+"&term="+Termid+"&page=LinkPIOutList&programID="+request.getParameter("programID"));
                //request.getRequestDispatcher("/cycle/index.jsp?page=LinkPIOutList&programID="+request.getParameter("programID")).forward(request, response);
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
