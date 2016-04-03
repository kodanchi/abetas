package FDB;

import passReset.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ibrahim Abuaqel on 3/31/2016.
 */
@WebServlet(name = "EmailRequestUnlook", urlPatterns = {"/Emailreq"})
public class EmailRequestUnlook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Fn = request.getParameter("Fullname");
        String Fu = request.getParameter("Username");
        String c = request.getParameter("course");
        String pi = request.getParameter("pi");
        String type = request.getParameter("type");

        SendEmail sendemail = new SendEmail();

            //System.out.println(e[i]);
                //sendemail.sendMsg("Hello this is reminder", "Reminder", e[i].substring(1, ','));

                System.out.println("      gggggggggggggggggggggggggggggg      ");
                System.out.println(Fn);
                System.out.println(Fu);
                System.out.println(c);
                System.out.println(pi);
                System.out.println(type);
                System.out.println("Faculty Member: "+Fn+" request to un-look a "+type+" form in "+c+" course for "+pi+" performance indicator");

                sendemail.sendMsg("Faculty Member: "+Fn+" request to un-look a "+type+" form in "+c+" course for "+pi+" performance indicator", "Request Un-Look Form","mulhimmm@gmail.com");
                sendMsg("Email was sent requesting to un-look the form",request);
                response.sendRedirect("/form/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void sendMsg(String msg, HttpServletRequest request) {

        if (request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg", msg);

    }
}
