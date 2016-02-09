package login;

import ASDB.AS_Select;
import ASDB.SessionIdentifierGenerator;
import com.database.Password;
import sessionListener.CookiesControl;
import sessionListener.User;
import sessionListener.UserDAO;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

/**
 * Created by Mojahed on 1/24/2016.
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class loginServlet extends HttpServlet {
    AS_Select adb = new AS_Select();
    UserDAO udb = new UserDAO();
    //private User user;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Done with post");

        //If the user info is in session, move forward to another page
        String forward = "/index.jsp";

        String username = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        System.out.println("ur password :"+password);
        //String hashedPass="";

        if(username != null && password != null){

            try {
                //hashedPass = Password.getSaltedHash(password);
                //System.out.println("ur hashed password :"+hashedPass);



                String[] userData = adb.login(username);

                if(userData != null){
                    int userLvl = Integer.parseInt(userData[0]);
                    String userPassword = userData[1];
                    String userEmail = userData[2];
                    //System.out.println("ur hashed password :"+hashedPass);
                    System.out.println("user password :"+userPassword);
                    System.out.println("checking :"+ Password.check(password,userPassword));
                    if(Password.check(password,userPassword)){

                     //login success

                        /*//create new session
                        HttpSession session = request.getSession();
                        //generate uuid for identify the session
                        SessionIdentifierGenerator generator = new SessionIdentifierGenerator();
                        String uid = generator.nextSessionId();
                        System.out.println("uid : "+uid);
                        //set the userdata attributes int the session
                        String[] ud = {username,userLvl};
                        session.setAttribute("username",username);
                        session.setAttribute("userlvl",userLvl);


                        //create new cookie and set the value to be the generated session attribute
                        Cookie cookie = new Cookie("userSessionId",uid);

                        //set the expired time depending on user choice
                        if(request.getParameter("remember") != null){
                            cookie.setMaxAge(60*60*24*365);
                        }else {
                            cookie.setMaxAge(60*60*2);
                        }

                        //send the cookie to the browser
                        response.addCookie(cookie);
                        response.sendRedirect("/index.jsp");*/


                        // Session Object
                        HttpSession l_session = null;

                        String l_sessionCookieId = CookiesControl.getCookieValue(request, "JSESSIONID");
                        String l_persistentCookieId = CookiesControl.getCookieValue(request, "MY_SESSION_COOKIE");

                        // If a session cookie has been created
                        if (l_sessionCookieId != null)
                        {
                            // If there isn't already a persistent session cookie
                            if (l_persistentCookieId == null)
                            {

                                if(request.getParameter("remember") != null){
                                    CookiesControl.addCookie(response, "MY_SESSION_COOKIE", l_sessionCookieId, 60*60*60*30);
                                }else {
                                    CookiesControl.addCookie(response, "MY_SESSION_COOKIE", l_sessionCookieId, 60*60*2);
                                }
                            }
                        }
                        // If a persistent session cookie has been created
                        if (l_persistentCookieId != null)
                        {
                            HashMap<String, HttpSession> l_activeUsers = (HashMap<String, HttpSession>) request.getServletContext().getAttribute("activeUsers");
                            // Get the existing session
                            l_session = l_activeUsers.get(l_persistentCookieId);
                        }
                        // Otherwise a session has not been created
                        if (l_session == null)
                        {
                            // Create a new session
                            l_session = request.getSession();
                        }



                        //Get the user
                        User user = (User) l_session.getAttribute("user");

                        //If there's no user
                        if (user == null)
                        {
                            // Put the user in session
                            if (username != null && password != null)
                            {
                                user = udb.find(username,userLvl);
                                l_session.setAttribute("user", user );

                                forward = "/index.jsp";
                            }
                            // Ask again for proper login
                            else
                            {
                                forward = "/login/login.jsp?status=failedLogin";
                            }
                        }



                    }else {
                        //login failed
                        forward ="/login/login.jsp?status=failedLogin";
                    }
                }else {
                    //login failed
                    forward = "/login/login.jsp?status=failedLogin";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



        }else {
            //login failed
            forward = "/login/login.jsp?status=missingData";
        }

        //Forward
        this.getServletContext().getRequestDispatcher(forward).forward( request, response );


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/login/login.jsp");




    }
}
