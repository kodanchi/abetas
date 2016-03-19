package Backup;

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
    private final String UPLOAD_DIRECTORY = "backup";
    private String SERVER_DIRECTORY = null;
    /**
     *
   *  @param request used to get the servler request from the uri which has the user data
      * @param response used to respone to the servlet site either by redirect the site to another url or show some output to the user
     * @throws ServletException used to handle if the error occurs from uri (web server)
     * @throws IOException used to handle any inpout/output operation in dealing with windows operations
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SERVER_DIRECTORY = getServletContext().getRealPath("/");

        PrintWriter out = response.getWriter();
        /**
         * get "restoreAction" values from the servlet, since its button once the user click on it restoreDB function will be called and passing the servlet request and response with it.
         * then it will be redirected to the url "backup.jsp"
         * if the backup file not exist the user will get message till him that  restore files operation not successfully done
         */
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
    public void restoreDB(String restoreBackup,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        /**
         * String restoreBackup will take the selected backup file that user selected via servlet
         * @param request used to get the servler request from the uri which has the user data
         * @param response used to respone to the servlet site either by redirect the site to another url or show some output to the user
         * @throws ServletException used to handle if the error occurs from uri (web server)
         * @throws IOException used to handle any inpout/output operation in dealing with windows operations
         */
        restoreBackup = request.getParameter("restoreAction");
        /**
         * String executeCmd has the command prompt in windows that will do the restore backup process using mysql.exe program, by knowing the username, password
         * ,the database name and the location of the file that need to be restored
         * after that doing the command in windows using Process class and handle the I/O operation by catch
         * lastly message will apear to the user either the operation successfully done or not
         */
        String[] executeCmd = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql.exe", dbName, "--user=" + dbUser, "--password=" + dbPass, "-e", "\" source " +SERVER_DIRECTORY +  UPLOAD_DIRECTORY +"\\"+restoreBackup+"\"" };
        for(int i= 0 ;i<executeCmd.length;i++)
        System.out.print(executeCmd[i]);

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

