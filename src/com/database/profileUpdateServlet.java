package com.database;

import ASDB.AS_Delete;
import ASDB.AS_Insert;
import ASDB.AS_Select;
import ASDB.AS_Update;
import sessionListener.CookiesControl;

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

        AS_Update udb = new AS_Update();
        AS_Select sdb = new AS_Select();
        try {

            int ulvl = Integer.parseInt(request.getParameter("ulvl"));
            int uid = Integer.parseInt(request.getParameter("uid"));
            String uname =  request.getParameter("uname");
            String oldUemail = request.getParameter("uOldemail");


            String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String uemail = request.getParameter("uemail");
            userVal = new String[]{request.getParameter("uid"), fname, mname, lname, uemail};

            if(uemail.equals("")){
                sendErrMsg("Email Required!",request,response);
            }else {
                if(!checkEmailValidation(request.getParameter("uemail"))){
                    sendErrMsg("The Email is not in the proper format",request,response);
                }else if(sdb.selectEmailIfExist(request.getParameter("uemail"),oldUemail)){
                    sendErrMsg("The Email is already exist",request,response);
                }else{

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

                    response.sendRedirect("/settings/index.jsp?status=userUpdated");
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

    protected void sendErrMsg(String msg,HttpServletRequest request, HttpServletResponse response){


        System.out.println("ErrMsg : "+msg);

        System.out.println("session is : "+request.getSession().getId());
        request.getSession().setAttribute("errMsg",msg);
        //request.getSession().setAttribute("userValue",userVal);


        try {
            response.sendRedirect("/settings/index.jsp?page=update");
            //request.getRequestDispatcher("/settings/index.jsp?page=update").forward(request,response);
        } /*catch (ServletException e) {
            e.printStackTrace();
        }*/ catch (IOException e) {
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
