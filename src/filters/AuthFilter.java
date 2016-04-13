package filters;

import Listeners.CookiesControl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * AuthFilter is used to filter any access to the system files which will check for a valid session attribute (username)
 * and a valid cookie (userCookie), if both true it will allow the user to access the requested page otherwise, the user
 * will be redirected to the login.
 * There are file and folders will be excluded to be filtered which been specified as a parameter called avoid-urls in
 * web.xml (ex: js/css/images files)
 */
@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {

    FilterConfig config = null;
    private ArrayList<String> urlList;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        StringTokenizer token = new StringTokenizer(config.getInitParameter("avoid-urls"), ",");

        urlList = new ArrayList<String>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());

        }
        String url = request.getServletPath();

        boolean isAllowed = false;

        if( request.getSession(false)!= null) {
            if (request.getSession().getAttribute("username") != null) {
                if (CookiesControl.getCookieValue(request, "userCookie") != null) {
                    isAllowed = true;
                }
            }
        }else {
            request.getSession(true);
        }

        if(urlList.contains(url.split("/",3)[1])) {
            isAllowed = true;
        }

        if(!isAllowed){
            request.getHeader("referer");
            response.sendRedirect("/login.jsp");
        }else {
            chain.doFilter(req, resp);
        }


    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;

    }

}
