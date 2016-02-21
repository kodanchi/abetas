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
    String pageName = "cycleList.jsp";
    if(request.getMethod().equals("GET")){
        pageName = null;
        String pageCall = request.getParameter("page");
        System.out.println(pageCall);
        if(pageCall != null){
            if(pageCall.equals("addTerm")) {
                String id = request.getParameter("id");
                if(id != null){
                    pageName = "addTerm.jsp";
                }else {
                   System.out.println("dfsdfdsfdfdffsfsdvdvvfvfdvvfd"); //display error page
                }
            }else if(pageCall.equals("Formative")) {
                String Formative_ID=request.getParameter("Formative_ID");
                String FacilityID=request.getParameter("FacilityID");
                    pageName = "Formative.jsp?Formative_ID=" + Formative_ID+"&FacilityID="+FacilityID;
            }else if(pageCall.equals("LinkPIOutList")) {

                String programID=request.getParameter("programID");
                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid"));
                    pageName = "LinkPIOutList.jsp?programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addStudent")) {

                String courseCode=request.getParameter("courseCode");
                String courseName=request.getParameter("courseName");
                String programID=request.getParameter("programID");
                String programName=request.getParameter("programName");
                String F_ID=request.getParameter("F_ID");

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid")+ "F     "+F_ID);
                    pageName = "addStudent.jsp?programID=" + programID+"&courseCode="+courseCode+"&courseName="+courseName+"&programName="+programName+"&F_ID="+F_ID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("studentList")) {

                String courseCode=request.getParameter("courseCode");
                String courseName=request.getParameter("courseName");
                String programID=request.getParameter("programID");
                String programName=request.getParameter("programName");
                String F_ID=request.getParameter("F_ID");

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQq  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid")+"    "+courseCode+"      "+courseName+"     "+programID+"     "+programName+"   "+F_ID);
                    pageName = "studentList.jsp?programID=" + programID+"&courseCode="+courseCode+"&courseName="+courseName+"&programName="+programName+"&F_ID="+F_ID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("includeCourse")) {

                String programID=request.getParameter("programID");

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid"));
                    pageName = "includeCourse.jsp?programID="+programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addPILinks")) {

                String programID=request.getParameter("programID");
                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid"));
                    pageName = "addPILinks.jsp?programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("piList")) {

                String programID=request.getParameter("programID");
                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid"));
                    pageName = "piList.jsp?programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addPI")) {

                String programID=request.getParameter("programID");

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY  " +programID);
                    pageName = "addPI.jsp?programID="+programID;
                }else {
                    System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                    //display error page
                }
            }else if(pageCall.equals("update")){
                String id = request.getParameter("id");
                String type = request.getParameter("type");
                if(id != null && type != null){
                    pageName = "adduser.jsp?id="+id+"&type="+type;
                }else {
                    //display error page
                }
            }
        }else {
            pageName = "cycleList.jsp";
        }
    }else if(request.getMethod().equals("POST")) {
        pageName = null;
        String pageCall = request.getParameter("page");
        System.out.println(pageCall);
        if(pageCall != null){
            if(pageCall.equals("add")) {
                pageName = "adduser.jsp";
            }else if(pageCall.equals("update")){
                String id = request.getParameter("id");
                if(id != null){
                    pageName = "cycleView.jsp?id="+id;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addTerm")) {

                if(request.getSession().getAttribute("id") != null){
                    pageName = "addTerm.jsp";
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addTerm")) {

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    pageName = "piList.jsp";
                }else {
                    //display error page
                }
            }else if(pageCall.equals("CourseInfo")) {
                String programID=request.getParameter("programID");
                String CourseValue=request.getParameter("CourseValue");

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    pageName = "CourseInfo.jsp?programID=" + programID+"&CourseValue="+CourseValue;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("LinkPIOutList")) {

                String programID=request.getParameter("programID");
                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid"));
                    pageName = "LinkPIOutList.jsp?programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("updatePILink")) {

                String LinkID=request.getParameter("LinkID");
                String OutValue=request.getParameter("OutValue");
                String PIValue=request.getParameter("PIValue");
                String CourseValue=request.getParameter("CourseValue");
                String TypeValue=request.getParameter("TypeValue");
                String PValue=request.getParameter("PValue");
                String RubricValue=request.getParameter("RubricValue");
                String TermValue=request.getParameter("TermValue");
                String programID=request.getParameter("programID");

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid")+"   "+LinkID+"    "+OutValue+"   "+PIValue+"    "+CourseValue+"   "+TypeValue+"     "+PValue+"    "+RubricValue+"  "+TermValue+"   "+programID);
                    pageName = "addPILinks.jsp?LinkID="+LinkID+"&OutValue="+OutValue+"&PIValue="+PIValue+"&CourseValue=" + CourseValue+"&TypeValue="+TypeValue+"&PValue=" + PValue+"&RubricValue="+RubricValue+"&TermValue=" + TermValue+"&programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("piList")) {

                String programID=request.getParameter("programID");
                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid"));
                    pageName = "piList.jsp?programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("studentList")) {

                String courseCode=request.getParameter("courseCode");
                String courseName=request.getParameter("courseName");
                String programID=request.getParameter("programID");
                String programName=request.getParameter("programName");
                String F_ID=request.getParameter("F_ID");

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQq  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid")+"    "+courseCode+"      "+courseName+"     "+programID+"     "+programName+"   "+F_ID);
                    pageName = "studentList.jsp?programID=" + programID+"&courseCode="+courseCode+"&courseName="+courseName+"&programName="+programName+"&F_ID="+F_ID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("includeCourse")) {

                String programID=request.getParameter("programID");
                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null && programID!=null){
                    System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid")+"        "+programID);
                    pageName = "includeCourse.jsp?programID=" + programID;
                }else {
                    pageName = "includeCourse.jsp";
                }
            }else if(pageCall.equals("addRubrics")) {
                System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid"));

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("fdsfdfsdfdfsdfsfdfdfsf");
                    pageName = "addRubrics.jsp";
                }else {
                    //display error page
                }
            }else if(pageCall.equals("updateCycle")) {
                System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY  ");
                String id=request.getParameter("id");

                if(request.getParameter("id") != null){
                    System.out.println("fdsfdfsdfdfsdfsfdfdfsf");
                    pageName = "addTerm.jsp?id="+id;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("updatePI")) {

                String PILabel=request.getParameter("PILabel");
                String PIValue=request.getParameter("PIValue");
                String programID=request.getParameter("programID");

                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY  " +PILabel +"    "+ PIValue+"     "+programID);
                    pageName = "addPI.jsp?PILabel=" + PILabel + "&PIValue="+PIValue+"&programID="+programID;
                }else {
                    System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                    //display error page
                }
            }else if(pageCall.equals("addPI")) {

                String programID=request.getParameter("programID");
                if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
                    pageName = "addPI.jsp?programID="+programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("cycleList")) {
                    pageName = "cycleList.jsp";
            }else {
                //display error page
            }
        }else {
            pageName = "cycleList.jsp";
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