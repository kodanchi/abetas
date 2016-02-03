package mulhim;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;

/**
 * Created by Mohammed on 2/1/2016.
 * http://www.coderanch.com/t/480353/JDBC/databases/MySql-DB-backup-java
 * http://ubuntuforums.org/showthread.php?t=1184395
 *
 */
@WebServlet(name = "backup",
        urlPatterns = {"/Backup"})
public class Backup extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // backupDB();
       // restoreDB();
        listBackupFile();
    }

    String dbName = "abetasdb";
    String dbUser = "root";
    String dbPass = "abetas";


    public void backupDB() {
        String executeCmd = "";

        executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump.exe -u " + dbUser + " -p" + dbPass + " " + dbName + " -r test_backup.sql";

        Process runtimeProcess = null;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int processComplete = 0;
        try {
            processComplete = runtimeProcess.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (processComplete == 0) {
            System.out.println("Backup taken successfully");
        } else {
            System.out.println("Could not take mysql backup");
        }
    }

    public void restoreDB(String backupFile) {
        String[] executeCmd = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql.exe", dbName, "--user=" + dbUser, "--password=" + dbPass, "-e", " source C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\"+backupFile };
            //System.out.println(executeCmd);

            Process runtimeProcess= null;

        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int processComplete = 0;
        try {
            processComplete = runtimeProcess.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (processComplete == 0) {
            System.out.println("Restore taken successfully");
        } else {
            System.out.println("restore failure");
        }
        }
    public void listBackupFile(){

// Directory path here
        String path = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin";

        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++)
        {

            if (listOfFiles[i].isFile())
            {
                files = listOfFiles[i].getName();
                if (files.endsWith(".sql") || files.endsWith(".SQL"))
                {

                    System.out.println(files);


                }
            }
        }
    }
}
