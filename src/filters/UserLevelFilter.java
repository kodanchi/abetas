package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserLevelFilter is used to filter the index.jsp (home page) which can be accessible only by superusers otherwise, the
 * user will be redirected to form main page for faculty member users or to report main page for evaluator users.
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
                    case 0:
                    case 1:
                        chain.doFilter(req, resp);
                        break;
                    case 2:
                        response.sendRedirect("/form/");
                        break;
                    case 3:
                        response.sendRedirect("/report/");
                        break;

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
