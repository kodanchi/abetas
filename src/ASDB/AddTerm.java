package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddTerm",
        urlPatterns = {"/AddTerm"})
public class AddTerm extends HttpServlet {
    String[] TermVal;

    /**
     * insert or update term , then redirect to the term list.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            C_AS_Insert dba = new C_AS_Insert();
            C_AS_Select sdb = new C_AS_Select();
            int id = Integer.parseInt(request.getParameter("cycle"));
            int Termid = 0;
            try {
                if(!sdb.isTermYearExist(request.getParameter("termName"),request.getParameter("fyear")+"-"+request.getParameter("tyear"),id)){
                    Termid = dba.addTerm(request.getParameter("termName"),request.getParameter("fyear")+"-"+request.getParameter("tyear"),id);
                    Auditor.add((String)request.getSession().getAttribute("username"),"Added new term (Cycle ID : "+id+")");

                    response.sendRedirect("/cycle/index.jsp?page=addTerm&cycle="+id);
                }else {
                    TermVal = new  String[]{request.getParameter("termName"),request.getParameter("fyear"),request.getParameter("tyear")};
                    sendErrMsg(request.getParameter("termName")+" with year period of "+request.getParameter("fyear")+"-"+request.getParameter("tyear")+
                            " is exist already",request.getParameter("cycle"),request,response);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        try {
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * show error message
     * @param msg String
     * @param cycle String
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void sendErrMsg(String msg,String cycle,HttpServletRequest request, HttpServletResponse response){



        request.getSession().setAttribute("errMsg",msg);
        request.getSession().setAttribute("TermVal", TermVal);


        try {
            if(request.getParameter("cycle")!= null) {

                response.sendRedirect("/cycle/index.jsp?page=addTerm&cycle="+cycle);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }
}
