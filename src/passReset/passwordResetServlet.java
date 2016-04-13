package passReset;

import ASDB.U_AS_Select;
import ASDB.U_AS_Update;
import login.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "passwordResetServlet", urlPatterns = {"/passReset"})
public class passwordResetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("/////////////////////////passwordResetServlet ");
        response.setContentType("text/plain");

        String uEmail = request.getParameter("email");
        String uNewPassword = request.getParameter("pw");
        System.out.println("uEmail : "+uEmail);
        System.out.println("uNewPassword : "+uNewPassword);

        PrintWriter out=response.getWriter();

        U_AS_Update dbu = new U_AS_Update();
        U_AS_Select dbs = new U_AS_Select();

        try {
            String hashedPassword = Password.getSaltedHash(uNewPassword);
            int ulvl = dbs.selectUserLevel(uEmail);
            System.out.println("ulvl : "+ulvl);
            switch (ulvl){
                case 0:
                case 1:
                    dbu.updateSuperuserPassword(hashedPassword,uEmail);
                    break;
                case 2:
                    dbu.updateFacultyPassword(hashedPassword,uEmail);
                    break;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.print("<strong class=\"green\">Password has been reset!</strong>" +
                "<script>" +
                "$('#loginDiv').show();\n" +
                "$('#passResetDiv').hide();\n" +
                "</script>");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
