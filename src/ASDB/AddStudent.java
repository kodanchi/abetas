package ASDB;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParsePosition;


@WebServlet(name = "AddStudent",
        urlPatterns = {"/AddStudent"})
public class AddStudent extends HttpServlet {
    String[] StudentVal;

    /**
     * insert or update student , then redirect to the student list.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("NValue").equals("null")) {

            C_AS_Insert dba = new C_AS_Insert();
            C_AS_Select dbs = new C_AS_Select();
            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");

            try {



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
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                StudentVal = new String[]{request.getParameter("Student_ID"),request.getParameter("Sname")};
                sendErrMsg("Invalid Data!",request.getParameter("cycle"),request,response);
            }

        } else {

            C_AS_Update udb = new C_AS_Update();
            C_AS_Select dbs = new C_AS_Select();

            String id = request.getParameter("cycle");
            String Termid = request.getParameter("term");


            try {
                if(request.getParameter("Sname").equals("") || request.getParameter("Student_ID").equals("")){
                    String sid = request.getParameter("Student_ID") != null ? request.getParameter("Student_ID") : "";
                    String sname = request.getParameter("Sname") != null ? request.getParameter("Sname") : "";
                    StudentVal = new String[]{sid,sname};
                    sendErrMsg("Student ID and Name must be filled",request.getParameter("cycle"),request,response);

                }else if(request.getParameter("Student_ID").length()>20){
                    StudentVal = new String[]{request.getParameter("Student_ID"),request.getParameter("Sname")};
                    sendErrMsg("Student ID Must not be long than 20 digits",request.getParameter("cycle"),request,response);
                }else if(dbs.isStudentIDExistExcept(request.getParameter("Student_ID"), Integer.parseInt(request.getParameter("section")),Integer.parseInt(request.getParameter("S_ID")))){
                    StudentVal = new String[]{request.getParameter("Student_ID"),request.getParameter("Sname")};
                    sendErrMsg("Student ID: "+request.getParameter("Student_ID")+" is exist already in this section",request.getParameter("cycle"),request,response);

                }else {
                    udb.updateStudent(request.getParameter("Student_ID"),request.getParameter("Sname"),Integer.parseInt(request.getParameter("S_ID")));

                    Auditor.add((String)request.getSession().getAttribute("username"),"Updated student (Student ID : "+request.getParameter("Student_ID")+")");

                    response.sendRedirect("/cycle/index.jsp?page=studentList&cycle="+id+"&term="+Termid+"&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+"&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));

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
        request.getSession().setAttribute("StudentVal", StudentVal);


        try {
            if(request.getParameter("NValue").equals("null")) {

                response.sendRedirect("/cycle/index.jsp?page=addStudent&cycle="+cycle+"&term="+request.getParameter("term")+
                        "&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+
                        "&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section"));
            }else {

                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location","/cycle/index.jsp?page=updateStudent&cycle="+cycle+"&term="+request.getParameter("term")+
                        "&programID="+request.getParameter("programID")+"&courseCode="+request.getParameter("courseCode")+
                        "&courseName="+request.getParameter("courseName")+"&section="+request.getParameter("section")+
                        "&NValue="+request.getParameter("NValue")+"&IDValue="+request.getParameter("IDValue"));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }

    /**
     * check if the string is numeric or not, return true or false.
     * @param str String
     * @return
     */
    public static boolean isNumeric(String str)
    {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

}
