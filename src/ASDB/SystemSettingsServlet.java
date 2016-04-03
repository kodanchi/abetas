package ASDB;

import ASDB.Settings_Update;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mojahed on 2/11/2016.
 */
@WebServlet(name = "SystemSettingsServlet", urlPatterns = {"/sysSettingsUpdate"})
public class SystemSettingsServlet extends HttpServlet {

    private String uname,cname,ulogo,color,removeLogo;
    private final String UPLOAD_DIRECTORY = "uploads";
    private String SERVER_DIRECTORY ;
    private boolean isValid;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        uname = cname = ulogo = color = removeLogo = null;
        isValid = true;

        SERVER_DIRECTORY = getServletContext().getRealPath("/");

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
                                if(uname.equals("")){
                                    sendMsg("University name must be entered",request,response);
                                    isValid = false;
                                } /*else if (!uname.matches("/^[a-zA-Z]*$/g")) {
                                    sendMsg("Name must have only alphabetic letters", request, response);
                                    isValid = false;
                                }*/
                                break;
                            case "cname":
                                cname = item.getString();
                                if(cname.equals("")){
                                    sendMsg("College name must be entered",request,response);
                                    isValid = false;
                                } /*else if (!cname.matches("/^[a-zA-Z]*$/g")) {
                                    sendMsg("Name must have only alphabetic letters",request,response);
                                    isValid = false;
                                }*/
                                break;
                            case "color":
                                color = item.getString();
                                if(color.equals("")){
                                    /*sendMsg("College name must be entered",request,response);
                                    isValid = false;*/
                                    color = "#043366";
                                }
                                break;
                            case "removeLogo":
                                removeLogo = item.getString();
                                System.out.println("removeLogo : "+removeLogo);
                                /*if(color.equals("")){
                                    *//*sendMsg("College name must be entered",request,response);
                                    isValid = false;*//*
                                    color = "#043366";
                                }*/
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
                                        item.write(new File(SERVER_DIRECTORY + File.separator + UPLOAD_DIRECTORY +
                                                File.separator + name));
                                        ulogo = "/" + UPLOAD_DIRECTORY + "/" + name;
                                        //File uploaded successfully
                                        System.out.println("File Uploaded Successfully!"+ulogo);

                                    } else {
                                        sendMsg("university logo must be type of PNG",request,response);
                                        isValid = false;
                                    }


                                } else {
                                    sendMsg("Logo image's size exceeds 2 mb",request,response);
                                    isValid = false;
                                }
                            }else{
                                ulogo = "same";
                            }
                        }
                    }

                    if(isValid){

                        System.out.println("Ulogo : "+ ulogo);
                        ServletContext context = request.getServletContext();
                        Settings_Update adb = new Settings_Update();

                        if(removeLogo != null && removeLogo.equals("remove")){
                            System.out.println("inside if ----");
                            adb.updateSystemSettings(uname,cname,null,color);
                            //context.removeAttribute("ulogo");
                            context.setAttribute("ulogo",null);
                        }else if (ulogo.equals("same")){
                            System.out.println("inside if else ----");
                            adb.updateSystemSettings(uname,cname,context.getAttribute("ulogo") != null ? (String)  context.getAttribute("ulogo"):null,color);

                        }else {
                            System.out.println("inside else ----");
                            adb.updateSystemSettings(uname,cname,ulogo,color);
                            //context.removeAttribute("ulogo");
                            context.setAttribute("ulogo",ulogo);
                        }


                        //context.removeAttribute("uname");
                        //context.removeAttribute("cname");
                        //context.removeAttribute("color");
                        context.setAttribute("uname",uname);
                        context.setAttribute("cname",cname);
                        context.setAttribute("color",color);


                        Auditor.add((String)request.getSession().getAttribute("username"),"Updated the system settings");
                    }


                } catch (Exception ex) {
                    System.out.println("File Upload Failed due to " + ex);
                }

                //response.sendRedirect("/settings/index.jsp?status=SystemUpdated");
                sendMsg("System settings has been updated",request,response);
                response.sendRedirect("/settings/index.jsp");
            }else{
                System.out.println("not multipart!");
                response.sendRedirect("/settings/index.jsp");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void sendMsg(String msg, HttpServletRequest request, HttpServletResponse response){


        if(request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg",msg);


    }

}
