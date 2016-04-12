package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SuperuserLevelFilter is used to filter the files under cycle, program and users folders and other files (specified in
 * web.xml file) which will be accessible only by superusers otherwise, the user will be redirected to the home page.
 */
@WebFilter(filterName = "SuperuserLevelFilter")
public class SuperuserLevelFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if( request.getSession(false)!= null) {
            if (request.getSession().getAttribute("userLvl") != null) {
                Integer userLvl = (Integer) request.getSession().getAttribute("userLvl");
                switch (userLvl){
                    case 0:
                    case 1:
                        chain.doFilter(req, resp);
                        break;
                    case 2:
                    case 3:
                    default:
                        response.sendRedirect("/index.jsp");
                }

            }else {
                request.getSession().invalidate();
               response.sendRedirect("/login.jsp");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
