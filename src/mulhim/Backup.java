package mulhim;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mohammed on 2/1/2016.
 * http://www.coderanch.com/t/480353/JDBC/databases/MySql-DB-backup-java
 *
 */
@WebServlet(name = "backup",
        urlPatterns = {"/Backup"})
public class Backup extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //backupDB();
        restoreDB();
    }

    String dbName = "abetasdb";
    String dbUser = "root";
    String dbPass = "abetas";


    public void backupDB() {
        String executeCmd = "";

        executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump.exe -u " + dbUser + " -p" + dbPass + " " + dbName + " -r backup_test2.sql";

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

    public void restoreDB() {

String path="E:\\L 8\\Project Implementation\\apache-tomee-1.7.3-plus\\apache-tomee-plus-1.7.3\\bin\\backup.sql";
            String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql.exe -u + dbUser + \" -p\" + dbPass + \" \" + dbName + <" + path;

            System.out.println(executeCmd);

            Process runtimeProcess= null;

            try {
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);

                int processComplete = runtimeProcess.waitFor();

                System.out.println(processComplete);

                if (processComplete == 0) {
                    System.out.println("Restored the Backup !");
                } else {
                    System.out.println("Couldn't Restore the backup !");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
