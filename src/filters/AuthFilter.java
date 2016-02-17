package filters;

import sessionListener.CookiesControl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Mojahed on 2/10/2016.
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

        System.out.println("param : "+config.getInitParameter("avoid-urls"));

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
            System.out.println("Allowed URL! : "+url.split("/",3)[1]);
        }

        if(!isAllowed){
            response.sendRedirect("/login.jsp");
        }else {
            chain.doFilter(req, resp);
        }


    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;

    }

}
