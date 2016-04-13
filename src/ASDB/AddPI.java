package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddPI",
        urlPatterns = {"/AddPI"})
public class AddPI extends HttpServlet {
    String[] PVal;

    /**
     * insert or update performance indicator , then redirect to the performance indicator list.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("PIValue").equals("null")) {
            C_AS_Insert dba = new C_AS_Insert();
            C_AS_Select sdba = new C_AS_Select();
            String id = request.getParameter("cycle");
            int programID = 0;

            try {
                programID=sdba.selectProgramID(request.getParameter("programName"));
                if(sdba.isPIExist(request.getParameter("PI"), programID, Integer.parseInt(id))){
                    PVal = new String[]{request.getParameter("PI")};
                    sendErrMsg(request.getParameter("PI")+" already existed",request.getParameter("cycle"),request,response);
                }else {
                    if (programID!=0) {
                        dba.addPI(request.getParameter("PI"), Double.parseDouble(request.getParameter("STshold")), programID, Integer.parseInt(id));
                        Auditor.add((String)request.getSession().getAttribute("username"),"Added new Performance Indicator (Cycle ID : "+id+")");

                    }

                    response.sendRedirect("/cycle/index.jsp?page=piList&cycle="+id+"&programID="+request.getParameter("programID"));

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        } else {
            C_AS_Update dba = new C_AS_Update();
            C_AS_Select dbs = new C_AS_Select();
            String id = request.getParameter("cycle");
            try {

                if(dbs.isPIExistExcept(request.getParameter("PI"),Integer.parseInt(request.getParameter("programID")),
                        Integer.parseInt(id),Integer.parseInt(request.getParameter("PILabel")))){
                    PVal = new String[]{request.getParameter("PI")};
                    sendErrMsg(request.getParameter("PI")+" is already existed",request.getParameter("cycle"),request,response);

                }else {
                    dba.updatePI(request.getParameter("PI"),Double.parseDouble(request.getParameter("STshold")),Integer.parseInt(request.getParameter("PILabel")));
                    Auditor.add((String)request.getSession().getAttribute("username"),"Updated Performance Indicator (ID : "+request.getParameter("PILabel")+")");

                    response.sendRedirect("/cycle/index.jsp?page=piList&cycle="+id+"&programID="+request.getParameter("programID"));

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {

            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * send error message
     * @param msg String
     * @param cycle String
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void sendErrMsg(String msg,String cycle,HttpServletRequest request, HttpServletResponse response){

        request.getSession().setAttribute("errMsg",msg);
        request.getSession().setAttribute("PVal", PVal);

        try {
            if (request.getParameter("PIValue").equals("null")) {
                response.sendRedirect("/cycle/index.jsp?page=addPI&cycle="+cycle+
                        "&programID="+request.getParameter("programID"));
            }else {
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location","/cycle/index.jsp?page=updatePI&cycle="+cycle+
                        "&programID="+request.getParameter("programID")+"&PILabel="+request.getParameter("PILabel")+"&PIValue="+request.getParameter("PI"));
            }

        }  catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

}
