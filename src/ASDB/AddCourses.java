package ASDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ibrahim Abuaqel on 2/4/2016.
 */
@WebServlet(name = "AddCourses",
        urlPatterns = {"/Add Courses"})
public class AddCourses extends HttpServlet {
    String[] courseVal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(request.getParameter("Code")+"           "+request.getParameter("Cname")+"             AdObj Servlet");
        //ArrayList<String> data = new ArrayList<String>();
        if (request.getParameter("CourseName").equals("null")) {
            int id=0;
        AS_Insert dba=new AS_Insert();
        AS_Select dbaS=new AS_Select();
        try {
            courseVal = new String[]{request.getParameter("Code"),request.getParameter("Cname"),request.getParameter("level")};
            if(!dbaS.isCoursesCodeExist(request.getParameter("Code"))){
                //id=dbaS.selectProgram(request.getParameter("Pname"));
                dba.addCourse(request.getParameter("Code"),request.getParameter("Cname"),Integer.parseInt(request.getParameter("level")),0,Integer.parseInt(request.getParameter("id")));
                //System.out.println(data.get(0)+"                vdgfsg            "+data.get(1));
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                //response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
                response.setHeader("Location", "/program/index.jsp?page=CoursesList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));

            }else {
                sendErrMsg("Course code is already exists",request.getParameter("name"),request.getParameter("id"),request,response);
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

        }
        else {
            System.out.println("#########################NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
            System.out.println(request.getParameter("Outid")+"            AdObj Servlet IDIDIDIDIDDIIDID");

            int id = 0;
            AS_Update dba = new AS_Update();
            AS_Select dbaS=new AS_Select();
            try {
                courseVal = new String[]{request.getParameter("Code"),request.getParameter("Cname"),request.getParameter("level")};
                if(request.getParameter("Code").equals("") || request.getParameter("Cname").equals("")){
                    sendErrMsg("You must fill the course name and code",request.getParameter("name"),request.getParameter("id"),request,response);
                }else {
                    if(!dbaS.isCoursesCodeExistExcept(request.getParameter("Code"),Integer.parseInt(request.getParameter("Cid")))) {
                        //id=dbaS.selectProgram(request.getParameter("Pname"));
                        System.out.println(request.getParameter("Code") + "           " + request.getParameter("Cname") + "          Update   AdObj Servlet");
                        dba.updateCourse(Integer.parseInt(request.getParameter("Cid")), request.getParameter("Code"), request.getParameter("Cname"), Integer.parseInt(request.getParameter("level")));
                    }else {
                        sendErrMsg("Course code is already exists",request.getParameter("name"),request.getParameter("id"),request,response);

                    }
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
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            //response.setHeader("Location", "/program/index.jsp?page=OutcomeList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
            response.setHeader("Location", "/program/index.jsp?page=CoursesList&name="+request.getParameter("name")+"&id="+request.getParameter("id"));
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void sendErrMsg(String msg,String pname,String pid,HttpServletRequest request, HttpServletResponse response){


        System.out.println("ErrMsg : "+msg);

        System.out.println("session is : "+request.getSession().getId());
        request.getSession().setAttribute("errMsg",msg);
        request.getSession().setAttribute("courseVal",courseVal);


        try {
            if(request.getParameter("CourseName").equals("null")) {
                //request.getRequestDispatcher("/program/index.jsp?page=add").forward(request, response);
                /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location","/program/index.jsp?page=add");*/
                response.sendRedirect("/program/index.jsp?name="+pname+"&id="+pid+"&page=addCourses&status=err");
            }else {

                String Courseid = request.getParameter("Courseid");
                String CourseName = request.getParameter("CourseName");
                String CourseLevel = request.getParameter("CourseLevel");
                String Cid = request.getParameter("Cid");
                //request.getRequestDispatcher("/program/index.jsp?page=update").forward(request, response);
                response.sendRedirect("/program/index.jsp?page=updateCourses&name="+pname+"&id="+pid+"&Courseid="+ Courseid+"&Cid="+ Cid +"&CourseName="+CourseName+"&CourseLevel="+CourseLevel);
            }
        } /*catch (ServletException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }

}
