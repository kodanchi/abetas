package FDB;

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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim Abuaqel on 2/24/2016.
 */
@WebServlet(name = "SaveSummative",
        urlPatterns = {"/SaveSummative"})
public class SaveSummative extends HttpServlet {
    private String evidence, Summative_ID, studentsNumber,dateInput = null;
    private ArrayList<String> optionsRadios, SID = null;
    private final String UPLOAD_DIRECTORY = "uploads";
    private String SERVER_DIRECTORY ;
    private boolean isValid = true;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(request.getParameter("Summative_ID")+"            SaveSummative ))))))))))))))))))))))))))))))))))))))))))))))))))))))");

        //ArrayList<String> data = new ArrayList<String>();
        System.out.println("#################################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");


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
                        /*System.out.println(item.getString()+" ----item");
                        System.out.println(item.getFieldName()+" --++--item");*/

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


                        /*if(name.startsWith("optionsRadios")){
                            System.out.println("optionsRadios is : "+name + " = "+ item.getString());
                            String num = item.getFieldName().substring(13);
                            System.out.println("substering : "+num);
                            for (FileItem i:items){
                                String SIDname = i.getFieldName();
                                if(SIDname.equals("SID"+num)){
                                    System.out.println("SID is : "+SIDname+ " = "+ item.getString());
                                    SID.add(i.getString());
                                    optionsRadios.add(item.getString());
                                }
                            }*/

                        if(name.startsWith("optionsRadios")){
                            System.out.println("optionsRadios is : "+name + " = "+ item.getString());
                            String num = item.getFieldName().substring(13);
                            System.out.println("substering : "+num);
                            for (FileItem i:items){
                                String SIDname = i.getFieldName();
                                if(SIDname.equals("SID"+num)){
                                    System.out.println("SID is : "+SIDname+ " = "+ i.getString());
                                    SID.add(i.getString());
                                    optionsRadios.add(item.getString());
                                }
                            }


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
                        F_Delete dbd = new F_Delete();
                        F_Insert dbi = new F_Insert();
                        if (evidence != null) {
                            dba.updateFormS(evidence, Integer.parseInt(Summative_ID));
                        } else {
                            dba.updateFormS(null, Integer.parseInt(Summative_ID));
                        }
                        int size=0;
                        size=Integer.parseInt(studentsNumber);
                        dbd.deleteSummRub(Integer.parseInt(Summative_ID));
                        for (int i =0; i < size; i++){

                            System.out.println("student_rubric : "+optionsRadios.get(i)+",SID : "+SID.get(i));
                            dbi.addSummativeRubric(Integer.parseInt(Summative_ID),optionsRadios.get(i),Integer.parseInt(SID.get(i)));
                        }
                        sendMsg("Form No:"+Summative_ID+" has been successfully saved",request,response);
                        //redirectURL= "/form/index.jsp?page=fillForm&type=formative&id="+Formative_ID;
                    }else {
                        redirectURL= "/form/index.jsp?page=fillForm&type=summative&id="+Summative_ID;
                    }


                } catch (Exception ex) {
                    System.out.println("File Upload Failed due to " + ex);
                }

                //response.sendRedirect("/settings/index.jsp?status=SystemUpdated");

            }else{
                System.out.println("not multipart!");
                redirectURL = "/form/index.jsp?page=fillForm&type=summative&id="+Summative_ID;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect( redirectURL);
    }

/*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(request.getParameter("WrittenRubricsV")+"            S Servlet ))))))))))))))))))))))))))))))))))))))))))))))))))))))");

        //ArrayList<String> data = new ArrayList<String>();
        System.out.println("#################################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

        int id = 0;
        F_Update dba = new F_Update();
        F_Delete dbd = new F_Delete();
        F_Insert dbi = new F_Insert();
        //AS_Select dbaS=new AS_Select();
        try {
            //id=dbaS.selectProgram(request.getParameter("Pname"));
            if (request.getParameter("evidence") != null) {
                dba.updateFormS(request.getParameter("evidence"), Integer.parseInt(request.getParameter("Summative_ID")));
            } else {
                dba.updateFormS(null, Integer.parseInt(request.getParameter("Summative_ID")));
            }
            int size=0;
            size=Integer.parseInt(request.getParameter("studentsNumber"));
            dbd.deleteSummRub(Integer.parseInt(request.getParameter("Summative_ID")));
            for (int i =0; i < size; i++){

                dbi.addSummativeRubric(Integer.parseInt(request.getParameter("Summative_ID")),request.getParameter(("optionsRadios"+i)),Integer.parseInt(request.getParameter(("SID"+i))));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        //out.println(id+"       fggfdggfdgdgdsffdgdgffgggdfdgdffd");

        //System.out.println(data.get(0)+"                vdgfsg            "+data.get(1));
        //response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        //response.setHeader("Location", "http://localhost:8081/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        response.sendRedirect( "/form/index.jsp");
    }
*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void sendMsg(String msg, HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg", msg);

    }

}
