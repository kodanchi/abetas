package Backup;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Backup is used to set a database backup t
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
    private String SERVER_DIRECTORY = null;

    @Override
    public void init(ServletConfig config)
            throws ServletException{
        SERVER_DIRECTORY = config.getServletContext().getRealPath("/");
    }

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
            new BackupTool(SERVER_DIRECTORY+UPLOAD_DIRECTORY);

        }
        /**
         * after calling the function return the user to the same page using sendRedirect
         */
        response.sendRedirect("backup.jsp");

    }

}
