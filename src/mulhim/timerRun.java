package mulhim;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletContextListener;
/**
 * Created by Mohammed on 2/6/2016.
 */

public class timerRun implements javax.servlet.ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TimeClass t = new TimeClass();
        t.timeTest();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
