
package com.database;

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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Ibrahim Abuaqel on 1/19/2016.
 */

@WebServlet(name = "InstallServlet",
        urlPatterns = {"/install"})
public class InstallServlet extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "/uploads";
    private String uname,cname,afname,amname,alname,ausername,apassword,arepassword,aemail,ulogo = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>installing..</title>\n" +
                "    <!-- Bootstrap -->\n" +
                "    <link href=\"/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"/css/setup.css\" rel=\"stylesheet\">\n" +
                "</head>\n" +
                "<body><div class=\"container text-center\">\n" +
                "     <h3>Installing..</h3>\n" +
                "\t<button class=\"btn btn-lg btn-warning\"><span class=\"glyphicon glyphicon-refresh glyphicon-refresh-animate\"></span> Loading...</button>\n" +
                "</div></body>\n" +
                "</html>");
        InstallDB db = new InstallDB();

        try {
            db.installdb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            //process only if its multipart content
            if(ServletFileUpload.isMultipartContent(request)){
                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    for(FileItem item : items){
                        System.out.println(item.getString()+" ----item");
                        System.out.println(item.getFieldName()+" --++--item");

                        String name = item.getFieldName();
                        switch (name) {
                            case "uname":
                                uname = item.getString();
                                break;
                            case "cname":
                                cname = item.getString();
                                break;
                            case "adminUsername":
                                ausername = item.getString();
                                break;
                            case "adminpassword":
                                apassword = item.getString();
                                break;
                            case "rePassword":
                                arepassword = item.getString();
                                break;
                            case "adminemail":
                                aemail = item.getString();
                                break;
                            case "adminFirstName":
                                afname = item.getString();
                                break;
                            case "adminMiddleName":
                                amname = item.getString();
                                break;
                            case "adminLastName":
                                alname = item.getString();
                                break;
                            default:
                                System.out.println("default fired!");
                        }

                        if(!item.isFormField()){
                            System.out.println("else fired!");
                            name = new File(item.getName()).getName();
                            item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                            ulogo = UPLOAD_DIRECTORY + "/" + name;
                        }
                    }

                    //File uploaded successfully
                    System.out.println("File Uploaded Successfully!");
                } catch (Exception ex) {
                    System.out.println("File Upload Failed due to " + ex);
                }

                db.init(uname, cname, ulogo, afname, amname, alname, ausername, apassword, aemail);
            }else{
                System.out.println("not multipart!");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        //out.println("Insert");


        //response.sendRedirect("http://localhost:8080/");
        // New location to be redirected
        // String site = new String("http://localhost:8081/");
        // response.setHeader("Location", site);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

