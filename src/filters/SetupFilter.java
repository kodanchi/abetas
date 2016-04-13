package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * SetupFilter is used to filter the access to the setup servlet page which will be accessible only when the install.jsp
 * under setup folder is exist otherwise, the user will be redirected to the home page.
 */
@WebFilter(filterName = "SetupFilter")
public class SetupFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        File f = new File(request.getServletContext().getRealPath("/")+"setup/install.jsp");

        if (f.exists()) {
            chain.doFilter(req, resp);
        }else {
            response.sendRedirect("/index.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
