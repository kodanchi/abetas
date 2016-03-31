package sessionListener;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Mojahed on 2/9/2016.
 */
public class CustomHttpSessionListener implements HttpSessionListener {
    public void init(ServletConfig config){
    }
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        /*HttpSession    session = httpSessionEvent.getSession();
        ServletContext context = session.getServletContext();
        HashMap<String, HttpSession> activeUsers =  (HashMap<String, HttpSession>) context.getAttribute("activeUsers");

        activeUsers.put(session.getId(), session);
        context.setAttribute("activeUsers", activeUsers);
        System.out.println("userSession add to activeUsers");*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        /*HttpSession    session = httpSessionEvent.getSession();
        ServletContext context = session.getServletContext();
        HashMap<String, HttpSession> activeUsers = (HashMap<String, HttpSession>)context.getAttribute("activeUsers");
        activeUsers.remove(session.getId());
        System.out.println("userSession removed from activeUsers");*/
System.out.println("kjcdnskcms,dcdscdscdskckjdschdjkhckhvhsfvsh dhdhbghgdhghg");
    }
}
