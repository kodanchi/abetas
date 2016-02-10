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
/*
        HashMap activeUsers = new HashMap();
        context.setAttribute("activeUsers", activeUsers);
        System.out.println("activeUsers is created");*/

        /*HashMap<String, HttpSession> map = deserialize();
        if (map == null){
            HashMap activeUsers = new HashMap();
            context.setAttribute("activeUsers", activeUsers);
            System.out.println("activeUsers is created");
        }else {
            // Display content using Iterator
            Set set = map.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
                System.out.print("key: " + mentry.getKey() + " & Value: ");
                System.out.println(mentry.getValue());
            }
        }*/

    }
    /*public HashMap<String, HttpSession> deserialize(){
        HashMap<String, HttpSession> map = null;
        try
        {
            FileInputStream fis = new FileInputStream("d:/hashmap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap<String, HttpSession>) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        System.out.println("Deserialized HashMap..");

        return map;
    }*/

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
