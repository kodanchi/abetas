package mulhim;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Mohammed on 2/6/2016.
 */
@WebServlet(name = "restoreDB",
        urlPatterns = {"/RestoreDB"})
public class RestoreDB extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        System.out.println(request.getParameter("restoreAction"));
        String restoreBackup=request.getParameter("restoreAction");
        if (restoreBackup!=null)
        restoreDB(restoreBackup,request,response);
        else{
            out.print("Wrong restore files");

        }
        response.sendRedirect("backup.jsp");
    }
    String dbName = "abetasdb";
    String dbUser = "root";
    String dbPass = "abetas";
    public void restoreDB(String backupFile,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        backupFile = request.getParameter("restoreAction");
        String[] executeCmd = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql.exe", dbName, "--user=" + dbUser, "--password=" + dbPass, "-e", " source E:\\Backup\\"+backupFile };
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
}

