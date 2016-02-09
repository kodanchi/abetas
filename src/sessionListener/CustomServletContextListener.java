package sessionListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

/**
 * Created by Mojahed on 2/9/2016.
 */
public class CustomServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();

        //
        // instanciate a map to store references to all the active
        // sessions and bind it to context scope.
        //
        HashMap activeUsers = new HashMap();
        context.setAttribute("activeUsers", activeUsers);
        System.out.println("activeUsers is created");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed here");
        // To overcome the problem with losing the session references
        // during server restarts, put code here to serialize the
        // activeUsers HashMap.  Then put code in the contextInitialized
        // method that reads and reloads it if it exists...
    }
}
