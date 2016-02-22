package mulhim;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        System.out.println(request.getParameter("deleteAction"));
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

        File file = new File("E:\\Backup\\"+deleteDB);

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
