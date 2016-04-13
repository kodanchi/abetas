package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * UnlockFormServlet is used to unlock selected form by superuser from unlockForm page which updating form status in the
 * database to be 0 and redirect to the same page.
 */
@WebServlet(name = "UnlockFormServlet" , urlPatterns = {"/unlockForm"})
public class UnlockFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        AS_Update dbu = new AS_Update();
        String redirectURL= "/form/index.jsp?page=unlockForm";
        try {

            if (request.getParameter("fid") != null && request.getParameter("ftype") != null) {
                if (request.getParameter("ftype").equals("summative")) {
                    dbu.updateUnlockFormS(Integer.parseInt(request.getParameter("fid")));
                } else if(request.getParameter("ftype").equals("formative")) {
                    dbu.updateUnlockFormF(Integer.parseInt(request.getParameter("fid")));
                }
                Auditor.add((String)request.getSession().getAttribute("username"),"Unlocked form (Type: "+
                        request.getParameter("ftype")+", Form ID: "+request.getParameter("fid")+")");

                sendMsg("Form has been unlocked",request);
            } else {
                sendMsg("Error! un-valid form, Contact support! ",request);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(redirectURL);
    }
    /**
     * send success message through session attribute and redirect the user to the same page.
     * @param msg message which will be sent to the user
     * @param request HttpServletRequest
     */
    protected void sendMsg(String msg, HttpServletRequest request) {

        if (request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg", msg);

    }
}
