package ASDB;

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


@WebServlet(name = "SystemSettingsServlet", urlPatterns = {"/sysSettingsUpdate"})
public class SystemSettingsServlet extends HttpServlet {

    private String uname,cname,ulogo,color,removeLogo;
    private final String UPLOAD_DIRECTORY = "uploads";
    private String SERVER_DIRECTORY ;
    private boolean isValid;

    /**
     * update the system information (university name and logo, college name and color of the header).
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        uname = cname = ulogo = color = removeLogo = null;
        isValid = true;

        SERVER_DIRECTORY = getServletContext().getRealPath("/");

        try {
            if(ServletFileUpload.isMultipartContent(request)){

                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    for(FileItem item : items){


                        String name = item.getFieldName();
                        switch (name) {
                            case "uname":
                                uname = item.getString();
                                if(uname.equals("")){
                                    sendMsg("University name must be entered",request,response);
                                    isValid = false;
                                }
                                break;
                            case "cname":
                                cname = item.getString();
                                if(cname.equals("")){
                                    sendMsg("College name must be entered",request,response);
                                    isValid = false;
                                }
                                break;
                            case "color":
                                color = item.getString();
                                if(color.equals("")){
                                    color = "#043366";
                                }
                                break;
                            case "removeLogo":
                                removeLogo = item.getString();
                                break;
                            default:
                        }

                        if(!item.isFormField()){

                            if(item.getSize() != 0) {
                                name = new File(item.getName()).getName();
                                if (item.getSize() < 2000000) {

                                    String extension = "";

                                    int i = item.getName().lastIndexOf('.');
                                    if (i > 0) {
                                        extension = item.getName().substring(i + 1);
                                    }
                                    if (extension.equals("png")) {
                                        item.write(new File(SERVER_DIRECTORY + File.separator + UPLOAD_DIRECTORY +
                                                File.separator + name));
                                        ulogo = "/" + UPLOAD_DIRECTORY + "/" + name;

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

                        ServletContext context = request.getServletContext();
                        Settings_Update adb = new Settings_Update();

                        if(removeLogo != null && removeLogo.equals("remove")){
                            adb.updateSystemSettings(uname,cname,null,color);
                            context.setAttribute("ulogo",null);
                        }else if (ulogo.equals("same")){
                            adb.updateSystemSettings(uname,cname,context.getAttribute("ulogo") != null ? (String)  context.getAttribute("ulogo"):null,color);

                        }else {
                            adb.updateSystemSettings(uname,cname,ulogo,color);
                            context.setAttribute("ulogo",ulogo);
                        }

                        context.setAttribute("uname",uname);
                        context.setAttribute("cname",cname);
                        context.setAttribute("color",color);

                        Auditor.add((String)request.getSession().getAttribute("username"),"Updated the system settings");
                    }


                } catch (Exception ex) {
                }

                sendMsg("System settings has been updated",request,response);
                response.sendRedirect("/settings/index.jsp");
            }else{
                response.sendRedirect("/settings/index.jsp");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * send message
     * @param msg String
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     */
    protected void sendMsg(String msg, HttpServletRequest request, HttpServletResponse response){


        if(request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg",msg);

    }

}
