package login;

import ASDB.Settings_Select;
import com.database.Password;
import sessionListener.CookiesControl;
import sessionListener.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Mojahed on 1/24/2016.
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class loginServlet extends HttpServlet {
    Settings_Select adb = new Settings_Select();
    UserDAO udb = new UserDAO();
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

                if (username != null && password != null) {

                    try {
                        hashedPass = Password.getSaltedHash(password);
                        System.out.println("ur hashed password :" + hashedPass);


                        String[] userData = adb.login(username);

                        if (userData != null) {
                            int userLvl = Integer.parseInt(userData[0]);
                            String userPassword = userData[1];
                            String userEmail = userData[2];
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

                                //cookie
                                if (request.getParameter("remember") != null) {
                                    CookiesControl.addCookie(response, "userCookie", userEmail, 60 * 60 * 60 * 30);
                                } else {
                                    CookiesControl.addCookie(response, "userCookie", userEmail, 60 * 60 * 2);
                                }

                                successLogin = true;
                                response.sendRedirect("/index.jsp");


                            } else {
                                //login failed
                                forward = "/login/login.jsp?status=failedLogin";
                            }
                        } else {
                            //login failed
                            forward = "/login/login.jsp?status=failedLogin";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    //login failed
                    forward = "/login/login.jsp?status=missingData";
                }

                if(!successLogin) {
                    //Forward
                    //this.getServletContext().getRequestDispatcher(forward).forward(request, response);
                    response.sendRedirect(forward);
                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/login/login.jsp");




    }
}
