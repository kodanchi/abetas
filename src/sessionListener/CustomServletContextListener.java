package sessionListener;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        // if the directory does not exist, create it
        String backupfolder =  context.getRealPath("/")+ "backup";
        File theDir = new File(backupfolder);

        if (!theDir.exists()) {
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
        }

    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed here");
        // To overcome the problem with losing the session references
        // during server restarts, put code here to serialize the
        // activeUsers HashMap.  Then put code in the contextInitialized
        // method that reads and reloads it if it exists...


        /*try
        {
            ServletContext context = servletContextEvent.getServletContext();
            HashMap<String, HttpSession> activeUsers = (HashMap<String, HttpSession>) context.getAttribute("activeUsers");

            FileOutputStream fos =
                    new FileOutputStream("d:/hashmap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(activeUsers);
            oos.close();
            fos.close();
            System.out.println("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }*/
    }
}
