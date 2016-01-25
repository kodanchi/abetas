package com.database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Mojahed on 1/24/2016.
 */
@WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")
public class loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Done with post");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("Done with get");


        String pass = request.getParameter("userPassword");
        System.out.println("ur password :"+pass);
        String hashedPass="";
        try {
             hashedPass = Password.getSaltedHash(pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ur hashed password :"+hashedPass);
        try {
            System.out.println("checking :"+Password.check(pass,hashedPass));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
