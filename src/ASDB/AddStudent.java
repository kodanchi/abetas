package ASDB;

import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * Created by Ibrahim Abuaqel on 2/15/2016.
 */
@WebServlet(name = "AddStudent",
        urlPatterns = {"/AddStudent"})
public class AddStudent extends HttpServlet {
    String[] StudentVal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("NValue").equals("null")) {
            System.out.println("#########################EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE  SID  "+  request.getParameter("Student_ID"));

            C_AS_Insert dba = new C_AS_Insert();
            C_AS_Select dbs = new C_AS_Select();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            System.out.println("##########################################################  Sname  "+request.getParameter("Sname")+"          ");

            try {
                //System.out.println("ttrttttttttttttttttttttttttt  Program id          " + request.getParameter("programID") + "ttrttttttttttttttttttttttttt           ");
                System.out.println("@@@@@@@@@@@@@@@@@@@    2222");
                System.out.println(request.getParameter("programName"));
                System.out.println(request.getParameter("programID"));
                System.out.println(request.getParameter("courseCode"));
                System.out.println(request.getParameter("courseName"));
                System.out.println(request.getParameter("F_ID"));
                System.out.println(request.getParameter("programName"));
                System.out.println("@@@@@@@@@@@@@@@@@@@    2222");

                System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWW  " + request.getParameter("Sname")+"     "+Termid+"    "+request.getParameter("Student_ID")+"      "+request.getParameter("courseCode")+"     "+request.getParameter("F_ID")+"        "+Termid);


                if(request.getParameter("Sname").equals("") || request.getParameter("Student_ID").equals("")){
                    String sid = request.getParameter("Student_ID") != null ? request.getParameter("Student_ID") : "";
                    String sname = request.getParameter("Sname") != null ? request.getParameter("Sname") : "";
                    StudentVal = new String[]{sid,sname};
                    sendErrMsg("Student ID and Name must be filled",request.getParameter("cycle"),request,response);

                }else if(request.getParameter("Student_ID").length()>20){
                    StudentVal = new String[]{request.getParameter("Student_ID"),request.getParameter("Sname")};
                    sendErrMsg("Student ID Must not be long than 20 digits",request.getParameter("cycle"),request,response);
                }else if(!isNumeric(request.getParameter("Student_ID"))){
                    StudentVal = new String[]{request.getParameter("Student_ID"),request.getParameter("Sname")};
                    sendErrMsg("Student ID Must be numeric",request.getParameter("cycle"),request,response);
                }else if(dbs.isStudentIDExist(request.getParameter("Student_ID"),Integer.parseInt(request.getParameter("section")))){
                    StudentVal = new String[]{request.getParameter("Student_ID"),request.getParameter("Sname")};
                    sendErrMsg("Student ID: "+request.getParameter("Student_ID")+" is exist already in this section",request.getParameter("cycle"),request,response);
                }else {

                    dba.addStudent(request.getParameter("Sname"),request.getParameter("Student_ID"),Integer.parseInt(request.getParameter("section")));

                    Auditor.add((String)request.getSession().getAttribute("username"),"Added new student (Section ID : "+request.getParameter("section")+")");

                    response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+id+"&term="+Termid+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+"&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));

                }
                //Display error message.
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                StudentVal = new String[]{request.getParameter("Student_ID"),request.getParameter("Sname")};
                sendErrMsg("Invalid Data!",request.getParameter("cycle"),request,response);
            }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
            //System.out.println("ttrttttttttttttttttttttttttt  Rubric id          ttrttttttttttttttttttttttttt           ");
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            /*try {

                //request.getRequestDispatcher("/cycle/index.jsp?page=LinkPIOutList&programID="+request.getParameter("programID")).forward(request, response);
            } catch (NullPointerException e) {
                e.fillInStackTrace();
            }*/
        } else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");

            C_AS_Update udb = new C_AS_Update();
            C_AS_Select dbs = new C_AS_Select();

            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");
            System.out.println("##########################################################  Sname  "+request.getParameter("Sname")+"          ");
            System.out.println("##########################################################  Student_ID  "+request.getParameter("Student_ID")+"          ");


            try {
                if(request.getParameter("Sname").equals("") || request.getParameter("Student_ID").equals("")){
                    String sid = request.getParameter("Student_ID") != null ? request.getParameter("Student_ID") : "";
                    String sname = request.getParameter("Sname") != null ? request.getParameter("Sname") : "";
                    StudentVal = new String[]{sid,sname};
                    sendErrMsg("Student ID and Name must be filled",request.getParameter("cycle"),request,response);

                }else if(request.getParameter("Student_ID").length()>10){
                    StudentVal = new String[]{request.getParameter("Student_ID"),request.getParameter("Sname")};
                    sendErrMsg("Student ID Must not be long than 10 digits",request.getParameter("cycle"),request,response);
                }else if(dbs.isStudentIDExistExcept(Long.parseLong(request.getParameter("Student_ID")), Integer.parseInt(request.getParameter("section")),Integer.parseInt(request.getParameter("S_ID")))){
                    StudentVal = new String[]{request.getParameter("Student_ID"),request.getParameter("Sname")};
                    sendErrMsg("Student ID: "+request.getParameter("Student_ID")+" is exist already in this section",request.getParameter("cycle"),request,response);

                }else {
                    //System.out.println("ttrttttttttttttttttttttttttt  Program id          " + request.getParameter("programID") + "ttrttttttttttttttttttttttttt           ");
                    udb.updateStudent(Long.parseLong(request.getParameter("Student_ID")),request.getParameter("Sname"),Integer.parseInt(request.getParameter("S_ID")));

                    Auditor.add((String)request.getSession().getAttribute("username"),"Updated student (Student ID : "+request.getParameter("Student_ID")+")");

                    response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+id+"&term="+Termid+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+"&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));

                }

                //Display error message.
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*PrintWriter out = response.getWriter();
            //out.println("name: " + request.getParameter("name"));
            //out.println("logo: " + request.getParameter("logo"));
            out.println("H");*/
            //System.out.println("ttrttttttttttttttttttttttttt  Rubric id          ttrttttttttttttttttttttttttt           ");
            /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/cycle/index.jsp?page=addTerm");*/
            try {
                //response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+id+"&term="+Termid+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+"&courseName="+request.getParameter("courseName")+"&F_ID="+request.getParameter("F_ID"));
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
        request.getSession().setAttribute("StudentVal", StudentVal);


        try {
            if(request.getParameter("NValue").equals("null")) {

                //response.sendRedirect("/cycle/index.jsp?page=addTerm&cycle="+cycle);
                response.sendRedirect("/cycle/index.jsp?page=addStudent&cycle="+cycle+"&term="+request.getParameter("term")+
                        "&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+
                        "&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));
            }else {

                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location","/cycle/index.jsp?page=updateStudent&cycle="+cycle+"&term="+request.getParameter("term")+
                        "&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+
                        "&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section")+
                        "&NValue="+request.getParameter("NValue")+"&IDValue="+request.getParameter("IDValue"));
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
    public static boolean isNumeric(String str)
    {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

}
