package filters;

import sessionListener.CookiesControl;

import javax.jms.Session;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mojahed on 2/10/2016.
 */
@WebFilter(filterName = "UserLevelFilter")
public class UserLevelFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if( request.getSession(false)!= null) {
            if (request.getSession().getAttribute("userLvl") != null) {
                Integer userLvl = (Integer) request.getSession().getAttribute("userLvl");
                switch (userLvl){
                    case 1:

                        break;
                    case 2:
                        response.sendRedirect("/form/");
                        break;
                    case 3:
                        response.sendRedirect("/vis/");
                        break;

                }
                //chain.doFilter(req, resp);

            }else {
                request.getSession().invalidate();
               response.sendRedirect("/login.jsp");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
