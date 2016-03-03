<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/25253391/javascript-loading-screen-while-page-loads
--%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    System.out.println(request.getMethod());
    String pageName = "evaList.jsp";
    if(request.getMethod().equals("GET")){
        pageName = null;
        String pageCall = request.getParameter("page");
        //String cmdCall = request.getParameter("cmd");
        System.out.println(pageCall);
        if(pageCall != null){
             if(pageCall.equals("fillForm")) {

                if (request.getParameter("type").equals("formative")) {
                    pageName = "formative.jsp?Formative_ID=" + request.getParameter("id");

                }else if(request.getParameter("type").equals("summative")) {
                    pageName = "summative.jsp?Summative_ID=" + request.getParameter("id");
                }
            }else if(pageCall.equals("unlockForm")){
                 pageName = "unlockform.jsp";
            }else if(pageCall.equals("showForm")){
                 if (request.getParameter("type").equals("formative")) {
                     pageName = "formativeDisplay.jsp?Formative_ID=" + request.getParameter("id");

                 }else if(request.getParameter("type").equals("summative")) {
                     pageName = "summativeDisplay.jsp?Summative_ID=" + request.getParameter("id");
                 }
            }else if(pageCall.equals("showGraph")){
                 pageName = "displayGraph.jsp?id="+request.getParameter("id");
             }else {
                 pageName = "evaList.jsp";
             }
        }else {
            pageName = "evaList.jsp";
        }
        //-----------------------------------------------POST
    }
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>User Management</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="/css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/demo.css" rel="stylesheet" />
    <link href="/css/users.css" rel="stylesheet" />
    <link href="/css/cus.css" rel="stylesheet" />
    <link href="/css/chartCss.css" rel="stylesheet" />



</head>
<body>
<div id="page">
    <div id="header">
        <jsp:include page="/Header.jsp"/>
    </div>

    <div id="main" class="main">
        <jsp:include page="<%=pageName%>"/>
    </div>

    <!--   end modal  -->


    <div id="footer">
        <jsp:include page="/Footer.jsp"/>
    </div>
</div>
<div id="loading" ></div>
</body>

<script src="/js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->
<script src="/js/bootstrap-select.min.js"></script>
<script src="/js/jquery.bsFormAlerts.js"></script>

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
        var intervalID = window.setInterval(checkReady, 1);
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