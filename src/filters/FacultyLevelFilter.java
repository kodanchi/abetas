package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FacultyLevelFilter is used to filter files under form folder which are only accessible by faculty users otherwise,
 * the user will be redirected to home page.
 */
@WebFilter(filterName = "FacultyLevelFilter")
public class FacultyLevelFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if( request.getSession(false)!= null) {
            if (request.getSession().getAttribute("userLvl") != null) {
                Integer userLvl = (Integer) request.getSession().getAttribute("userLvl");
                System.out.println("userLvl :"+userLvl);
                switch (userLvl){
                    case 0:
                        response.sendRedirect("/index.jsp");
                        break;
                    case 1:
                        response.sendRedirect("/index.jsp");
                        break;
                    case 2:
                        chain.doFilter(req, resp);
                        break;
                    case 3:
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
