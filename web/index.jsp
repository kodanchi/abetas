<%@ page import="sessionListener.CookiesControl" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="sessionListener.User" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/19/2016
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>ABETAS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>






<div class="container">



  <section id="wizard">
      <div class="page-header">
        <h1>ABETAS SETUP</h1>
      </div>

      <div >


            <div class="jumbotron text-center">

                <%
                    HttpSession l_session = null;
                    String l_persistentCookieId = CookiesControl.getCookieValue(request, "MY_SESSION_COOKIE");


                    try {

                        if (l_persistentCookieId != null)
                        {
                            HashMap<String, HttpSession> l_activeUsers = (HashMap<String, HttpSession>) request.getServletContext().getAttribute("activeUsers");
                            // Get the existing session
                            l_session = l_activeUsers.get(l_persistentCookieId);
                        }
                        // Otherwise a session has not been created
                        if (l_session == null)
                        {
                            response.sendRedirect("/login/login.jsp");
                        }

                        User user = (User) l_session.getAttribute("user");
                        if(user != null){
                            out.println("Username : "+user.getUsername());
                            out.println("Email : "+user.getEmail());
                        }else {
                            out.print("no thing ");
                        }



                    }catch (NullPointerException e){
                        e.fillInStackTrace();
                        System.out.println(e);
                    }
                %>




              </div>
            </div>
    </section>
  </div>

</body>
</html>