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
    String pageName = "programList.jsp";
    if(request.getMethod().equals("GET")){
        pageName = null;
        String pageCall = request.getParameter("page");
        System.out.println(pageCall);
        if(pageCall != null){
            if(pageCall.equals("add")) {
                pageName = "addProgram.jsp";
            }else if(pageCall.equals("addObj")){
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if(id != null){
                    pageName = "Add_Program_Objective.jsp?name="+name+"&id="+id;
                }else {
                    //display error page
                }
            }else if (pageCall.equals("CoursesList")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                System.out.println(name+"   "+id);
                if (id != null) {
                    System.out.println(name+"   "+id);
                    pageName = "CoursesList.jsp?name=" + name + "&id=" + id;
                } else {
                    //display error page
                }
            }else if(pageCall.equals("addCourses")){
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if(id != null){
                    pageName = "addCourses.jsp?name="+name+"&id="+id;
                }else {
                    //display error page
                }
            }else if (pageCall.equals("LinkOutObj")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if (id != null) {
                    pageName = "linkSOWithO.jsp?name=" + name + "&id=" + id;
                } else {
                    //display error page
                }
            }else if (pageCall.equals("OutcomeList")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if (id != null) {
                    pageName = "SOList.jsp?name=" + name + "&id=" + id;
                } else {
                    //display error page
                }
            }else if(pageCall.equals("addOut")){
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                System.out.println("ID   index    " + id + "   name " + name);
                if(id != null){
                    pageName = "AddStudentOut.jsp?name="+name+"&id="+id;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addLinkO")){
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                System.out.println("ID   index    " + id + "   name " + name);
                if(id != null){
                    pageName = "AddOutObjLink.jsp?name="+name+"&id="+id;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("update")){
                String id = request.getParameter("id");
                String type = request.getParameter("type");
                if(id != null && type != null){
                    pageName = "addProgram.jsp?id="+id+"&type="+type;
                }else {
                    pageName = "programList.jsp";
                }
            }else {
                pageName = "programList.jsp";
            }
        }else {
            pageName = "programList.jsp";
        }
    }else if(request.getMethod().equals("POST")) {
        pageName = null;
        String pageCall = request.getParameter("page");
        System.out.println(pageCall);
        if(pageCall != null) {
            if (pageCall.equals("ObjList")) {
                String name = request.getParameter("name");
                String id = request.getParameter("id");
                System.out.println(name);
                if (name != null && id != null) {
                    pageName = "programObjectiveList.jsp?name=" + name + "&id=" + id;
                }
            } else if (pageCall.equals("OutcomeList")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if (id != null) {
                    pageName = "SOList.jsp?name=" + name + "&id=" + id;
                } else {
                    //display error page
                }
            } else if (pageCall.equals("CoursesList")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if (id != null) {
                    pageName = "CoursesList.jsp?name=" + name + "&id=" + id;
                } else {
                    //display error page
                }
            }else if (pageCall.equals("LinkOutObj")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if (id != null) {
                    pageName = "linkSOWithO.jsp?name=" + name + "&id=" + id;
                } else {
                    //display error page
                }
            } else if (pageCall.equals("update")) {
                String id = request.getParameter("id");
                if (id != null) {
                    pageName = "programView.jsp?id=" + id;
                } else {
                    pageName = "programList.jsp";
                }
            } else if (pageCall.equals("updateObj")) {
                String name = request.getParameter("name");
                String id = request.getParameter("id");
                String objid = request.getParameter("Objid");
                String objValue = request.getParameter("ObjValue");
                if (id != null) {
                    System.out.println(id+"      "+name+"       "+objid+"        "+objValue);
                    pageName = "Add_Program_Objective.jsp?id=" + id+"&Objid="+ objid+"&ObjValue="+objValue+"&name="+name;
                } else {
                    pageName = "Add_Program_Objective.jsp";
                }
            }else if (pageCall.equals("programObjectiveList")) {
                pageName = "programObjectiveList.jsp";
            }else {
                pageName = "programList.jsp";
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
        var intervalID = window.setInterval(checkReady, 10);
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