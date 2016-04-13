
package ASDB;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@WebServlet(name = "AddUser",
        urlPatterns = {"/addUser"})
public class AddUser extends HttpServlet {
    String[] userVal;

    /**
     * insert users , then redirect to the users list.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        U_AS_Insert idb = new U_AS_Insert();
        U_AS_Select sdb = new U_AS_Select();
        try {
            String userType = request.getParameter("userType");
            String uname = request.getParameter("uname");
            String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String uemail = (request.getParameter("uemail") != null ? request.getParameter("uemail").toLowerCase() : "");
            String program = request.getParameter("evaluatorProg");
            userVal = new String[]{userType, fname, mname, lname, uname, uemail};

            if(uname.equals("")){
                sendErrMsg("username Required!",request,response);
            }else {
                if(sdb.selectUserIfExist(uname)){
                    sendErrMsg("Username Exist",request,response);
                }else {
                    if(userType.equals("Superuser")||userType.equals("Faculty_Member")) {
                        if(!checkEmailValidation(request.getParameter("uemail"))){
                            sendErrMsg("The Email is not in the proper format",request,response);
                        }else if(sdb.selectEmailIfExist(request.getParameter("uemail"))){
                            sendErrMsg("The Email is already exist",request,response);
                        }else{
                            if(userType.equals("Superuser")) {
                                idb.addUser(0, uname, uemail, capitalize(fname), capitalize(mname), capitalize(lname), null);
                                Auditor.add((String) request.getSession().getAttribute("username"),
                                        "added new superuser :" + uname);
                            }else if (request.getParameter("userType").equals("Faculty_Member")) {
                                idb.addUser(1, uname, uemail, capitalize(fname), capitalize(mname), capitalize(lname), null);
                                Auditor.add((String) request.getSession().getAttribute("username"),
                                        "added new Faculty Member :" + uname);
                            }
                            sendMsg(userType+" : "+capitalize(fname)+" has been added Successfully",request);
                            response.sendRedirect("/users/index.jsp");

                        }
                    }else if (request.getParameter("userType").equals("Evaluator")){
                        idb.addUser(2, uname, null, capitalize(fname), capitalize(mname), capitalize(lname), program);
                        Auditor.add((String) request.getSession().getAttribute("username"),
                                "added new Evaluator :" + uname);
                        sendMsg(userType+" : "+capitalize(fname)+" has been added Successfully",request);
                        response.sendRedirect("/users/index.jsp");
                    }
                }
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * show message
     * @param msg String
     * @param request HttpServletRequest
     */
    protected void sendMsg(String msg, HttpServletRequest request) {

        if (request.getSession().getAttribute("Msg") == null)
            request.getSession().setAttribute("Msg", msg);

    }

    /**
     * show error message
     * @param msg String
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void sendErrMsg(String msg,HttpServletRequest request, HttpServletResponse response){



            request.getSession().setAttribute("errMsg",msg);
            request.getSession().setAttribute("userValue",userVal);


        try {
            request.getRequestDispatcher("/users/index.jsp?page=add").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return;
    }

    /**
     * checking the validation by Email
     * @param email String
     * @return true or false
     */
    protected boolean checkEmailValidation(String email) {
        boolean result = true;
        try {
            InternetAddress emailChecker = new InternetAddress(email);
            emailChecker.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    /**
     * capitalize the first letter of the word
     * @param string String
     * @return capitalize string
     */
    public String capitalize(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') {
                found = false;
            }
        }
        return String.valueOf(chars);
    }

}

