package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "AddPILinks",
        urlPatterns = {"/AddPILinks"})
public class AddPILinks extends HttpServlet {
    String[] LinkVal;

    /**
     * insert or upload performance indicator links. in case of changing performance indicator from Formative to Summative or opposite it will be deleting from one and inserting to other. Redirect to the performance indicator links page.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("OutValue").equals("null")) {

            C_AS_Insert dba = new C_AS_Insert();
            C_AS_Select sdba = new C_AS_Select();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            int R=0;
            int Link_id=0;
            String type="";

            try {
                if(request.getParameter("PI") == null || request.getParameter("PI").equals("")){
                    LinkVal = new String[]{request.getParameter("Out"),request.getParameter("PI"), request.getParameter("Course"),request.getParameter("Type")};
                    sendErrMsg("You must select a performance indicator, or create on if there is none",request.getParameter("cycle"),request,response);

                }else if(sdba.isPILinkExist(Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),Integer.parseInt(request.getParameter("programID")), request.getParameter("Course"),Integer.parseInt(Termid),request.getParameter("Type"))){
                    LinkVal = new String[]{request.getParameter("Out"),request.getParameter("PI"), request.getParameter("Course"),request.getParameter("Type")};
                    sendErrMsg("The selected values for the link are already existed",request.getParameter("cycle"),request,response);
                }else{
                    R=dba.addRubric(request.getParameter("firstD"),request.getParameter("secondD"),request.getParameter("thirdD"),request.getParameter("forthD"));
                    Link_id=dba.addPILink(Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),Integer.parseInt(request.getParameter("programID")), R, request.getParameter("Course"),Integer.parseInt(Termid),request.getParameter("Type"));



                    ArrayList<Integer> courseSections = sdba.selectTermCourseSection(request.getParameter("Course"), Integer.parseInt(Termid));
                    if (Link_id!=0) {

                        type = sdba.selectFormType(Link_id);
                        if (type.equals("Formative")) {
                            for(int i=0;i<courseSections.size();i++){
                                dba.addFormF(Link_id,courseSections.get(i));
                            }


                        } else if (type.equals("Summative")) {
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormS(Link_id, courseSections.get(i));
                            }
                        } else {
                        }
                    }
                    Auditor.add((String)request.getSession().getAttribute("username"),"Added new Performance Indicator Link (Cycle ID : "+id+")");


                    response.sendRedirect("/cycle/index.jsp?cycle="+id+"&term="+Termid+"&page=LinkPIOutList&programID="+request.getParameter("programID"));

                }




            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            C_AS_Insert dba = new C_AS_Insert();
            C_AS_Update dbu = new C_AS_Update();
            C_AS_Select sdba = new C_AS_Select();
            C_AS_Delete dbd = new C_AS_Delete();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            int Link_id = Integer.parseInt(request.getParameter("LinkID"));

            String type="";
            try {

                if(sdba.isPILinkExistExcept(Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),
                        Integer.parseInt(request.getParameter("programID")), request.getParameter("Course"),Integer.parseInt(Termid),
                        request.getParameter("Type"),Integer.parseInt(request.getParameter("LinkID")))){
                    LinkVal = new String[]{request.getParameter("Out"),request.getParameter("PI"), request.getParameter("Course"),request.getParameter("Type")};
                    sendErrMsg("The selected values for the link are already existed",request.getParameter("cycle"),request,response);
                }else {

                    dbu.updatePILink(Integer.parseInt(request.getParameter("LinkID")),Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),Integer.parseInt(request.getParameter("programID")),Integer.parseInt(request.getParameter("RubricValue")),request.getParameter("Course"),Integer.parseInt(Termid),request.getParameter("Type"));
                    dbu.updateRubrics(request.getParameter("firstD"),request.getParameter("secondD"),request.getParameter("thirdD"),request.getParameter("forthD"),Integer.parseInt(request.getParameter("RubricValue")));

                    ArrayList<Integer> courseSections = sdba.selectTermCourseSection(request.getParameter("Course"), Integer.parseInt(Termid));
                    type = sdba.selectFormType(Link_id);
                    if(!request.getParameter("oldTypeValue").equals(request.getParameter("Type"))){
                        if (type.equals("Formative")) {
                            dbd.deleteForm(Link_id, 1);
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormF(Link_id,courseSections.get(i) );
                            }
                        } else if (type.equals("Summative")) {
                            dbd.deleteForm(Link_id,0);
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormS(Link_id,courseSections.get(i));
                            }
                        } else {
                        }
                    }else if (type.equals("Summative")){
                    }

                    if(!request.getParameter("oldCourseValue").equals(request.getParameter("Course"))){
                        if (type.equals("Formative")) {
                            dbd.deleteForm(Link_id, 1);
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormF(Link_id,courseSections.get(i) );
                            }
                        } else if (type.equals("Summative")) {
                            dbd.deleteForm(Link_id,0);
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormS(Link_id, courseSections.get(i));
                            }
                        } else {
                        }
                    }

                    Auditor.add((String)request.getSession().getAttribute("username"),"Updated Performance Indicator Link (ID : "+Link_id+")");

                    response.sendRedirect("/cycle/index.jsp?cycle="+id+"&term="+Termid+"&page=LinkPIOutList&programID="+request.getParameter("programID"));

                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * show error message
     * @param msg String
     * @param cycle String
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void sendErrMsg(String msg,String cycle,HttpServletRequest request, HttpServletResponse response){



        request.getSession().setAttribute("errMsg",msg);
        request.getSession().setAttribute("LinkVal", LinkVal);


        try {
            if(request.getParameter("OutValue").equals("null")) {

                response.sendRedirect("/cycle/index.jsp?page=addPILinks&cycle="+cycle+"&term="+request.getParameter("term")+
                        "&programID="+request.getParameter("programID"));
            }else {

                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location","/cycle/index.jsp?page=updatePILink&cycle="+cycle+"&term="+request.getParameter("term")+
                        "&programID="+request.getParameter("programID")+"&LinkID="+request.getParameter("LinkID")+
                        "&OutValue="+request.getParameter("OutValue")+"&PIValue="+request.getParameter("PIValue")+
                        "&CourseValue"+request.getParameter("CourseValue")+"&TypeValue="+request.getParameter("TypeValue")+
                        "&PValue="+request.getParameter("PValue")+"&RubricValue="+request.getParameter("RubricValue")+
                        "&TermValue="+request.getParameter("TermValue"));


            }
        }  catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }

}
