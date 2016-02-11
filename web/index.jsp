<%@ page import="sessionListener.CookiesControl" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="sessionListener.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sessionListener.UserDAO" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %><%--
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

                <%--<%
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
                %>--%>


                <%
                    UserDAO dao = new UserDAO();
                    session = request.getSession(false);
                    if(session.getAttribute("username") != null){
                        String uname = (String)session.getAttribute("username");
                        Integer ulvl = (Integer)session.getAttribute("userLvl");
                        ArrayList<String> user = dao.find(uname,ulvl);
                        if(user != null){
                            out.println("ID : "+user.get(0));
                            out.println("Username : "+user.get(1));
                            out.println("Email : "+user.get(2));
                            out.println("lvl : "+user.get(3));
                        }else {
                            out.print("user not found");
                        }
                        /*out.println(session.getAttribute("username"));
                        out.println((String)session.getAttribute("userEmail"));

                        out.println(Integer.parseInt(String.valueOf(ulvl)));*/
                    }else {
                        out.print("no thing ");
                    }
                %>

                <a href="/logout" class="btn btn-success" >Logout</a>


              </div>
            </div>
    </section>
  </div>

</body>
</html>