package ASDB;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "AddProgram",
        urlPatterns = {"/Add Program"})
public class AddProgram extends HttpServlet {
    String[] programVal;

    /**
     * insert or update program , then redirect to the program list.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("ProgramName").equals("null")) {

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

            } catch (MySQLIntegrityConstraintViolationException e) {

                if(e.getMessage().startsWith("Duplicate")){
                    sendErrMsg("Program name exist",request.getParameter("Pname"),request.getParameter("id"),request,response);
                }
            } catch (Exception e) {

            }

        }else {
            P_AS_Select dbaS=new P_AS_Select();


            String name="";
            P_AS_Update dba = new P_AS_Update();
            try {
                programVal = new String[]{request.getParameter("Pname"),request.getParameter("Pmission")};
                if(!dbaS.isProgramNameExistExcept(request.getParameter("Pname"), Integer.parseInt(request.getParameter("id")))){
                    dba.updateProgram(Integer.parseInt((request.getParameter("id"))), request.getParameter("Pname"), request.getParameter("Pmission"));

                    name = dbaS.selectProgramName(Integer.parseInt((request.getParameter("id"))));
                    Auditor.add((String)request.getSession().getAttribute("username"),"Updated Program (ID : "+request.getParameter("id")+")");

                    response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                    response.setHeader("Location", "/program/index.jsp?page=ObjList&name="+name+"&id="+request.getParameter("id"));
                }else {
                    sendErrMsg("Program name exist",request.getParameter("Pname"),request.getParameter("id"),request,response);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (MySQLIntegrityConstraintViolationException e) {
            } catch (SQLException e) {
            }



        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * show error message
     * @param msg String
     * @param pname String
     * @param pid String
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void sendErrMsg(String msg,String pname,String pid,HttpServletRequest request, HttpServletResponse response){



        request.getSession().setAttribute("errMsg",msg);
        request.getSession().setAttribute("programVal",programVal);


        try {
            if(request.getParameter("ProgramName").equals("null")) {
                response.sendRedirect("/program/index.jsp?page=add");
            }else {
                response.sendRedirect("/program/index.jsp?pname="+pname+"&id="+pid+"&page=update");
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }
}