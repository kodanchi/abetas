package FDB;

import passReset.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EmailReminderServlet is used to send emails to all the faculty members who have one or more of un-submitted forms.
 */

@WebServlet(name = "EmailReminderServlet", urlPatterns = {"/Emailrem"})
public class EmailReminderServlet extends HttpServlet {
    /**
     * Send emails to all the faculty members who have one or more of un-submitted forms.
     * This will go through all the emails and add each one after another with , after each email.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] e = request.getParameterValues("emailList");

        SendEmail sendemail = new SendEmail();

        for (int i = 0; i < e.length; i++) {
            if (i == 0) {
                e[i].replace('[', ' ');
                e[i].replace(']', ' ');
                String a = e[0].replace('[', ' ');
                a = a.replace(']', ' ');
                sendemail.sendMsg("Hello, this is reminder that you have one or more of un-submitted forms", "Reminder", a);
                sendMsg("Email was sent to all the Faculty Members in the table",request);
                response.sendRedirect("/form/index.jsp?page=unsubmitted");
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * send success message through session attribute and redirect the user to the same page (un-submitted forms page)
     * @param msg message which will be sent to the user
     * @param request HttpServletRequest
     */
    protected void sendMsg(String msg, HttpServletRequest request) {

        if (request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg", msg);

    }
}