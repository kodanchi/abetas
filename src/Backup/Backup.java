package Backup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by Mohammed on 2/1/2016.
 * http://www.coderanch.com/t/480353/JDBC/databases/MySql-DB-backup-java
 * http://ubuntuforums.org/showthread.php?t=1184395
 * http://stackoverflow.com/questions/2723838/determine-file-creation-date-in-java
 *
 *
 */


@WebServlet(name = "backup",
        urlPatterns = {"/Backup"})
public class Backup extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "backup";
    private final String SERVER_DIRECTORY = getServletContext().getRealPath("/");

    /**
     *
     * @param request used to get the servler request from the uri which has the user data
     * @param response used to respone to the servlet site either by redirect the site to another url or show some output to the user
     * @throws ServletException used to handle if the error occurs from uri (web server)
     * @throws IOException used to handle any inpout/output operation in dealing with windows operations
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * get "BackupCreate" values from the servlet, since its button once the user click on it backupDB function will be called
         */
        if(request.getParameter("backupCreate")!=null){
            backupDB();

        }
/**
 * after calling the function return the user to the same page using sendRedirect
 */
        response.sendRedirect("backup.jsp");

    }

    /**
     * Since the superuser only the person who will do the backup , the database configuration here done manually
     */
    String dbName = "abetasdb";
    String dbUser = "root";
    String dbPass = "abetas";

    /**
     * Backup function will create the database backup
     */
    public void backupDB() {
        String executeCmd = "";

        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy-HH-mm-S");
        String date = DATE_FORMAT.format(today);
        System.out.println("Today in dd-MM-yy:HH:mm:SS : " + date);
/**
 * the next string has the command prompt in windows that will do the backup using mysqldump program, by knowing the username, password and the database name
 * it is important to mention the directory that you want to store the backup in it after providing the database information
 */
        executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump.exe -u " + dbUser + " -p" + dbPass + " " + dbName + " -r "+SERVER_DIRECTORY + "/" + UPLOAD_DIRECTORY +"/Backup_"+date.toString()+".sql";
/**
 * doing the command in windows using Process class and handle the I/O operation by catch
 */
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
        /**
         * if the process done correctly the result of complete should be zero , otherwise it is not
         */
        if (processComplete == 0) {
            System.out.println("Backup taken successfully");
        } else {
            System.out.println("Could not take mysql backup");
        }
    }


}
