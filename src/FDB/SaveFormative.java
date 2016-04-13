package FDB;

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
import java.util.List;

@WebServlet(name = "SaveFormative",
        urlPatterns = {"/SaveFormative"})
public class SaveFormative extends HttpServlet {
    private String WrittenRubrics,Comments,Obstacles,Improvement,evidence,Formative_ID = null;
    private final String UPLOAD_DIRECTORY = "uploads";
    private String SERVER_DIRECTORY ;
    private boolean isValid = true;

    /**
     * post method that serves by saving formative form.
     * That is done considering the written rubrics, comments from the faculty members, their point of obstacles, where it can be improved and the evidence.
     * The use of Formative ID parameter is a must to identify the specified form.
     * The size of the evidence file must be checked so that it will not exceed the limits.
     * The validation of the fields are also included.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        SERVER_DIRECTORY = getServletContext().getRealPath("/");
        String redirectURL = "/form/index.jsp";

        try {
            //process only if its multipart content
            if(ServletFileUpload.isMultipartContent(request)){

                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    for(FileItem item : items){
                        String name = item.getFieldName();
                        switch (name) {
                            case "Formative_ID":
                                Formative_ID = item.getString();
                                if (Formative_ID.equals("")) {
                                }
                                break;
                            case "WrittenRubrics":
                                WrittenRubrics = item.getString();
                                if (WrittenRubrics.equals("")) {
                                }
                                break;
                            case "Comments":
                                Comments = item.getString();
                                if (Comments.equals("")) {
                                }
                                break;
                            case "Obstacles":
                                Obstacles = item.getString();
                                if (Obstacles.equals("")) {
                                }
                                break;
                            case "Improvement":
                                Improvement = item.getString();
                                if (Improvement.equals("")) {
                                }
                                break;
                            case "evidence":
                                evidence = item.getString();
                                if (evidence.equals("")) {
                                }
                                break;

                        }


                        if(!item.isFormField()){
                        if(item.getSize() != 0) {
                                name = new File(item.getName()).getName();
                                if (item.getSize() < 100000000) {

                                    String extension = "";

                                    int i = item.getName().lastIndexOf('.');
                                    if (i > 0) {
                                        extension = item.getName().substring(i + 1);
                                    }
                                    if (extension.equals("pdf")) {
                                        item.write(new File(SERVER_DIRECTORY + File.separator + UPLOAD_DIRECTORY +
                                                File.separator + name));
                                        evidence = "/" + UPLOAD_DIRECTORY + "/" + name;
                                        //File uploaded successfully



                                    } else {
                                        sendMsg("file must be type of PDF",request,response);
                                        isValid = false;
                                    }


                                } else {
                                    sendMsg("Evidence file's size exceeds 100 mb",request,response);
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
                        sendMsg("Form is saved",request,response);
                        //redirectURL= "/form/index.jsp?page=fillForm&type=formative&id="+Formative_ID;
                    }else {
                        redirectURL= "/form/index.jsp?page=fillForm&type=formative&id="+Formative_ID;
                    }


                } catch (Exception ex) {
                }


            }else{
                redirectURL = "/form/index.jsp?page=fillForm&type=formative&id="+Formative_ID;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
            response.sendRedirect( redirectURL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Function that handels the message sending.
     * @param msg
     * @param request
     * @param response
     */
    protected void sendMsg(String msg, HttpServletRequest request, HttpServletResponse response){


        if(request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg",msg);

    }

}
