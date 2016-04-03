package ASDB;

import login.Password;

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
 * Created by Mojahed on 2/11/2016.
 */
@WebServlet(name = "profileUpdateServlet", urlPatterns = {"/updateProfile"})
public class profileUpdateServlet extends HttpServlet {
    String[] userVal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Settings_Update udb = new Settings_Update();
        Settings_Select sdb = new Settings_Select();
        try {

            int ulvl = Integer.parseInt(request.getParameter("ulvl"));
            int uid = Integer.parseInt(request.getParameter("uid"));
            String uname =  request.getParameter("uname");
            String oldUemail = request.getParameter("uOldemail");


            String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String uemail = request.getParameter("uemail");
            String uOldpass = request.getParameter("uOldPassword");
            String uNewpass = request.getParameter("upassword");
            String reuNewpass = request.getParameter("reupassword");
            userVal = new String[]{request.getParameter("uid"), fname, mname, lname, uemail};

            if(uemail.equals("")){
                sendMsg("Email Required!",request,response);
            }else {
                if(!checkEmailValidation(request.getParameter("uemail"))){
                    sendMsg("The Email is not in the proper format",request,response);
                }else if(sdb.selectEmailIfExist(request.getParameter("uemail"),oldUemail)){
                    sendMsg("The Email is already exist",request,response);
                }else{

                    if(!uOldpass.equals("")){

                        String[] userData = sdb.login(uname);

                        if (userData != null) {
                            String userPassword = userData[1];
                            System.out.println("uOldpass : "+uOldpass);
                            System.out.println("userPassword : "+userPassword);
                            System.out.println("check Password : "+Password.check(uOldpass,userPassword));
                            if(Password.check(uOldpass,userPassword)){
                                if((!uNewpass.equals("") || !reuNewpass.equals(""))) {

                                    if(uNewpass.equals(reuNewpass)){
                                        String hashedPass = Password.getSaltedHash(uNewpass);

                                        System.out.println("new hashed password : "+hashedPass);
                                        //update the table needed
                                        switch (ulvl){
                                            case 0:
                                            case 1:
                                                udb.updateSuperuser(uid,fname,mname,lname,uname,uemail,hashedPass);
                                                break;
                                            case 2:
                                                udb.updateFaculty(uid,fname,mname,lname,uname,uemail,hashedPass);
                                                break;
                                        }
                                    }else {
                                        sendMsg("New Password fields aren't matched!",request,response);
                                    }

                                }else {
                                    //update the table needed
                                    switch (ulvl){
                                        case 0:
                                        case 1:
                                            udb.updateSuperuser(uid,fname,mname,lname,uname,uemail);
                                            break;
                                        case 2:
                                            udb.updateFaculty(uid,fname,mname,lname,uname,uemail);
                                            break;
                                    }
                                }
                            }else {
                                sendMsg("Enter your current password correctly",request,response);
                            }
                        }else {
                            sendMsg("User not found!",request,response);
                        }

                    }else {
                        sendMsg("Enter your current password",request,response);
                    }

                    Auditor.add((String)request.getSession().getAttribute("username"),"Updated user profile");

                    sendMsg("User profile has been updated",request,response);
                    response.sendRedirect("/settings/index.jsp");
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void sendMsg(String msg, HttpServletRequest request,HttpServletResponse response) throws IOException {

        if(request.getSession().getAttribute("Msg") == null)
        request.getSession().setAttribute("Msg",msg);

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
