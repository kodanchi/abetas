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
 * Created by Mojahed on 3/21/2016.
 */
@WebServlet(name = "backupReschduleServlet", urlPatterns = {"/setBackupTime"})
public class backupReschduleServlet extends HttpServlet {
    final int SECOND = 1000;
    final int MINUTE = 60 * SECOND;
    final int HOUR = 60 * MINUTE;
    final int DAY = 24 * HOUR;
    final int WEEK = 7 * DAY;
    final int MONTH = 4 * WEEK;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String selctedTime = request.getParameter("timeList");

        ServletContext context = getServletContext();

        TimeClass t = (TimeClass) context.getAttribute("TimeClass");
        if(selctedTime.equals("daily")){
            context.setAttribute("backupTime","daily");
            //t.cancel();
            t.Reschedule(DAY);
        }else if(selctedTime.equals("weekly")){
            context.setAttribute("backupTime","weekly");
            t.Reschedule(WEEK);
        }else if(selctedTime.equals("biweekly")){
            context.setAttribute("backupTime","biweekly");
            t.Reschedule(WEEK*2);
        }else if (selctedTime.equals("monthly")){
            context.setAttribute("backupTime","monthly");
            t.Reschedule(MONTH);
        }


        response.sendRedirect("/backup.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
