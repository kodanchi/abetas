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
 * Created by Mohammed on 2/6/2016.
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
        String deleteAction=request.getParameter("deleteAction");
        if(deleteAction!=null) {
            deleteBackup(deleteAction, request, response);
            response.sendRedirect("backup.jsp");
        }
        else{
            out.print("Wrong restore files");

        }
    }
    public void deleteBackup(String deleteDB,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        deleteDB = request.getParameter("deleteAction");
    try{
/**
 * The location of the backup files that need to be deleted
 * then check if the file exist then delete otherwise tell the user that the file is not exist
 */
        File file = new File(SERVER_DIRECTORY + "/" + UPLOAD_DIRECTORY + "/" +deleteDB);

        if(file.delete()){
            System.out.println(file.getName() + " is deleted!");
        }else{
            System.out.println("Delete operation is failed.");
        }

    }catch(Exception e){

        e.printStackTrace();

    }

    }
}
