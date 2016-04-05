package passReset;

import ASDB.AS_Select;
import ASDB.InstallDB;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Created by Mohammed on 2/6/2016.
 */

/**
 * contextListener class used to initiate time class and TImeTest method when the server is start
 * that's done though  contextInitialized method
 */
public class contextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String backupfolder =  context.getRealPath("/")+ "backup";
        String uploadsfolder =  context.getRealPath("/")+ "uploads";

        createIfNotExisted(backupfolder);
        createIfNotExisted(uploadsfolder);

        try {
            InstallDB dbCon = new InstallDB(null);
            if(dbCon.setUpChk()){
                AS_Select dbs = new AS_Select();

                ArrayList<String> headerData = null;

                headerData = dbs.selectHeaderData();

                servletContextEvent.getServletContext().setAttribute("ulogo",headerData.get(2));
                servletContextEvent.getServletContext().setAttribute("uname",headerData.get(0));
                servletContextEvent.getServletContext().setAttribute("cname",headerData.get(1));
                servletContextEvent.getServletContext().setAttribute("color",headerData.get(3));

            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        servletContextEvent.getServletContext().setAttribute("backupTime","weekly");
        TimeClass t = new TimeClass(backupfolder);
        t.timeTest();
        servletContextEvent.getServletContext().setAttribute("TimeClass",t);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public void createIfNotExisted(String dir){

        File theDir = new File(dir);

        if (!theDir.exists()) {
            System.out.println("creating directory: " + dir);
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
                System.out.println("DIR created");
            }
        }else {System.out.println("DIR already existed");}

    }

}
