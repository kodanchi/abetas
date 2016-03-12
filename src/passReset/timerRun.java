package passReset;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * Created by Mohammed on 2/6/2016.
 */

/**
 * timerRun class used to initiate time class and TImeTest method when the server is start
 * that's done though  contextInitialized method
 */
public class timerRun implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TimeClass t = new TimeClass();
        t.timeTest();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
