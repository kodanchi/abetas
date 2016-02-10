package filters;

import com.sun.org.apache.bcel.internal.generic.FADD;
import sessionListener.CookiesControl;
import sessionListener.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by Mojahed on 2/10/2016.
 */
@WebFilter(filterName = "UsersFilter")
public class UsersFilter implements Filter {
    FilterConfig config = null;
    private ArrayList<String> urlList;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        System.out.println("param : "+config.getInitParameter("avoid-urls"));

        StringTokenizer token = new StringTokenizer(config.getInitParameter("avoid-urls"), ",");

        urlList = new ArrayList<String>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());

        }

        boolean allowed = false;

        String forward = "/login/login.jsp";

        String url = request.getServletPath();
        System.out.println("URL: "+url);




            String l_persistentCookieId = CookiesControl.getCookieValue(request, "MY_SESSION_COOKIE");

            if(l_persistentCookieId != null) {


                if (request.getSession(false) != null) {

                    System.out.println("sID : "+ request.getSession().getId());
                    //Get the user
                    ArrayList<String> user = (ArrayList<String>) request.getSession().getAttribute("user");

                    //If there's a user
                    if (user != null) {
                        System.out.println("User exists!"+user);
                        allowed = true;
                        if (url.startsWith("/login")) {
                            allowed = false;
                            forward = "/index.jsp";
                        }
                    } else {
                        System.out.println("User Not exists!");
                        CookiesControl.removeCookie(response, "MY_SESSION_COOKIE");
                    }

                } else {
                    System.out.println("session is null!");
                    CookiesControl.removeCookie(response, "MY_SESSION_COOKIE");
                    request.getSession(true);

                }
            } else {
                System.out.println("cookie is null!");
                request.getSession(true);

            }

        //System.out.println(url.split("/",3)[1] +" split");
        if(urlList.contains(url.split("/",3)[1])) {
            allowed = true;

        }
        if(!allowed){
            response.sendRedirect(forward);
        }

            /*// If a persistent session cookie has been created
            if (l_persistentCookieId != null) {
                System.out.println("here the : "+l_persistentCookieId);


                HashMap<String, HttpSession> l_activeUsers = (HashMap<String, HttpSession>) request.getServletContext().getAttribute("activeUsers");
                // Get the existing session
                l_session = l_activeUsers.get(l_persistentCookieId);

                if(l_session != null){
                    //Get the user
                    User user = (User) l_session.getAttribute("user");

                    //If there's no user
                    if (user == null) {
                        response.sendRedirect("/login/login.jsp");
                    }
                }else {

                    response.sendRedirect("/login/login.jsp");
                }

            }else {

            }
*/

        chain.doFilter(req, resp);




    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;

    }

}
