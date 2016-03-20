package login;

import ASDB.AS_Select;
import sessionListener.CookiesControl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Mojahed on 1/24/2016.
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class loginServlet extends HttpServlet {
    AS_Select adb = new AS_Select();
    boolean successLogin = false;
    //private User user;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login post");

        //If the user info is in session, move forward to another page
        String forward = "/index.jsp";


        if(request.getParameter("login")!=null) {
            if (request.getParameter("login").equals("Login")) {


                String username = request.getParameter("userName");
                String password = request.getParameter("userPassword");
                System.out.println("ur password :" + password);
                String hashedPass = "";

                if ((username != null && password != null) && (!username.equals("") && !password.equals(""))) {

                    try {
                        hashedPass = Password.getSaltedHash(password);
                        System.out.println("ur hashed password :" + hashedPass);


                        String[] userData = adb.login(username);

                        if (userData != null) {
                            int userLvl = Integer.parseInt(userData[0]);
                            String userPassword = userData[1];
                            String userEmail = userData[2];
                            String userID = userData[3];
                            //System.out.println("ur hashed password :"+hashedPass);
                            System.out.println("user password :" + userPassword);
                            System.out.println("checking :" + Password.check(password, userPassword));
                            if (Password.check(password, userPassword)) {

                                //login success


                                // Session Object
                                HttpSession session = request.getSession();
                                session.setAttribute("username",username);
                                session.setAttribute("userEmail",userEmail);
                                session.setAttribute("userLvl",userLvl);
                                session.setAttribute("userId",userID);
                                System.out.println("Session username : "+request.getSession().getAttribute("username"));
                                System.out.println("Session userEmail : "+request.getSession().getAttribute("userEmail"));
                                System.out.println("Session userLvl : "+request.getSession().getAttribute("userLvl"));
                                System.out.println("Session userID : "+request.getSession().getAttribute("userId"));

                                //cookie
                                //System.out.println("checking value : "+request.getParameter("remember"));
                                if (request.getParameter("remember") != null) {
                                    CookiesControl.addCookie(response, "userCookie", userEmail, 60 * 60 * 60 * 30);
                                } else {
                                    CookiesControl.addCookie(response, "userCookie", userEmail, 60 * 60 * 2);
                                }

                                successLogin = true;
                                response.getWriter().print("Login Success!");
                                forward ="/index.jsp";


                            } else {
                                //login failed
                                forward = "/login.jsp?status=failedLogin";
                            }
                        } else {
                            //login failed
                            forward = "/login.jsp?status=failedLogin";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    //login failed
                    forward = "/login.jsp?status=missingData";
                }

                //if(!successLogin) {
                    //Forward
                    //this.getServletContext().getRequestDispatcher(forward).forward(request, response);
                    response.sendRedirect(forward);
                //}
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //response.sendRedirect("/login.jsp");




    }
}
