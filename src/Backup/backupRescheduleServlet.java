package Backup;

import passReset.TimeClass;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * backupRescheduleServlet is used to set the backup interval as user requested (daily, weekly,biweekly, monthly).
 */
@WebServlet(name = "backupRescheduleServlet", urlPatterns = {"/setBackupTime"})
public class backupRescheduleServlet extends HttpServlet {
    final int SECOND = 1000;
    final int MINUTE = 60 * SECOND;
    final int HOUR = 60 * MINUTE;
    final int DAY = 24 * HOUR;
    final long WEEK = 7 * DAY;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String selctedTime = request.getParameter("timeList");

        ServletContext context = getServletContext();

        TimeClass t = (TimeClass) context.getAttribute("TimeClass");
        TimeClass newtimer = new TimeClass(t.getBACKUP_DIRECTORY());
        if(selctedTime.equals("daily")){
            context.setAttribute("backupTime","daily");
            newtimer.Reschedule(DAY);
            t.cancel();

        }else if(selctedTime.equals("weekly")){
            context.setAttribute("backupTime","weekly");
            newtimer.Reschedule(WEEK);
            t.cancel();
        }else if(selctedTime.equals("biweekly")){
            context.setAttribute("backupTime","biweekly");
            newtimer.Reschedule(WEEK*2);
            t.cancel();
        }else if (selctedTime.equals("monthly")){
            context.setAttribute("backupTime","monthly");
            newtimer.Reschedule(WEEK*4);
            t.cancel();
        }

        context.setAttribute("TimeClass",newtimer);

        sendMsg("Backup rescheduled to be "+selctedTime,request);

        response.sendRedirect("/backup.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * send success message through session attribute and redirect the user to the same page (backup page)
     * @param msg message which will be sent to the user
     * @param request HttpServletRequest
     */
    protected void sendMsg(String msg, HttpServletRequest request){
        request.getSession().setAttribute("Msg",msg);
    }

}
