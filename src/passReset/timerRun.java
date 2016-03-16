package passReset;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
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
        ServletContext context = servletContextEvent.getServletContext();
        String backupfolder =  context.getRealPath("/")+ "backup";
        File theDir = new File(backupfolder);

        /*if (!theDir.exists()) {
            System.out.println("creating directory: " + backupfolder);
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
                System.out.println("Backup DIR created");
            }
        }else {System.out.println("Backup already existed");}
*/
        TimeClass t = new TimeClass(backupfolder);
        t.timeTest();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
