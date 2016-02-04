package mulhim;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // backupDB();
       // restoreDB();
        if(request.getParameter("backupCreate")!=null){
            backupDB();

        }

        response.sendRedirect("backup.jsp");
    }

    String dbName = "abetasdb";
    String dbUser = "root";
    String dbPass = "abetas";


    public void backupDB() {
        String executeCmd = "";
loginDB d= new loginDB();
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy-HH-mm-S");
        String date = DATE_FORMAT.format(today);
        System.out.println("Today in dd-MM-yy:HH:mm:SS : " + date);

        executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump.exe -u " + dbUser + " -p" + dbPass + " " + dbName + " -r E:\\Backup\\Backup_"+date.toString()+".sql";

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
    public void listBackupFile(HttpServletResponse r) throws ClassNotFoundException, SQLException, IOException{
        PrintWriter out =r.getWriter();

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
                    Path p = Paths.get(files);
                    BasicFileAttributes view
                            = Files.getFileAttributeView(p, BasicFileAttributeView.class)
                            .readAttributes();

                    //System.out.println(view.creationTime()+" is the same as "+view.lastModifiedTime());
                    System.out.println(p+" "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                            .format(view.creationTime().toMillis()));
//                    out.println("<td>1</td>\n" +
//                            "                                <td>A</td>\n" +
//                            "                                <td><a class=\"btn btn-warning btn-simple\" data-toggle=\"modal\" data-target=\"#restoreModal\"><i class=\"fa fa-undo fa-2x\"></i></a></td>\n" +
//                            "                                <td ><a class=\"btn btn-danger btn-simple\" data-toggle=\"modal\" data-target=\"#deleteModal\"><i class=\"fa fa-trash-o fa-2x\"></i></a></td>");
//                    out.println();


                }
            }
        }
    }
}
