package passRestore;

import ASDB.U_AS_Select;
import passReset.PassCodeMap;
import passReset.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;


@WebServlet(name = "resetPasswordServlet",
        urlPatterns = {"/Reset"})
public class resetPasswordServlet extends HttpServlet {

    /**
     *
     *
     * @param request used to get the servler request from the uri which has the user data
     * @param response used to respone to the servlet site either by redirect the site to another url or show some output to the user
     * @throws ServletException used to handle if the error occurs from uri (web server)
     * @throws IOException used to handle any inpout/output operation in dealing with windows operations
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        /**
         * String userEmail will Get the email that the user entered to reset the password
         */
        String userEmail = request.getParameter("email");


        String respondForm = "<script src=\"/js/jquery.bsFormAlerts.js\"></script>" +
                "<form >\n" +
                "                                        <div class=\"form-group\">\n" +
                "                                            <input data-toggle=\"tooltip\" title=\"Enter the Passcode\" id=\"passcode\" name=\"userPasscode\"\n" +
                "                                                   type=\"text\" onkeypress='return event.charCode >= 48 && event.charCode <= 57'\n" +
                "                                                   class=\"form-control\" placeholder=\"Passcode\" required autofocus/>\n" +
                "                                            <span class=\"small\"  data-alertid=\"passMsg\" id=\"passMsg\"></span >\n" +
                "                                        </div>\n" +
                "                                        <button type=\"button\" class=\"btn btn-primary btn-block\" onclick=\"onEnterPasscode()\">Reset Password</button>\n" +
                "                                        <button type=\"button\" class=\"btn btn-default btn-block\" onclick=\"new function(){\n" +
                "                                                                $('#loginDiv').show();\n" +
                "                                                                $('#passcodeDiv').hide();\n" +
                "                                                            }\">Cancel</button>\n" +
                "                                    </form>\n" +
                "                                    <script>\n" +
                "                                        function onEnterPasscode(){\n" +
                "                                            var passcode = document.getElementById(\"passcode\");\n" +
                "                                            $(document).trigger(\"clear-alert-id.passMsg\");\n" +
                "                                            if(passcode.value == \"\"){\n" +
                "                                                $(document).trigger(\"set-alert-id-passMsg\", [\n" +
                "                                                    {\n" +
                "                                                        message: \"Please enter the passcode\",\n" +
                "                                                        priority: \"error\"\n" +
                "                                                    }\n" +
                "                                                ]);\n" +
                "                                                passcode.focus();\n" +
                "                                            }else {\n" +
                "                                                show('page', false);\n" +
                "                                                show('loading', true);\n" +
                "                                                $.ajax({\n" +
                "                                                    type: 'POST',\n" +
                "                                                    data: {\n" +
                "                                                        email: '"+ userEmail +"',\n" +
                "                                                        code: passcode.value\n" +
                "                                                    },\n" +
                "                                                    url: '/Passcode',\n" +
                "                                                    success: function (result) {\n" +
                "                                                        $('#passResetDiv').html(result);\n" +
                "                                                        show('page', true);\n" +
                "                                                        show('loading', false);\n" +
                "\n" +
                "                                                    }\n" +
                "\n" +
                "                                                })\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    </script>";


        PrintWriter out=response.getWriter();
        try {
            U_AS_Select dbs = new U_AS_Select();
            /**
             * if the user has iniate the reset password operation correctly then send the passcode to his/her email
             * and then redirect him/her to enterpasscode jsp page to enter the code that he/she recieved
             * through the email
             * otherwise message will appear to the user to tell him/her the email doesn't exist
             */
            if(dbs.selectEmailIfExist(userEmail)){

                if(!PassCodeMap.checkKey(userEmail) && !userEmail.equals("")){
                    Random rand = new Random();
                    String randNumber = String.valueOf(rand.nextInt(100000));
                    System.out.println("This is the random number "+randNumber);
                    PassCodeMap.setPassCode(userEmail,randNumber);



                    SendEmail msg = new SendEmail();

                    msg.sendMsg("The code is  "+ PassCodeMap.getpassKey(userEmail)+"\n This passcode is valid for 24 hours only\n ABETAS Team","Reset password request",userEmail);

                    out.println(respondForm);



                }else if(PassCodeMap.checkKey(userEmail)){

                    out.println(respondForm);

                }else{
                    out.print("<script>\n" +
                            "                                    $(document).trigger(\"clear-alert-id.emailMsg\");\n" +
                            "                                    $(document).trigger(\"set-alert-id-emailMsg\", [\n" +
                            "                                        {\n" +
                            "                                            message: \"Email Does not exist!, Try more time!\",\n" +
                            "                                            priority: \"info\"\n" +
                            "                                        }\n" +
                            "                                    ]);\n" +
                            "                                </script>");
                }



            }
            else{

                out.print("<script>\n" +
                        "                                    $(document).trigger(\"clear-alert-id.emailMsg\");\n" +
                        "                                    $(document).trigger(\"set-alert-id-emailMsg\", [\n" +
                        "                                        {\n" +
                        "                                            message: \"Email Does not exist!, Try more time!\",\n" +
                        "                                            priority: \"info\"\n" +
                        "                                        }\n" +
                        "                                    ]);\n" +
                        "                                </script>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            out.print("false");

        }
    }
}
