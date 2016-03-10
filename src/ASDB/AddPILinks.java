package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ibrahim Abuaqel on 2/11/2016.
 */
@WebServlet(name = "AddPILinks",
        urlPatterns = {"/AddPILinks"})
public class AddPILinks extends HttpServlet {
    String[] LinkVal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("OutValue").equals("null")) {
            System.out.println("#########################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE  Course  "+  request.getParameter("Course"));

            C_AS_Insert dba = new C_AS_Insert();
            C_AS_Select sdba = new C_AS_Select();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            int programID = 0;
            int R=0;
            int Link_id=0;
            String type="";
            System.out.println("##########################################################    "+request.getParameter("Out")+"          "+request.getParameter("PI")+"          "+request.getParameter("programID")+"          "+request.getParameter("Course")+"   "+Termid+"       "+request.getParameter("Type"));

            try {
                if(request.getParameter("PI") == null || request.getParameter("PI").equals("")){
                    LinkVal = new String[]{request.getParameter("Out"),request.getParameter("PI"), request.getParameter("Course"),request.getParameter("Type")};
                    sendErrMsg("You must select a performance indicator, or create on if there is none",request.getParameter("cycle"),request,response);

                }else if(sdba.isPILinkExist(Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),Integer.parseInt(request.getParameter("programID")), request.getParameter("Course"),Integer.parseInt(Termid),request.getParameter("Type"))){
                    LinkVal = new String[]{request.getParameter("Out"),request.getParameter("PI"), request.getParameter("Course"),request.getParameter("Type")};
                    sendErrMsg("The selected values for the link are already existed",request.getParameter("cycle"),request,response);
                }else{
                    System.out.println("ttrttttttttttttttttttttttttt  Program id          " + request.getParameter("programID") + "ttrttttttttttttttttttttttttt           ");
                    R=dba.addRubric(request.getParameter("firstD"),request.getParameter("secondD"),request.getParameter("thirdD"),request.getParameter("forthD"));
                    Link_id=dba.addPILink(Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),Integer.parseInt(request.getParameter("programID")), R, request.getParameter("Course"),Integer.parseInt(Termid),request.getParameter("Type"));

                    ArrayList<Integer> courseSections = sdba.selectTermCourseSection(request.getParameter("Course"), Integer.parseInt(Termid));
                    if (Link_id!=0) {
                        System.out.println("in if "+Link_id);

                        type = sdba.selectFormType(Link_id);
                        if (type.equals("Formative")) {
                            System.out.println("in if F");
                            for(int i=0;i<courseSections.size();i++){
                                dba.addFormF(Link_id,courseSections.get(i));
                            }


                        } else if (type.equals("Summative")) {
                            System.out.println("in if S");
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormS(Link_id, Integer.parseInt(request.getParameter("STshold")),courseSections.get(i));
                            }
                        } else {
                            System.out.println("id is not set");
                        }
                    }

                    response.sendRedirect("/cycle/index.jsp?cycle="+id+"&term="+Termid+"&page=LinkPIOutList&programID="+request.getParameter("programID"));

                }




            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
            System.out.println("ttrttttttttttttttttttttttttt  Rubric id          " + R + "ttrttttttttttttttttttttttttt           ");
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            try {
                //request.getRequestDispatcher("/cycle/index.jsp?page=LinkPIOutList&programID="+request.getParameter("programID")).forward(request, response);
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        } else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            C_AS_Insert dba = new C_AS_Insert();
            C_AS_Update dbu = new C_AS_Update();
            C_AS_Select sdba = new C_AS_Select();
            C_AS_Delete dbd = new C_AS_Delete();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            int Link_id = Integer.parseInt(request.getParameter("LinkID"));

            System.out.println("ttrttttttttttttttttttttttttt  PI name          " + request.getParameter("PI") + "   ttrttttttttttttttttttttttttt           ");
            System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww  PI id          " + Integer.parseInt(request.getParameter("OutValue")) + "   wwwwwwwwwwwwwwww           ");
            String type="";
            try {

                if(sdba.isPILinkExistExcept(Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),
                        Integer.parseInt(request.getParameter("programID")), request.getParameter("Course"),Integer.parseInt(Termid),
                        request.getParameter("Type"),Integer.parseInt(request.getParameter("LinkID")))){
                    LinkVal = new String[]{request.getParameter("Out"),request.getParameter("PI"), request.getParameter("Course"),request.getParameter("Type")};
                    sendErrMsg("The selected values for the link are already existed",request.getParameter("cycle"),request,response);
                }else {
                    System.out.println("##########################################################  "+request.getParameter("LinkID")+"    "+request.getParameter("Out")+"          "+request.getParameter("PI")+"          "+request.getParameter("programID")+"          "+request.getParameter("programID")+"      "+request.getParameter("RubricValue")+"  "+request.getParameter("Course")+"   "+Termid+"    "+request.getParameter("Type"));

                    dbu.updatePILink(Integer.parseInt(request.getParameter("LinkID")),Integer.parseInt(request.getParameter("Out")),Integer.parseInt(request.getParameter("PI")),Integer.parseInt(request.getParameter("programID")),Integer.parseInt(request.getParameter("RubricValue")),request.getParameter("Course"),Integer.parseInt(Termid),request.getParameter("Type"));
                    dbu.updateRubrics(request.getParameter("firstD"),request.getParameter("secondD"),request.getParameter("thirdD"),request.getParameter("forthD"),Integer.parseInt(request.getParameter("RubricValue")));
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");

                    ArrayList<Integer> courseSections = sdba.selectTermCourseSection(request.getParameter("Course"), Integer.parseInt(Termid));
                    type = sdba.selectFormType(Link_id);
                    if(!request.getParameter("oldTypeValue").equals(request.getParameter("Type"))){
                        if (type.equals("Formative")) {
                            System.out.println("in if F");
                            dbd.deleteForm(Link_id, 1);
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormF(Link_id,courseSections.get(i) );
                            }
                        } else if (type.equals("Summative")) {
                            System.out.println("in if S");
                            dbd.deleteForm(Link_id,0);
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormS(Link_id, Integer.parseInt(request.getParameter("STshold")),courseSections.get(i));
                            }
                        } else {
                            System.out.println("id is not set");
                        }
                    }else if (type.equals("Summative")){
                        dbu.updateFormThredshold(Link_id,Integer.parseInt(request.getParameter("STshold")));
                    }

                    if(!request.getParameter("oldCourseValue").equals(request.getParameter("Course"))){
                        if (type.equals("Formative")) {
                            System.out.println("in if F");
                            dbd.deleteForm(Link_id, 1);
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormF(Link_id,courseSections.get(i) );
                            }
                        } else if (type.equals("Summative")) {
                            System.out.println("in if S");
                            dbd.deleteForm(Link_id,0);
                            for(int i=0;i<courseSections.size();i++) {
                                dba.addFormS(Link_id, Integer.parseInt(request.getParameter("STshold")),courseSections.get(i));
                            }
                        } else {
                            System.out.println("id is not set");
                        }
                    }


                    response.sendRedirect("/cycle/index.jsp?cycle="+id+"&term="+Termid+"&page=LinkPIOutList&programID="+request.getParameter("programID"));

                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/


            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            try {
                //request.getRequestDispatcher("/cycle/index.jsp?page=LinkPIOutList&programID="+request.getParameter("programID")).forward(request, response);
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void sendErrMsg(String msg,String cycle,HttpServletRequest request, HttpServletResponse response){


        System.out.println("ErrMsg : "+msg);

        System.out.println("session is : "+request.getSession().getId());
        request.getSession().setAttribute("errMsg",msg);
        request.getSession().setAttribute("LinkVal", LinkVal);


        try {
            if(request.getParameter("OutValue").equals("null")) {

                //response.sendRedirect("/cycle/index.jsp?page=addTerm&cycle="+cycle);
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

                /*String termName = request.getParameter("termName");
                String fyear = request.getParameter("fyear");
                String tyear = request.getParameter("tyear");*/
                //request.getRequestDispatcher("/program/index.jsp?page=update").forward(request, response);
                //response.sendRedirect("/program/index.jsp?page=updateLink&name="+cycle+"&id="+pid+"&Linkid="+ Linkid+"&ObjLinkValue="+ObjLinkValue+"&OutLinkValue="+OutLinkValue);
            }
        } /*catch (ServletException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }

}
