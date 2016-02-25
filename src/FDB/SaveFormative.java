package FDB;

import ASDB.*;
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
import java.util.List;

/**
 * Created by Ibrahim Abuaqel on 2/23/2016.
 */
@WebServlet(name = "SaveFormative",
        urlPatterns = {"/SaveFormative"})
public class SaveFormative extends HttpServlet {
    private String WrittenRubrics,Comments,Obstacles,Improvement,evidence,Formative_ID = null;
    private final String UPLOAD_DIRECTORY = "uploads";
    private String SERVER_DIRECTORY ;
    private boolean isValid = true;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(request.getParameter("Formative_ID")+"            SaveFormative ))))))))))))))))))))))))))))))))))))))))))))))))))))))");

        //ArrayList<String> data = new ArrayList<String>();
            System.out.println("#################################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");


        SERVER_DIRECTORY = getServletContext().getRealPath("/");
        String redirectURL = "/form/index.jsp";

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
                            case "Formative_ID":
                                Formative_ID = item.getString();
                                if (Formative_ID.equals("")) {
                                    //sendMsg("University name must be entered", request, response);
                                    //isValid = false;
                                }
                                break;
                            case "WrittenRubrics":
                                WrittenRubrics = item.getString();
                                if (WrittenRubrics.equals("")) {
                                    //sendMsg("University name must be entered", request, response);
                                    //isValid = false;
                                }
                                break;
                            case "Comments":
                                Comments = item.getString();
                                if (Comments.equals("")) {
                                    //sendMsg("University name must be entered", request, response);
                                    //isValid = false;
                                }
                                break;
                            case "Obstacles":
                                Obstacles = item.getString();
                                if (Obstacles.equals("")) {
                                    //sendMsg("University name must be entered", request, response);
                                    //isValid = false;
                                }
                                break;
                            case "Improvement":
                                Improvement = item.getString();
                                if (Improvement.equals("")) {
                                    //sendMsg("University name must be entered", request, response);
                                    //isValid = false;
                                }
                                break;
                            case "evidence":
                                evidence = item.getString();
                                if (evidence.equals("")) {
                                    //sendMsg("University name must be entered", request, response);
                                    //isValid = false;
                                }
                                break;

                        }


                        if(!item.isFormField()){

                            System.out.println("else fired!"+ item.getSize());
                            if(item.getSize() != 0) {
                                name = new File(item.getName()).getName();
                                if (item.getSize() < 5000000) {

                                    String extension = "";

                                    int i = item.getName().lastIndexOf('.');
                                    if (i > 0) {
                                        extension = item.getName().substring(i + 1);
                                        System.out.println("file ext !"+ extension);
                                    }
                                    if (extension.equals("pdf")) {
                                        item.write(new File(SERVER_DIRECTORY + File.separator + UPLOAD_DIRECTORY +
                                                File.separator + name));
                                        evidence = "/" + UPLOAD_DIRECTORY + "/" + name;
                                        //File uploaded successfully
                                        System.out.println("File Uploaded Successfully!"+evidence);



                                    } else {
                                        sendMsg("file must be type of PDF",request,response);
                                        isValid = false;
                                    }


                                } else {
                                    sendMsg("Logo image's size exceeds 5 mb",request,response);
                                    isValid = false;
                                }
                            }else{
                                evidence = null;
                            }
                        }
                    }

                    if(isValid){
                        F_Update dba = new F_Update();
                        if (evidence != null) {
                            dba.updateFormF(WrittenRubrics, Comments, Obstacles, Improvement, evidence, Integer.parseInt(Formative_ID));
                        } else {
                            dba.updateFormF(WrittenRubrics, Comments, Obstacles, Improvement, null, Integer.parseInt(Formative_ID));
                        }
                        sendMsg("user Updated",request,response);
                        //redirectURL= "/form/index.jsp?page=fillForm&type=formative&id="+Formative_ID;
                    }else {
                        redirectURL= "/form/index.jsp?page=fillForm&type=formative&id="+Formative_ID;
                    }


                } catch (Exception ex) {
                    System.out.println("File Upload Failed due to " + ex);
                }

                //response.sendRedirect("/settings/index.jsp?status=SystemUpdated");

            }else{
                System.out.println("not multipart!");
                redirectURL = "/form/index.jsp?page=fillForm&type=formative&id="+Formative_ID;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
            response.sendRedirect( redirectURL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void sendMsg(String msg, HttpServletRequest request, HttpServletResponse response){


        if(request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg",msg);


        /*System.out.println("ErrMsg : "+msg);

        System.out.println("session is : "+request.getSession().getId());
        request.getSession().setAttribute("errMsg",msg);
        //request.getSession().setAttribute("userValue",userVal);


        try {
            response.sendRedirect("/settings/index.jsp?page=update");
            //request.getRequestDispatcher("/settings/index.jsp?page=update").forward(request,response);
        } *//*catch (ServletException e) {
            e.printStackTrace();
        }*//* catch (IOException e) {
            e.printStackTrace();
        }
        *//*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        try {
            response.setHeader("Location","/users/index.jsp?page=add&status="+ URLEncoder.encode(msg, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*//*

        return;*/
    }

}
