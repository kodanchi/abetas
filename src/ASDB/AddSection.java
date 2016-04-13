package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "AddSection",
        urlPatterns = {"/AddSection"})
public class AddSection extends HttpServlet {
    /**
     * insert or update section , then redirect to the section list.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("section").equals("null")) {
            int id=0;
        C_AS_Insert dba = new C_AS_Insert();
        C_AS_Select dbs = new C_AS_Select();
        try {
            id=dba.addSection(Integer.parseInt(request.getParameter("term")),Integer.parseInt(request.getParameter("F_ID")),request.getParameter("courseCode"));

            ArrayList<Integer> piLinks = dbs.selectFFormsofSameCourse(Integer.parseInt(request.getParameter("term")),request.getParameter("courseCode"));
            for(int link: piLinks){
                ArrayList<Integer> piLinkData = dbs.selectPiforNewSectionSameCourse(link);
                int Link_id = dba.addPILink(piLinkData.get(0),piLinkData.get(1),piLinkData.get(2),piLinkData.get(3),request.getParameter("courseCode"),Integer.parseInt(request.getParameter("term")),"Formative");
                dba.addFormF(Link_id,id);
            }

            piLinks = dbs.selectSFormsofSameCourse(Integer.parseInt(request.getParameter("term")),request.getParameter("courseCode"));
            for(int link: piLinks){
                ArrayList<Integer> piLinkData = dbs.selectPiforNewSectionSameCourse(link);
                int Link_id = dba.addPILink(piLinkData.get(0),piLinkData.get(1),piLinkData.get(2),piLinkData.get(3),request.getParameter("courseCode"),Integer.parseInt(request.getParameter("term")),"Summative");
                dba.addFormS(Link_id,id);
            }



            Auditor.add((String)request.getSession().getAttribute("username"),"Added new section (Course ID : "+request.getParameter("courseCode")+")");



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+request.getParameter("cycle")+"&term="+
                request.getParameter("term")+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+
                "&courseName="+request.getParameter("courseName")+"&section="+id);
        }
        else {

            C_AS_Update dba = new C_AS_Update();
            try {
                dba.updateSection(Integer.parseInt(request.getParameter("section")), Integer.parseInt(request.getParameter("F_ID")));
                Auditor.add((String)request.getSession().getAttribute("username"),"Updated section (ID : "+request.getParameter("section")+")");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+request.getParameter("cycle")+"&term="+
                    request.getParameter("term")+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+
                    "&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
