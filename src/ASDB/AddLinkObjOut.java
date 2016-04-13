package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddLinkObjOut",
        urlPatterns = {"/Add Link Outcome and Objective"})
public class AddLinkObjOut extends HttpServlet {
    /**
     * insert or update link objective with student outcome, then redirect to the link objective with student outcome page.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("ObjLinkValue").equals("null")) {
        P_AS_Insert dba=new P_AS_Insert();
        P_AS_Select dbaS=new P_AS_Select();
        try {
            if(!dbaS.isExistLinkObj_Out(Integer.parseInt(request.getParameter("Obj")),Integer.parseInt(request.getParameter("Out")),
                    Integer.parseInt(request.getParameter("id")))){
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

        }
        else {
            P_AS_Update dba = new P_AS_Update();
            P_AS_Select dbaS=new P_AS_Select();
            try {
                if(!dbaS.isExistLinkObj_OutExcept(Integer.parseInt(request.getParameter("Obj")),Integer.parseInt(request.getParameter("Out")),
                        Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("Out")))){

                    dba.updateLinkObj_Out(Integer.parseInt(request.getParameter("Linkid")), Integer.parseInt(request.getParameter("Obj")), Integer.parseInt(request.getParameter("Out")));
                    Auditor.add((String)request.getSession().getAttribute("username"),"Updated Outcome/Objective Link (Program ID: "+request.getParameter("id")+")");

                }else {

                    sendErrMsg("Link is exist",request.getParameter("name"),request.getParameter("id"),request,response);
                }
                 } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/program/index.jsp?page=LinkOutObj&name=" + request.getParameter("name") + "&id=" + request.getParameter("id"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void sendErrMsg(String msg,String pname,String pid,HttpServletRequest request, HttpServletResponse response){


        request.getSession().setAttribute("errMsg",msg);


        try {
            if(request.getParameter("ObjLinkValue").equals("null")) {

                response.sendRedirect("/program/index.jsp?name="+pname+"&id="+pid+"&page=LinkOutObj&status=err");
            }else {

                String Linkid = request.getParameter("Linkid");
                String ObjLinkValue = request.getParameter("ObjLinkValue");
                String OutLinkValue = request.getParameter("OutLinkValue");
                response.sendRedirect("/program/index.jsp?page=updateLink&name="+pname+"&id="+pid+"&Linkid="+ Linkid+"&ObjLinkValue="+ObjLinkValue+"&OutLinkValue="+OutLinkValue);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }
}
