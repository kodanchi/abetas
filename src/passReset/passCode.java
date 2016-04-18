package passReset;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "passcode",
        urlPatterns = {"/Passcode"})
public class passCode extends HttpServlet {
    /**
     *
     @param request used to get the servler request from the uri which has the user data
      * @param response used to respone to the servlet site either by redirect the site to another url or show some output to the user
     * @throws ServletException used to handle if the error occurs from uri (web server)
     * @throws IOException used to handle any inpout/output operation in dealing with windows operations
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * getFromUser2 and getFromUser3 will take the email of the user and the passcode that he/she received
         * after that it will check in checkPasscode if the passcode matched or not
         */
        String uemail=request.getParameter("email");
        String upasscode= request.getParameter("code");


        try {
            checkPassCode(uemail,upasscode,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param email  will take the email to check if any request occurs before for reset password
     * @param passcode will take the passcode that the user entered
     * @param response used to response to the servlet site either by redirect the site to another url or show some output to the user
     * @throws ClassNotFoundException
     * @throws  IOException used to handle any inpout/output operation in dealing with windows operations
     */
    public void checkPassCode(String email, String passcode, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {

        PrintWriter out = response.getWriter();

        /**
         * It will check whether the email exist in the hash map or not
         * after that it will compare the passcode that the user entered with the one which is in the hash map
         * if both matches it will redirect the user to jsp page that request to enter the new password
         * otherwise it will alert the user with warning message
         */
        if (PassCodeMap.checkKey(email)) {


                if (Integer.parseInt(passcode) == Integer.parseInt(PassCodeMap.getpassKey(email))) {

                    out.println("<script src=\"/js/jquery.bsFormAlerts.js\"></script>" +
                            "<form >\n" +
                            "                                    <div class=\"form-group\">\n" +
                            "                                        <input data-toggle=\"tooltip\" title=\"Enter the new Password\" id=\"newPassword\" name=\"newPassword\"\n" +
                            "                                               type=\"password\" class=\"form-control\" placeholder=\"New Passoword\" required autofocus/></div>\n" +
                            "                                    <div class=\"form-group\">\n" +
                            "                                        <input data-toggle=\"tooltip\" title=\"Re-Enter the new Password\" id=\"newRePassword\" name=\"newPassword\"\n" +
                            "                                               type=\"password\" class=\"form-control\" placeholder=\"New Passoword\" required autofocus/>\n" +
                            "                                        <span class=\"small\"  data-alertid=\"passwordMsg\" id=\"passwordMsg\"></span >\n" +
                            "                                    </div>\n" +
                            "                                    <button type=\"button\" class=\"btn btn-primary btn-block\" onclick=\"onEnterPasscode()\">Reset Password</button>\n" +
                            "                                    <button type=\"button\" class=\"btn btn-default btn-block\" onclick=\"new function(){\n" +
                            "                                                                        $('#loginDiv').show();\n" +
                            "                                                                        $('#passResetDiv').hide();\n" +
                            "                                                                    }\">Cancel</button>\n" +
                            "                                </form>\n" +
                            "                                <script>\n" +
                            "                                    function onEnterPasscode(){\n" +
                            "                                        var newPassword = document.getElementById(\"newPassword\");\n" +
                            "                                        var newRePassword = document.getElementById(\"newRePassword\");\n" +
                            "                                        $(document).trigger(\"clear-alert-id.passwordMsg\");\n" +
                            "                                        if(newPassword.value == \"\" || newRePassword.value == \"\"){\n" +
                            "                                            $(document).trigger(\"set-alert-id-passwordMsg\", [\n" +
                            "                                                {\n" +
                            "                                                    message: \"Please enter the new password twice\",\n" +
                            "                                                    priority: \"error\"\n" +
                            "                                                }\n" +
                            "                                            ]);\n" +
                            "                                            newPassword.focus();\n" +
                            "                                        }else if(newPassword.value != newRePassword.value){\n" +
                            "                                            $(document).trigger(\"set-alert-id-passwordMsg\", [\n" +
                            "                                                {\n" +
                            "                                                    message: \"Password in the two fields doesn't match!\",\n" +
                            "                                                    priority: \"error\"\n" +
                            "                                                }\n" +
                            "                                            ]);\n" +
                            "                                            newPassword.focus();\n" +
                            "                                        }else {\n" +
                            "                                            show('page', false);\n" +
                            "                                            show('loading', true);\n" +
                            "                                            $.ajax({\n" +
                            "                                                type: 'POST',\n" +
                            "                                                data: {\n" +
                            "                                                    email: '"+email+"',\n" +
                            "                                                    pw: newPassword.value\n" +
                            "                                                },\n" +
                            "                                                url: '/passReset',\n" +
                            "                                                success: function (result) {\n" +
                            "                                                    $('#prinfo').html(result);\n" +
                            "                                                    show('page', true);\n" +
                            "                                                    show('loading', false);\n" +
                            "\n" +
                            "                                                }\n" +
                            "\n" +
                            "                                            })\n" +
                            "                                        }\n" +
                            "                                    }\n" +
                            "                                $('#passResetDiv').show();\n" +
                            "                                $('#passcodeDiv').hide();\n" +
                            "                                </script>");
                }
                else {
                    out.print("<script>\n" +
                            "                                    $(document).trigger(\"clear-alert-id.passMsg\");\n" +
                            "                                    $(document).trigger(\"set-alert-id-passMsg\", [\n" +
                            "                                        {\n" +
                            "                                            message: \"Wrong Passcode!\",\n" +
                            "                                            priority: \"info\"\n" +
                            "                                        }\n" +
                            "                                    ]);\n" +
                            "                                </script>");


                }


    }
        else{
            out.print("<script language=\"javaScript\">\n" +


                    "  alert('Wrong email or passcode');\n" +

                    "</script>\n");

        }

    }

}
