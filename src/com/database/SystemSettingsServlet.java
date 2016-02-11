package com.database;

import ASDB.AS_Update;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mojahed on 2/11/2016.
 */
@WebServlet(name = "SystemSettingsServlet", urlPatterns = {"/sysSettingsUpdate"})
public class SystemSettingsServlet extends HttpServlet {
    AS_Update db = new AS_Update();
    private String uname,cname,ulogo,oldUname = null;
    private final String UPLOAD_DIRECTORY = "D:/uploads";
    private final String SERVER_DIRECTORY = System.getProperty("ABETAS_HOME");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        try {
            //process only if its multipart content
            if(ServletFileUpload.isMultipartContent(request)){
                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    for(FileItem item : items){
                        /*System.out.println(item.getString()+" ----item");
                        System.out.println(item.getFieldName()+" --++--item");*/

                        String name = item.getFieldName();
                        switch (name) {
                            case "uname":
                                uname = item.getString();
                                break;
                            case "cname":
                                cname = item.getString();
                                break;
                            case "oldUname":
                                oldUname = item.getString();
                                break;
                            default:
                                System.out.println("default fired!");
                        }

                        if(!item.isFormField()){

                            System.out.println("else fired!"+ item.getSize());
                            if(item.getSize() != 0) {
                                name = new File(item.getName()).getName();
                                if (item.getSize() < 2000000) {

                                    String extension = "";

                                    int i = item.getName().lastIndexOf('.');
                                    if (i > 0) {
                                        extension = item.getName().substring(i + 1);
                                        System.out.println("file ext !"+ extension);
                                    }
                                    if (extension.equals("png")) {
                                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                                        ulogo = UPLOAD_DIRECTORY + "/" + name;
                                        //File uploaded successfully
                                        System.out.println("File Uploaded Successfully!");

                                        db.updateSystemSettings(uname,cname,ulogo,oldUname);
                                    } else {
                                        sendErrMsg("university logo must be type of PNG",request,response);
                                    }
                                } else {
                                    sendErrMsg("Logo image's size exceeds 2 mb",request,response);
                                }
                            }else{
                                ulogo = null;
                            }
                        }
                    }


                } catch (Exception ex) {
                    System.out.println("File Upload Failed due to " + ex);
                }

                response.sendRedirect("/settings/index.jsp?status=SystemUpdated");
            }else{
                System.out.println("not multipart!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void sendErrMsg(String msg,HttpServletRequest request, HttpServletResponse response){


        System.out.println("ErrMsg : "+msg);

        System.out.println("session is : "+request.getSession().getId());
        request.getSession().setAttribute("errMsg",msg);
        //request.getSession().setAttribute("userValue",userVal);


        try {
            response.sendRedirect("/settings/index.jsp?page=update");
            //request.getRequestDispatcher("/settings/index.jsp?page=update").forward(request,response);
        } /*catch (ServletException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        }
        /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        try {
            response.setHeader("Location","/users/index.jsp?page=add&status="+ URLEncoder.encode(msg, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        return;
    }

}
