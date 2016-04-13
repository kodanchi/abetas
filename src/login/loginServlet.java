package login;

import ASDB.AS_Select;
import Listeners.CookiesControl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class loginServlet extends HttpServlet {
    AS_Select adb = new AS_Select();
    boolean successLogin = false;

    /**
     * connect to the database and check if the the username and password is matched, then login to the system.
     * checking if the username or password are empty send the error message
     * create Session Object
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = "/index.jsp";


        if(request.getParameter("login")!=null) {
            if (request.getParameter("login").equals("Login")) {


                String username = request.getParameter("userName");
                String password = request.getParameter("userPassword");
                String hashedPass = "";

                if ((username != null && password != null) && (!username.equals("") && !password.equals(""))) {

                    try {
                        hashedPass = Password.getSaltedHash(password);


                        String[] userData = adb.login(username);

                        if (userData != null) {
                            int userLvl = Integer.parseInt(userData[0]);
                            String userPassword = userData[1];
                            String userEmail = userData[2];
                            String userID = userData[3];
                            if (Password.check(password, userPassword)) {

                                String firstPass = password.length() > 13 ? password.substring(4,10) :
                                        password ;
                                if(firstPass.equals("abetas")){
                                    sendMsg(userEmail,request);
                                    forward = "/login.jsp";
                                }else {
                                    HttpSession session = request.getSession();
                                    session.setAttribute("username",username);
                                    session.setAttribute("userEmail",userEmail);
                                    session.setAttribute("userLvl",userLvl);
                                    session.setAttribute("userId",userID);

                                    if (request.getParameter("remember") != null) {
                                        CookiesControl.addCookie(response, "userCookie", userEmail, 60 * 60 * 60 * 30);
                                        response.addCookie(new Cookie("rememberCookie","true"));
                                    } else {
                                        CookiesControl.addCookie(response, "userCookie", userEmail, 60 * 60 * 2);
                                        response.addCookie(new Cookie("rememberCookie","false"));
                                        session.setMaxInactiveInterval(1200);
                                    }

                                    successLogin = true;
                                    response.getWriter().print("Login Success!");

                                        forward = request.getParameter("backURL");

                                }


                            } else {
                                forward = "/login.jsp?status=failedLogin";
                            }
                        } else {
                            forward = "/login.jsp?status=failedLogin";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    forward = "/login.jsp?status=missingData";
                }

                    response.sendRedirect(forward);
            }
        }

    }

    /**
     * show message
     * @param msg String
     * @param request HttpServletRequest
     */
    protected void sendMsg(String msg, HttpServletRequest request){

        if(request.getSession().getAttribute("loginMsg") == null)
            request.getSession().setAttribute("loginMsg",msg);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





    }
}
