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
    String pageName = "userslist.jsp";
    if(request.getMethod().equals("GET")){
        pageName = null;
        String pageCall = request.getParameter("page");
        String cmdCall = request.getParameter("cmd");
        String stutsCall = request.getParameter("status");
        System.out.println(pageCall);
        if(pageCall != null){
            if(pageCall.equals("add")) {
                pageName = "adduser.jsp";
            }else if(pageCall.equals("update")){
                String id = request.getParameter("id");
                String type = request.getParameter("type");
                if(id != null && type != null){
                    pageName = "adduser.jsp?id="+id+"&type="+type;
                }else {
                    pageName = "userslist.jsp";
                }
            }else if(pageCall.equals("import")){
                pageName = "import.jsp";
            }else {
                pageName = "userslist.jsp";
            }
        }else if(cmdCall != null){
            pageName = "userslist.jsp";
            if(cmdCall.equals("upload")){
                if(request.getParameter("err")!= null){
                    pageName = "upload.jsp?err="+request.getParameter("err");
                }else {
                    pageName = "upload.jsp?file="+request.getParameter("file");
                }

            }else if(cmdCall.equals("confirm")){
                pageName = "upload.jsp";
            }
        }else if(stutsCall != null){
            pageName = "userslist.jsp?status="+stutsCall;
        }else{
            pageName = "userslist.jsp";
        }
    }else if(request.getMethod().equals("POST")) {
        pageName = null;
        String pageCall = request.getParameter("page");
        String cmdCall = request.getParameter("cmd");
        System.out.println(pageCall);
        if(pageCall != null){
            if(pageCall.equals("add")) {
                if(request.getParameter("status")!= null){
                    pageName = "adduser.jsp?status="+request.getParameter("status");
                }else {
                    pageName = "adduser.jsp";
                }

            }else if(pageCall.equals("update")){
                String id = request.getParameter("id");
                String type = request.getParameter("type");
                if(id != null && type != null){
                    pageName = "adduser.jsp?id="+id+"&type="+type;
                }else {
                    pageName = "userslist.jsp";
                }
            }else {
                pageName = "userslist.jsp";
            }
        }else if(cmdCall != null){
            pageName = "userslist.jsp";
            if(cmdCall.equals("upload")){
                if(request.getParameter("err")!= null){
                    pageName = "import.jsp?err="+request.getParameter("err");
                }

            }else if(cmdCall.equals("confirm")){
                pageName = "upload.jsp";
            }
        }
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