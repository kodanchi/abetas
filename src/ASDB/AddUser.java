
package ASDB;

import com.database.ASDB;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Ibrahim Abuaqel on 1/19/2016.
 */

@WebServlet(name = "AddUser",
        urlPatterns = {"/addUser"})
public class AddUser extends HttpServlet {
    String[] userVal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#############################Add user#############################");
        U_AS_Insert idb = new U_AS_Insert();
        U_AS_Select sdb = new U_AS_Select();
        try {
            String userType = request.getParameter("userType");
            String uname = request.getParameter("uname");
            String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String uemail = (request.getParameter("uemail") != null ? request.getParameter("uemail") : "");
            userVal = new String[]{userType, fname, mname, lname, uname, uemail};

            System.out.println(userType);
            if(uname.equals("")){
                sendErrMsg("username Required!",request,response);
            }else {
                if(sdb.selectUserIfExist(uname)){
                    sendErrMsg("Username Exist",request,response);
                }else {
                    if(userType.equals("Superuser")||userType.equals("Faculty_Member")) {
                        if(!checkEmailValidation(request.getParameter("uemail"))){
                            sendErrMsg("The Email is not in the proper format",request,response);
                        }else if(sdb.selectEmailIfExist(request.getParameter("uemail"))){
                            sendErrMsg("The Email is already exist",request,response);
                        }else{
                            if(userType.equals("Superuser"))
                                idb.addUser(0, uname, uemail, fname, mname, lname);
                            else if (request.getParameter("userType").equals("Faculty_Member"))
                                idb.addUser(1, uname, uemail, fname, mname, lname);

                            response.sendRedirect("/users/index.jsp?status=userAdded");

                        }
                    }else if (request.getParameter("userType").equals("Evaluator")){
                        idb.addUser(2, uname, null, fname, mname, lname);
                        response.sendRedirect("/users/index.jsp?status=userAdded"); 
                    }
                }
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        out.println("update");
*/

        // New location to be redirected
        // String site = new String("http://localhost:8081/");
        // response.setHeader("Location", site);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void sendErrMsg(String msg,HttpServletRequest request, HttpServletResponse response){


        System.out.println("ErrMsg : "+msg);

            System.out.println("session is : "+request.getSession().getId());
            request.getSession().setAttribute("errMsg",msg);
            request.getSession().setAttribute("userValue",userVal);


        try {
            request.getRequestDispatcher("/users/index.jsp?page=add").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        try {
            response.setHeader("Location","/users/index.jsp?page=add&status="+ URLEncoder.encode(msg, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        return;
    }

    protected boolean checkEmailValidation(String email) {
        boolean result = true;
        try {
            InternetAddress emailChecker = new InternetAddress(email);
            emailChecker.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}

