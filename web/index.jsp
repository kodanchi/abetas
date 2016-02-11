<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/25253391/javascript-loading-screen-while-page-loads
--%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="sessionListener.CookiesControl" %>
<%@ page import="com.sun.corba.se.impl.orbutil.ObjectUtility" %>
<%@ page import="ASDB.AS_Select" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %>
<%@ page import="sessionListener.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>User Management</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="/css/bootstrap.css" rel="stylesheet" />
    <link href="/css/ct-paper.css" rel="stylesheet"/>
    <link href="/css/demo.css" rel="stylesheet" />
    <link href="/css/users.css" rel="stylesheet" />
    <link href="/css/cus.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>

</head>
<body>
<script src="/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="/js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<div id="page">
    <div id="header">
        <jsp:include page="/Header.jsp"/>
    </div>

    <div id="main" class="main">

        <div class="container">



  <section id="wizard">
      <div class="page-header">
        <h1>MAIN PAGE</h1>
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
    </div>

    <div id="footer">
        <jsp:include page="/Footer.jsp"/>
    </div>
</div>
<div id="loading" ></div>
</body>

<script src="/js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->
<script src="/js/ct-paper-checkbox.js"></script>
<script src="/js/ct-paper-radio.js"></script>
<script src="/js/bootstrap-select.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>

<script src="/js/ct-paper.js"></script>

<script>
    $.ajaxPrefilter(function( options, originalOptions, jqXHR ) { options.async = true; });
    /*$( "#slider-range" ).slider({
     range: true,
     min: 0,
     max: 500,
     values: [ 75, 300 ],
     });
     $( "#slider-default" ).slider({
     value: 70,
     orientation: "horizontal",
     range: "min",
     animate: true
     });
     $('.btn-tooltip').tooltip('show');
     $('.radio').on('toggle', function() { });


     $(function(){
     //$("#header").load("/header.jsp");
     //$("#main").load("userslist.jsp");
     //$("#footer").load("/footer.jsp");
     });


     function addUser(){
     $("#main").load("adduser.jsp");
     }

     function cancel(){
     $("#main").load("userslist.jsp");
     }

     */

    function onReady(callback) {
        var intervalID = window.setInterval(checkReady, 100);
        function checkReady() {
            if (document.getElementsByTagName('body')[0] !== undefined) {
                window.clearInterval(intervalID);
                callback.call(this);
            }
        }
    }

    function show(id, value) {
        document.getElementById(id).style.display = value ? 'block' : 'none';
    }

    onReady(function () {
        show('page', true);
        show('loading', false);
    });


</script>

</html>