package Backup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * deleteBackupDB is used to delete the backup file from backup folder.
 */
@WebServlet(name = "deleteBackupDB",
        urlPatterns = {"/BackupDel"})
public class deleteBackupDB extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "backup";
    private String SERVER_DIRECTORY = null;

    /**
     *
     * @param request used to get the servler request from the uri which has the user data
     * @param response used to respone to the servlet site either by redirect the site to another url or show some output to the user
     * @throws ServletException used to handle if the error occurs from uri (web server)
     * @throws IOException used to handle any inpout/output operation in dealing with windows operations
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SERVER_DIRECTORY = getServletContext().getRealPath("/");

        PrintWriter out = response.getWriter();
        /**
         * get "deleteaction" values from the servlet, since its button once the user click on it deleteBackup function will be called and passing the servlet request and response with it.
         * then it will be redirected to the url "backup.jsp"
         * if the backup file not exist the user will get message till him that deleted files operation not successfully done
         */

        if(request.getParameter("deleteAction") !=null) {
            if(deleteBackup(request)) {
                response.sendRedirect("backup.jsp");
            }else {
                out.print("Wrong restore files");
            }
        }
        else{
            out.print("Wrong restore files");

        }
    }

    /**
     * The location of the backup files that need to be deleted
     * then check if the file exist then delete otherwise tell the user that the file is not exist
     *
     * @param request HttpServletRequest
     * @throws ServletException
     * @throws IOException
     */
    public boolean deleteBackup(HttpServletRequest request) throws ServletException, IOException{

        boolean isDeleted = false;
        String deleteDB = request.getParameter("deleteAction");
    try{

        File file = new File(SERVER_DIRECTORY + "/" + UPLOAD_DIRECTORY + "/" +deleteDB);

        if(file.delete()){
            isDeleted = true;
        }

    }catch(Exception e){

        e.printStackTrace();

    }
        return isDeleted;
    }
}
