package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ibrahim Abuaqel on 2/9/2016.
 */
@WebServlet(name = "AddPI",
        urlPatterns = {"/AddPI"})
public class AddPI extends HttpServlet {
    String[] PVal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################      "+request.getParameter("PIValue"));

        if (request.getParameter("PIValue").equals("null")) {
            System.out.println("#########################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

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
                    System.out.println("ttrttttttttttttttttttttttttt  Program name          " + request.getParameter("programName") + "ttrttttttttttttttttttttttttt           "+"       ssssss "+ Integer.parseInt(request.getParameter("Thresh"))+"        xxxxx"+request.getParameter("Thresh") );
                    if (programID!=0) {
                        dba.addPI(request.getParameter("PI"), Integer.parseInt(request.getParameter("Thresh")), programID, Integer.parseInt(id));
                    }else {
                        //Display error message.
                    }

                    response.sendRedirect("/cycle/index.jsp?page=piList&cycle="+id+"&programID="+request.getParameter("programID"));

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
                //request.getRequestDispatcher("/cycle/index.jsp?page=piList").forward(request, response);

            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        } else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            C_AS_Update dba = new C_AS_Update();
            C_AS_Select dbs = new C_AS_Select();
            String id = request.getParameter("cycle");
            System.out.println("ttrttttttttttttttttttttttttt  PI name          " + request.getParameter("PI") + "   ttrttttttttttttttttttttttttt           ");
            System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww  PI id          " + Integer.parseInt(request.getParameter("PILabel")) + "   wwwwwwwwwwwwwwww           ");
            try {

                if(dbs.isPIExistExcept(request.getParameter("PI"),Integer.parseInt(request.getParameter("programID")),
                        Integer.parseInt(id),Integer.parseInt(request.getParameter("PILabel")))){
                    PVal = new String[]{request.getParameter("PI")};
                    sendErrMsg(request.getParameter("PI")+" is already existed",request.getParameter("cycle"),request,response);

                }else {
                    dba.updatePI(request.getParameter("PI"),Integer.parseInt(request.getParameter("Thresh")),Integer.parseInt(request.getParameter("PILabel")));
                    response.sendRedirect("/cycle/index.jsp?page=piList&cycle="+id+"&programID="+request.getParameter("programID"));

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
                //request.getRequestDispatcher("/cycle/index.jsp?page=piList").forward(request, response);

            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void sendErrMsg(String msg,String cycle,HttpServletRequest request, HttpServletResponse response){

        System.out.println("ErrMsg : "+msg);

        System.out.println("session is : "+request.getSession().getId());
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
                /*response.sendRedirect("/cycle/index.jsp?page=updatePI&cycle="+cycle+"&term="+request.getParameter("term")+
                        "&programID="+request.getParameter("programID"));*/
            }
            /*if(request.getParameter("cycle")!= null) {

                //response.sendRedirect("/cycle/index.jsp?page=addTerm&cycle="+cycle);
                response.sendRedirect("/cycle/index.jsp?page=addPI&cycle="+cycle+"&term="+request.getParameter("term")+
                        "&programID="+request.getParameter("programID"));
            }else {

                *//*String termName = request.getParameter("termName");
                String fyear = request.getParameter("fyear");
                String tyear = request.getParameter("tyear");*//*
                //request.getRequestDispatcher("/program/index.jsp?page=update").forward(request, response);
                //response.sendRedirect("/program/index.jsp?page=updateLink&name="+cycle+"&id="+pid+"&Linkid="+ Linkid+"&ObjLinkValue="+ObjLinkValue+"&OutLinkValue="+OutLinkValue);
            }*/
        } /*catch (ServletException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

}
