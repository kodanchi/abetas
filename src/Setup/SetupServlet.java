package Setup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "SetupServlet", urlPatterns = "/setup")
public class SetupServlet extends HttpServlet {

    /**
     * This function will ask the user whether to remove the database if it exists or not.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        InstallDB dbcon = new InstallDB(out);

        if(request.getParameter("dbRemove")!=null)
        {
            switch (request.getParameter("dbRemove")){

                case "yes":
                    try {
                        dbcon.deleteDB();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect("/setup/install.jsp");
                    break;
                case "no":
                    response.sendRedirect("/index.jsp");
                    break;
            }
        }

    }

    /**
     * This method contains the required html content in order to ask whether to setup the system or not.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        InstallDB dbcon = new InstallDB(out);
        try {
            if(dbcon.setUpChk()){
                out.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <title>ABETAS</title>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "  <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">\n" +
                        "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>\n" +
                        "  <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div class=\"container\">\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  <section id=\"wizard\">\n" +
                        "      <div class=\"page-header\">\n" +
                        "        <h1>ABETAS SETUP</h1>\n" +
                        "      </div>\n" +
                        "\n" +
                        "      <div >\n" +
                        "\n" +
                        "\n" +

                        "    <form method=\"post\" action=\"#\">\n" +
                        "        <div class=\"panel panel-danger\">\n" +
                        "            <div class=\"panel-heading\">\n" +
                        "                <h3 class=\"panel-title\">Database Does exist!</h3>\n" +
                        "            </div>\n" +
                        "            <div class=\"panel-body\">\n" +
                        "                <h4>Do you want to delete the current database!?</h4>\n" +
                        "                <p>\n" +
                        "                <label for=\"selectRes\" >Delete database!? </label>\n" +
                        "                    <select id=\"selectRes\" name=\"dbRemove\">\n" +
                        "                        <option value=\"yes\">Yes</option>\n" +
                        "                        <option value=\"no\">No</option>\n" +
                        "                    </select>\n" +
                        "                </p>\n" +
                        "                <button class=\"btn btn-default\" type=\"submit\">Go</button>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </form>\n" +
                        "\n" +
                        "              </div>\n" +
                        "            </div>\n" +
                        "    </section>\n" +
                        "  </div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");
            }else {
                response.sendRedirect("/setup/install.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
