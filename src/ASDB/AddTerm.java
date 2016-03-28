package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ibrahim Abuaqel on 2/7/2016.
 */
@WebServlet(name = "AddTerm",
        urlPatterns = {"/AddTerm"})
public class AddTerm extends HttpServlet {
    String[] TermVal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################"+ request.getParameter("fyear")+"-"+request.getParameter("tyear"));

            C_AS_Insert dba = new C_AS_Insert();
            C_AS_Select sdb = new C_AS_Select();
            int id = Integer.parseInt(request.getParameter("cycle"));
            int Termid = 0;
            try {
                if(!sdb.isTermYearExist(request.getParameter("termName"),request.getParameter("fyear")+"-"+request.getParameter("tyear"),id)){
                    Termid = dba.addTerm(request.getParameter("termName"),request.getParameter("fyear")+"-"+request.getParameter("tyear"),id);
                    System.out.println("ttrttttttttttttttttttttttttt  id          " +String.valueOf(id)+"ttrttttttttttttttttttttttttt  Termid          " +String.valueOf(Termid));
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
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
        try {
            //request.getSession().setAttribute("Termid",String.valueOf(Termid));
            //request.getRequestDispatcher("/cycle/index.jsp?page=addRubrics").forward(request,response);
            //response.sendRedirect("/cycle/index.jsp?page=includeCourse&cycle="+id+"&term="+Termid);
        }catch (NullPointerException e){
            e.fillInStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void sendErrMsg(String msg,String cycle,HttpServletRequest request, HttpServletResponse response){


        System.out.println("ErrMsg : "+msg);

        System.out.println("session is : "+request.getSession().getId());
        request.getSession().setAttribute("errMsg",msg);
        request.getSession().setAttribute("TermVal", TermVal);


        try {
            if(request.getParameter("cycle")!= null) {

                response.sendRedirect("/cycle/index.jsp?page=addTerm&cycle="+cycle);
            }else {

                /*String termName = request.getParameter("termName");
                String fyear = request.getParameter("fyear");
                String tyear = request.getParameter("tyear");*/
                //request.getRequestDispatcher("/program/index.jsp?page=update").forward(request, response);
                //response.sendRedirect("/program/index.jsp?page=updateLink&name="+cycle+"&id="+pid+"&Linkid="+ Linkid+"&ObjLinkValue="+ObjLinkValue+"&OutLinkValue="+OutLinkValue);
            }
        } /*catch (ServletException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }
}
