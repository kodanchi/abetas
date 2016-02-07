
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
import java.io.PrintWriter;


/**
 * Created by Ibrahim Abuaqel on 1/19/2016.
 */

@WebServlet(name = "UpdateUser",
        urlPatterns = {"/updateUser"})
public class UpdateUser extends HttpServlet {
    String[] userVal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");
        AS_Insert idb = new AS_Insert();
        AS_Delete ddb = new AS_Delete();
        AS_Update udb = new AS_Update();
        AS_Select sdb = new AS_Select();
        try {

            String userType = request.getParameter("userType");
            String uname = request.getParameter("uname");
            String oldUname = request.getParameter("olduname");
            String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String uemail = (request.getParameter("uemail") != null ? request.getParameter("uemail") : "");
            String oldUemail = (request.getParameter("olduemail") != null ? request.getParameter("olduemail") : "");
            userVal = new String[]{request.getParameter("id"),userType, fname, mname, lname, uname, uemail};


            String userNewType = request.getParameter("userType");
            String userOldType = request.getParameter("oldLvl");
            System.out.println(userOldType+" = = "+userNewType);


            System.out.println(userType);
            if(uname.equals("")){
                sendErrMsg("username Required!",request,response);
            }else {
                if(sdb.selectUserIfExist(uname,oldUname)){
                    sendErrMsg("Username Exist",request,response);
                }else {
                    if(userType.equals("Superuser")||userType.equals("Faculty_Member")) {
                        if(!checkEmailValidation(request.getParameter("uemail"))){
                            sendErrMsg("The Email is not in the proper format",request,response);
                        }else if(sdb.selectEmailIfExist(request.getParameter("uemail"),oldUemail)){
                            sendErrMsg("The Email is already exist",request,response);
                        }else{
                            if(userNewType.equals(userOldType)){ //if the user lvl the same

                                //update the table needed
                                if(userNewType.equals("Superuser"))
                                    udb.updateSuperuser(Integer.parseInt(request.getParameter("id")),fname,mname,lname,uname,uemail);
                                else if (userNewType.equals("Faculty_Member"))
                                    udb.updateFaculty(Integer.parseInt(request.getParameter("id")),fname,mname,lname,uname,uemail);

                            }else { //if the user lvl changed

                                //delete the userdata form the old table
                                if(userOldType.equals("Superuser"))
                                    ddb.deleteSuperuser(Integer.parseInt(request.getParameter("id")));
                                else if (userOldType.equals("Faculty_Member"))
                                    ddb.deleteFaculty(Integer.parseInt(request.getParameter("id")));

                                //insert the new userdata to the new level table
                                if(userNewType.equals("Superuser"))
                                    idb.addUser(0, request.getParameter("uname"),uemail ,fname,mname,lname);
                                else if (userNewType.equals("Faculty_Member"))
                                    idb.addUser(1, request.getParameter("uname"),uemail ,fname,mname,lname);


                            }

                            response.sendRedirect("/users/index.jsp?status=userAdded");

                        }
                    }else if (request.getParameter("userType").equals("Evaluator")){
                        if(userNewType.equals(userOldType)){ //if the user lvl the same

                            //update the table needed
                                udb.updateEvaluator(Integer.parseInt(request.getParameter("id")),fname,mname,lname,uname);
                            response.sendRedirect("/users/index.jsp?status=userUpdated");

                        }else { //if the user lvl changed

                            //delete the userdata form the old table
                                ddb.deleteEvaluator(Integer.parseInt(request.getParameter("id")));

                            //insert the new userdata to the new level table
                                idb.addUser(2, request.getParameter("uname"), null, fname, mname, lname);

                            response.sendRedirect("/users/index.jsp?status=userUpdated");



                        }
                    }
                }
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        //out.println("name: " + request.getParameter("name"));
        out.println("update");


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
            request.getRequestDispatcher("/users/index.jsp?page=update").forward(request,response);
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

