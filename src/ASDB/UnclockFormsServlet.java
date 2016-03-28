package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Mojahed on 2/26/2016.
 */
@WebServlet(name = "UnclockFormsServlet" , urlPatterns = {"/unlockForm"})
public class UnclockFormsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("////////////////POST/ UnclockFormsServlet");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.print("////////////////GET/ UnclockFormsServlet");
        AS_Update dbu = new AS_Update();
        String redirectURL= "/form/index.jsp?page=unlockForm";
        try {

            if (request.getParameter("fid") != null && request.getParameter("ftype") != null) {
                if (request.getParameter("ftype").equals("summative")) {
                    dbu.updateUnlockFormS(Integer.parseInt(request.getParameter("fid")));
                } else if(request.getParameter("ftype").equals("formative")) {
                    dbu.updateUnlockFormF(Integer.parseInt(request.getParameter("fid")));
                }
                Auditor.add((String)request.getSession().getAttribute("username"),"Unlocked form (Type: "+request.getParameter("ftype")+
                        ", Form ID: "+request.getParameter("fid")+")");

                sendMsg("Form has been unlocked",request,response);
            } else {
                sendMsg("unvalid form ",request,response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(redirectURL);
    }
    protected void sendMsg(String msg, HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg", msg);

    }
}
