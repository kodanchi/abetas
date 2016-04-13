package Listeners;

import ASDB.AS_Select;
import Setup.InstallDB;
import passReset.TimeClass;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ContextListener class used when the server started/restarted to create folders for backup and uploads if not exist
 * and set several context attributes to hold data needed for the pages header (logo path, university name, college name,
 * header color). Furthermore ContextListener class used to initiate time class and TImeTest method that's done though
 * contextInitialized method.
 */
public class ContextListener implements ServletContextListener {

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

    /**
     * used to create new folder if not existed
     * @param dir real path of the directory to be checked
     */
    public void createIfNotExisted(String dir){

        File theDir = new File(dir);

        if (!theDir.exists()) {
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                se.printStackTrace();
            }
            if(result) {
                System.out.println("DIR created");
            }
        }

    }

}
