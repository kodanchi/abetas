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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Abuaqel on 2/23/2016.
 */
@WebServlet(name = "SubmitSummative",
        urlPatterns = {"/SubmitSummative"})
public class SubmitSummative extends HttpServlet {
    private String evidence, Summative_ID, studentsNumber,dateInput = null;
    private ArrayList<String> optionsRadios, SID = null;
    private final String UPLOAD_DIRECTORY = "uploads";
    private String SERVER_DIRECTORY ;
    private boolean isValid = true;

    /**
     * post method that serves by submitting summative form.
     * That is done considering each student and his/her rubrics through the student's number and the evidence.
     * The use of summmative ID parameter is a must to identify the specified form.
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

                    optionsRadios = new ArrayList<String>();
                    SID = new ArrayList<String>();




                    for(FileItem item : items){

                        String name = item.getFieldName();
                        switch (name) {
                            case "Summative_ID":
                                Summative_ID = item.getString();
                                if (Summative_ID.equals("")) {
                                    //sendMsg("University name must be entered", request, response);
                                    //isValid = false;
                                }
                                break;
                            case "studentsNumber":
                                studentsNumber = item.getString();
                                if (studentsNumber.equals("")) {
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
                            case "dateInput":
                                dateInput = item.getString();
                                if (dateInput.equals("")) {
                                    //sendMsg("University name must be entered", request, response);
                                    //isValid = false;
                                }
                                break;
                        }

                        if(name.startsWith("optionsRadios")){
                            String num = item.getFieldName().substring(13);
                            for (FileItem i:items){
                                String SIDname = i.getFieldName();
                                if(SIDname.equals("SID"+num)){
                                    SID.add(i.getString());
                                    optionsRadios.add(item.getString());
                                }
                            }
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
                        F_Delete dbd = new F_Delete();
                        F_Insert dbi = new F_Insert();
                        if (evidence != null) {
                            dba.updateFormS(evidence, Integer.parseInt(Summative_ID));
                        } else {
                            dba.updateFormS(null, Integer.parseInt(Summative_ID));
                        }

                        dba.updateSubmitFormS(Integer.parseInt(Summative_ID),dateInput);

                        int size=0;
                        size=Integer.parseInt(studentsNumber);
                        dbd.deleteSummRub(Integer.parseInt(Summative_ID));
                        for (int i =0; i < size; i++){

                            dbi.addSummativeRubric(Integer.parseInt(Summative_ID),optionsRadios.get(i),Integer.parseInt(SID.get(i)));
                        }
                        sendMsg("Form No:"+Summative_ID+" has been successfully submitted",request,response);
                    }else {
                        redirectURL= "/form/index.jsp?page=fillForm&type=summative&id="+Summative_ID;
                    }


                } catch (Exception ex) {
                }


            }else{
                redirectURL = "/form/index.jsp?page=fillForm&type=summative&id="+Summative_ID;
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
    protected void sendMsg(String msg, HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg", msg);

    }
}
