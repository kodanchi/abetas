package FDB;

import passReset.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EmailRequestUnlook is used to send emails to the ABETAS email asking to un-lock a specific form.
 */
@WebServlet(name = "EmailRequestUnlook", urlPatterns = {"/Emailreq"})
public class EmailRequestUnlook extends HttpServlet {
    /**
     * Send emails to the ABETAS email asking to un-lock a specific form.
     * This will indicate the faculty member name, type, course and performance indicator.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Fn = request.getParameter("Fullname");
        String c = request.getParameter("course");
        String pi = request.getParameter("pi");
        String type = request.getParameter("type");

        SendEmail sendemail = new SendEmail();
        sendemail.sendMsg("Faculty Member: "+Fn+" request to un-look a "+type+" form in "+c+" course for "+pi+" performance indicator", "Request Un-Look Form","mulhimmm@gmail.com");
        sendMsg("Email was sent requesting to un-look the form",request);
        response.sendRedirect("/form/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * send success message through session attribute and redirect the user to the same page (forms page)
     * @param msg message which will be sent to the user
     * @param request HttpServletRequest
     */
    protected void sendMsg(String msg, HttpServletRequest request) {

        if (request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg", msg);

    }
}
