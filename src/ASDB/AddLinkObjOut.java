package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 2/3/2016.
 */
@WebServlet(name = "AddLinkObjOut",
        urlPatterns = {"/Add Link Outcome and Objective"})
public class AddLinkObjOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################LLLLLLLLLLLLLL");
        System.out.println(request.getParameter("id")+"           "+request.getParameter("name")+"             AdLinkO_O Servlet");
        //ArrayList<String> data = new ArrayList<String>();
        if (request.getParameter("ObjLinkValue").equals("null")) {
            int id=0;
        P_AS_Insert dba=new P_AS_Insert();
        P_AS_Select dbaS=new P_AS_Select();
        try {
            if(!dbaS.isExistLinkObj_Out(Integer.parseInt(request.getParameter("Obj")),Integer.parseInt(request.getParameter("Out")),
                    Integer.parseInt(request.getParameter("id")))){
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                System.out.println("        "+request.getParameter("id")+"        LLLLLLLLLLLLLLLLL      Obj      "+Integer.parseInt(request.getParameter("Obj"))+"        Out            "+Integer.parseInt(request.getParameter("Out")));
                dba.addLinkObj_Out(Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("Obj")),Integer.parseInt(request.getParameter("id")));
                Auditor.add((String)request.getSession().getAttribute("username"),"added new Outcome/Objective Link (Program ID: "+request.getParameter("id")+")");
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location", "/program/index.jsp?page=LinkOutObj&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
            }else {
                sendErrMsg("Link is exist",request.getParameter("name"),request.getParameter("id"),request,response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        //out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");



        }
        else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            System.out.println(request.getParameter("Outid")+"            AdObj Servlet IDIDIDIDIDDIIDID");

            int id = 0;
            P_AS_Update dba = new P_AS_Update();
            P_AS_Select dbaS=new P_AS_Select();
            try {
                if(!dbaS.isExistLinkObj_OutExcept(Integer.parseInt(request.getParameter("Obj")),Integer.parseInt(request.getParameter("Out")),
                        Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("Out")))){

                    //id=dbaS.selectProgram(request.getParameter("Pname"));
                    dba.updateLinkObj_Out(Integer.parseInt(request.getParameter("Linkid")), Integer.parseInt(request.getParameter("Obj")), Integer.parseInt(request.getParameter("Out")));
                    System.out.println(Integer.parseInt(request.getParameter("Linkid"))+"           "+Integer.parseInt(request.getParameter("ObjLinkValue").substring(0, request.getParameter("ObjLinkValue").indexOf(':')))+"                     "+Integer.parseInt(request.getParameter("OutLinkValue").substring(0, request.getParameter("OutLinkValue").indexOf(':')))+"          Update   AdObj Servlet");
                    Auditor.add((String)request.getSession().getAttribute("username"),"Updated Outcome/Objective Link (Program ID: "+request.getParameter("id")+")");

                }else {

                    sendErrMsg("Link is exist",request.getParameter("name"),request.getParameter("id"),request,response);
                }
                 } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            //out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");

            //System.out.println(data.get(0)+"                vdgfsg            "+data.get(1));
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            //response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
            response.setHeader("Location", "/program/index.jsp?page=LinkOutObj&name=" + request.getParameter("name") + "&id=" + request.getParameter("id"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void sendErrMsg(String msg,String pname,String pid,HttpServletRequest request, HttpServletResponse response){


        System.out.println("ErrMsg : "+msg);

        System.out.println("session is : "+request.getSession().getId());
        request.getSession().setAttribute("errMsg",msg);
        //request.getSession().setAttribute("TermVal",TermVal);


        try {
            if(request.getParameter("ObjLinkValue").equals("null")) {

                response.sendRedirect("/program/index.jsp?name="+pname+"&id="+pid+"&page=LinkOutObj&status=err");
            }else {

                String Linkid = request.getParameter("Linkid");
                String ObjLinkValue = request.getParameter("ObjLinkValue");
                String OutLinkValue = request.getParameter("OutLinkValue");
                //request.getRequestDispatcher("/program/index.jsp?page=update").forward(request, response);
                response.sendRedirect("/program/index.jsp?page=updateLink&name="+pname+"&id="+pid+"&Linkid="+ Linkid+"&ObjLinkValue="+ObjLinkValue+"&OutLinkValue="+OutLinkValue);
            }
        } /*catch (ServletException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }
}
