
package Setup;

import login.Password;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Ibrahim Abuaqel on 1/19/2016.
 * http://www.javacodegeeks.com/2013/08/file-upload-example-in-servlet-and-jsp.html
 * http://javakart.blogspot.in/2012/11/file-upload-example-using-servlet.html
 * http://stackoverflow.com/questions/3571223/how-do-i-get-the-file-extension-of-a-file-in-java
 */

@WebServlet(name = "InstallServlet",
        urlPatterns = {"/install"})
public class InstallServlet extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "uploads";
    private String SERVER_DIRECTORY ;
    private String uname,cname,afname,amname,alname,ausername,apassword,arepassword,aemail,ulogo = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("##########################################################");

        SERVER_DIRECTORY = getServletContext().getRealPath("/");


        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <title>installing..</title>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <link rel=\"stylesheet\" href=\"/css/bootstrap.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"/css/chartCss.css\">\n" +
                "  <script src=\"/js/jquery-2.2.0.min.js\"></script>\n" +
                "  <script src=\"/js/bootstrap.min.js\"></script>\n" +
                "  <link rel=\"stylesheet\" href=\"/css/flat-ui.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"/css/cus.css\">\n" +



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
                "      <div id=\"rootwizard\">\n" +
                "\n" +
                "        <div id=\"bar\" class=\"progress\">\n" +
                "          <div class=\"progress-bar progress-bar-striped active\"  aria-valuenow=\"0\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 100%;\"></div>\n" +
                "        </div>\n" +
                "\n" +
                "\n" +
                "            <div class=\"jumbotron text-center\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "              <h2 class=\"green\"><span class=\"fui-check\"></span> Installed!</h2>\n" +
                "\n" );

        InstallDB db = new InstallDB(out);



        try {
            db.installDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            //process only if its multipart content
            if(ServletFileUpload.isMultipartContent(request)){
                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    for(FileItem item : items){
                        /*System.out.println(item.getString()+" ----item");
                        System.out.println(item.getFieldName()+" --++--item");*/

                        String name = item.getFieldName();
                        switch (name) {
                            case "uname":
                                uname = item.getString();
                                break;
                            case "cname":
                                cname = item.getString();
                                break;
                            case "adminUsername":
                                ausername = item.getString();
                                break;
                            case "adminpassword":
                                apassword = item.getString();
                                break;
                            case "rePassword":
                                arepassword = item.getString();
                                break;
                            case "adminemail":
                                aemail = item.getString();
                                break;
                            case "adminFirstName":
                                afname = item.getString();
                                break;
                            case "adminMiddleName":
                                amname = item.getString();
                                break;
                            case "adminLastName":
                                alname = item.getString();
                                break;
                            default:
                                System.out.println("default fired!");
                        }

                        if(!item.isFormField()){

                            System.out.println("else fired!"+ item.getSize());
                            if(item.getSize() != 0) {
                                name = new File(item.getName()).getName();
                                if (item.getSize() < 2000000) {

                                    String extension = "";

                                    int i = item.getName().lastIndexOf('.');
                                    if (i > 0) {
                                        extension = item.getName().substring(i + 1);
                                        System.out.println("file ext !"+ extension);
                                    }
                                    if (extension.equals("png")) {
                                        item.write(new File(SERVER_DIRECTORY + UPLOAD_DIRECTORY + File.separator + name));
                                        ulogo = "/" + UPLOAD_DIRECTORY + "/" + name;
                                        //File uploaded successfully
                                        System.out.println("File Uploaded Successfully!");

                                    } else {
                                        out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                                "                    <strong id=\"alertt\" >university logo must be type of PNG</strong>\n" +
                                                "                </div>");
                                    }
                                } else {
                                    out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                            "                    <strong id=\"alertt\" >Logo image's size exceeds 2mb</strong>\n" +
                                            "                </div>");
                                }
                            }else{
                                ulogo = null;
                            }
                        }
                    }


                } catch (Exception ex) {
                    System.out.println("File Upload Failed due to " + ex);
                }


                db.init(uname, cname, ulogo, afname, amname, alname, ausername, Password.getSaltedHash(apassword), aemail);

                request.getServletContext().setAttribute("ulogo",ulogo);
                request.getServletContext().setAttribute("uname",uname);
                request.getServletContext().setAttribute("cname",cname);
                request.getServletContext().setAttribute("color","#043366");


                deleteFile(SERVER_DIRECTORY+"setup/install.jsp");

                out.println("<h4 class\"red\">setup/Install.jsp were deleted for security reasons.</h4>");

                out.print("<br>\n" +
                        "        <p>Congratulations, ABETAS setup done successfully, now you need to add a superuser so he can enter the programs, course, create an evaluation cycle. Click on User Management to add and manage users.</p>\n" +
                        "        <br>\n" +
                        "        <div>\n" +
                        "            \n" +
                        "            <a  href=\"users/\" class=\"btn btn-primary btn-lg\" >User Management</a>\n" +
                        "            <a href=\"index.jsp\" class=\"btn btn-primary btn-lg\" >Home Page</a>\n" +
                        "\n" +
                        "        </div>");
            }else{
                System.out.println("not multipart!");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("              </div>\n" +
                "            </div>\n" +
                "    </section>\n" +
                "  </div>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        //out.println("name: " + request.getParameter("name"));
        //out.println("logo: " + request.getParameter("logo"));
        //out.println("Insert");


        //response.sendRedirect("http://localhost:8080/");
        // New location to be redirected
        // String site = new String("http://localhost:8081/");
        // response.setHeader("Location", site);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/index.jsp");
    }

    public void deleteFile(String filepath) {
        File file = new File(filepath);
        file.delete();
    }
}

