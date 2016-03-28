package ASDB;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
 * Created by Ibrahim Abuaqel on 2/1/2016.
 */
@WebServlet(name = "AddProgram",
        urlPatterns = {"/Add Program"})
public class AddProgram extends HttpServlet {
    String[] programVal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        System.out.println(request.getParameter("id")+"           "+request.getParameter("name")+"             AdProg Servlet");

        if (request.getParameter("ProgramName").equals("null")) {
            System.out.println("#########################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

            P_AS_Insert dba=new P_AS_Insert();
            P_AS_Select dbaS=new P_AS_Select();

            ArrayList<String> data = new ArrayList<String>();

            int id=0;



            programVal = new String[]{request.getParameter("Pname"),request.getParameter("Pmission")};
            try {

                if(!dbaS.isProgramNameExist(request.getParameter("Pname"))){
                    data=dba.addProgramm(request.getParameter("Pname"), request.getParameter("Pmission"));
                    id=dbaS.selectProgramID(request.getParameter("Pname"));
                    Auditor.add((String)request.getSession().getAttribute("username"),"Added new Program (ID : "+id+")");

                    response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                    response.setHeader("Location", "/program/index.jsp?page=ObjList&name="+data.get(0)+"&id="+String.valueOf(id));
                }else {
                    sendErrMsg("Program name exist",request.getParameter("Pname"),request.getParameter("id"),request,response);
                }

            } catch (ClassNotFoundException e) {
                //e.printStackTrace();

                System.out.println("data error : "+e.getMessage());
            } catch (MySQLIntegrityConstraintViolationException e) {

                System.out.println("SQL data error : "+e.getMessage());
                if(e.getMessage().startsWith("Duplicate")){
                    sendErrMsg("Program name exist",request.getParameter("Pname"),request.getParameter("id"),request,response);
                }
                //e.printStackTrace();
            } catch (Exception e) {

                //e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");
    //System.out.println("ttrttttttttttttttttttttttttt name     "+ data.get(0)+"    id          " +String.valueOf(id));

        }else {
            P_AS_Select dbaS=new P_AS_Select();
            ArrayList<String> data = new ArrayList<String>();

            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            System.out.println(request.getParameter("Objid")+"            AdObj Servlet IDIDIDIDIDDIIDID");

            String name="";
            P_AS_Update dba = new P_AS_Update();
            //P_AS_Select dbaS=new P_AS_Select();
            try {
                programVal = new String[]{request.getParameter("Pname"),request.getParameter("Pmission")};
                if(!dbaS.isProgramNameExistExcept(request.getParameter("Pname"), Integer.parseInt(request.getParameter("id")))){
                    //id=dbaS.selectProgram(request.getParameter("Pname"));
                    System.out.println(request.getParameter("id") + "           " + request.getParameter("Pname") + "         " + request.getParameter("Pmission") + "        Update   AdObj Servlet");
                    dba.updateProgram(Integer.parseInt((request.getParameter("id"))), request.getParameter("Pname"), request.getParameter("Pmission"));

                    name = dbaS.selectProgramName(Integer.parseInt((request.getParameter("id"))));
                    Auditor.add((String)request.getSession().getAttribute("username"),"Updated Program (ID : "+request.getParameter("id")+")");

                    //System.out.println(data.get(0)+"                vdgfsg            "+data.get(1));
                    response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                    //response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
                    response.setHeader("Location", "/program/index.jsp?page=ObjList&name="+name+"&id="+request.getParameter("id"));
                }else {
                    sendErrMsg("Program name exist",request.getParameter("Pname"),request.getParameter("id"),request,response);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (MySQLIntegrityConstraintViolationException e) {
                System.out.println("SQL data error : "+e.getSQLState());
                //e.printStackTrace();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            //out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void sendErrMsg(String msg,String pname,String pid,HttpServletRequest request, HttpServletResponse response){


        System.out.println("ErrMsg : "+msg);

        System.out.println("session is : "+request.getSession().getId());
        request.getSession().setAttribute("errMsg",msg);
        request.getSession().setAttribute("programVal",programVal);


        try {
            if(request.getParameter("ProgramName").equals("null")) {
                //request.getRequestDispatcher("/program/index.jsp?page=add").forward(request, response);
                /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location","/program/index.jsp?page=add");*/
                response.sendRedirect("/program/index.jsp?page=add");
            }else {
                //request.getRequestDispatcher("/program/index.jsp?page=update").forward(request, response);
                response.sendRedirect("/program/index.jsp?pname="+pname+"&id="+pid+"&page=update");
            }
        } /*catch (ServletException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }
}