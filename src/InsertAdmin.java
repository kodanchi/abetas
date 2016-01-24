import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Abdullah on 1/21/2016.
 */

@WebServlet(name = "InsertAdmin",
        urlPatterns = {"/InsertAdmin"})
public class InsertAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("##########################################################");
        DBAccessAdmin dba=new DBAccessAdmin();
    try {
        dba.insert(request.getParameter("adminUsername"),request.getParameter("password"),request.getParameter("email"),request.getParameter("adminFirstName"),request.getParameter("adminMiddleName"),request.getParameter("adminLastName"));
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    PrintWriter out = response.getWriter();

    out.println("Insert");

    response.sendRedirect("http://localhost:8081/");
    // New location to be redirected
    // String site = new String("http://localhost:8081/");
    // response.setHeader("Location", site);
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
