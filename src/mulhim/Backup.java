package mulhim;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mohammed on 2/1/2016.
 * https://malalanayake.wordpress.com/2012/09/28/take-the-mysql-db-backup-in-java/
 *
 */
@WebServlet(name = "backup",
        urlPatterns = {"/Backup"})
public class Backup extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    }
