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
        String cmdCall = request.getParameter("cmd");
        System.out.println(pageCall);
        if(pageCall != null){
            if(pageCall.equals("addTerm")) {
                String id = request.getParameter("cycle");
                System.out.println("cycle id is : "+id);
                if(id != null){
                    pageName = "addTerm.jsp?cycle="+id;
                }else {
                   System.out.println("dfsdfdsfdfdffsfsdvdvvfvfdvvfd"); //display error page
                }
            }else if(pageCall.equals("LinkPIOutList")) {

                String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                    pageName = "LinkPIOutList.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("rubricNames")) {

                String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null){
                    System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG  " + request.getParameter("cycle"));
                    pageName = "rubricNames.jsp?cycle="+request.getParameter("cycle")+"&programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addStudent")) {

                String courseCode=request.getParameter("courseCode");
                String courseName=request.getParameter("courseName");
                String programID=request.getParameter("programID");
                String programName=request.getParameter("programName");
                String section=request.getParameter("section");

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                    pageName = "addStudent.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&programID="+ programID+"&courseCode="+courseCode+"&courseName="+courseName+"&programName="+programName+"&section="+section;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("studentList")) {

                String courseCode=request.getParameter("courseCode");
                String courseName=request.getParameter("courseName");
                String programID=request.getParameter("programID");
                //String programName=request.getParameter("programName");
                String section=request.getParameter("section");

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    if(request.getParameter("status") != null){
                        System.out.println("ddddddddddddddddddddddddddd  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                        pageName = "studentList.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                                "&programID=" + programID+"&courseCode="+courseCode+"&courseName="+courseName+"&section="+section +
                        "status="+ request.getParameter("status");
                    }else {
                        System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQ  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                        pageName = "studentList.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                                "&programID=" + programID+"&courseCode="+courseCode+"&courseName="+courseName+"&section="+section;
                    }
                }else {
                    //display error page
                }
            }else if(pageCall.equals("includeCourse")) {

                String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null && request.getParameter("term") != null && programID!=null){
                    System.out.println("GGGGGGGGGGGGGGGGGGGGG  " + request.getParameter("cycle")+"     "+request.getParameter("term")+"        "+programID);
                    pageName = "includeCourse.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&programID=" + programID;
                }else {
                    pageName = "includeCourse.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term");
                }
            }else if(pageCall.equals("CourseInfo")) {
                String programID=request.getParameter("programID");
                String courseCode=request.getParameter("courseCode");

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    pageName = "CourseInfo.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&programID=" + programID+"&courseCode="+courseCode;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addPILinks")) {

                String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZ  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                    pageName = "addPILinks.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addPIOutLink")) {

                String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZ  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                    pageName = "addPILinks.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addPI")) {

                String programID=request.getParameter("programID");

                if(request.getParameter("cycle") != null){
                    System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY  " +programID);
                    pageName = "addPI.jsp?cycle="+request.getParameter("cycle")+"&programID="+programID;
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
                    pageName = "cycleList.jsp";
                }
            }else if(pageCall.equals("addRubrics")) {
                System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY  " + request.getParameter("cycle")+"     "+request.getParameter("term"));

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    System.out.println("fdsfdfsdfdfsdfsfdfdfsf");
                    pageName = "addRubrics.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term") ;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("piList")) {
                System.out.println("piList-----  " + request.getParameter("cycle"));

                if(request.getParameter("cycle") != null) {
                    if(request.getParameter("status") != null) {
                        System.out.println("fdsfdfsdfdfsdfsfdfdfsf");
                        pageName = "piList.jsp?cycle="+request.getParameter("cycle") ;
                    }else {
                        System.out.println("fdsfdfsdfdfsdfsfdfdfsf");
                        pageName = "piList.jsp?cycle="+request.getParameter("cycle") ;
                    }

                }else {
                    //display error page
                }
            }else if(pageCall.equals("import")){
                String id = request.getParameter("cycle");
                String name = request.getParameter("term");
                String programID = request.getParameter("programID");
                String courseCode = request.getParameter("courseCode");
                String courseName = request.getParameter("courseName");
                String section = request.getParameter("section");
                String data = request.getParameter("data");
                if(data != null){
                    if(request.getParameter("data").equals("students")) {
                        pageName = "import.jsp?cycle=" + id + "&term=" + name + "&programID" + programID + "&courseCode=" + courseCode +
                                "&courseName=" + courseName + "&section=" + section + "&data=students";
                    }else if(request.getParameter("data").equals("pis")){
                        pageName = "import.jsp?cycle=" + id + "&term=" + name + "&programID" + programID + "&courseCode=" + courseCode +
                                "&courseName=" + courseName + "&section=" + section + "&data=pis";
                    }

                }else {
                    //display error page
                }
            }else {
                pageName = "cycleList.jsp";
            }
        }else if(cmdCall !=null){
            if(cmdCall.equals("upload")){
                String id = request.getParameter("cycle");
                String name = request.getParameter("term");
                String programID = request.getParameter("programID");
                String courseCode = request.getParameter("courseCode");
                String courseName = request.getParameter("courseName");
                String section = request.getParameter("section");
                if(request.getParameter("err")!= null){
                    pageName = "import.jsp?cycle=" + id + "&term=" + name + "&programID" + programID + "&courseCode=" + courseCode +
                            "&courseName=" + courseName + "&section=" + section + "&err="+request.getParameter("err");
                    //pageName = "import.jsp?name="+name+"&id="+id+"&err="+request.getParameter("err");
                }else {
                    //pageName = "import.jsp?name="+name+"&id="+id+"&file="+request.getParameter("file");
                    pageName = "import.jsp?cycle=" + id + "&term=" + name + "&programID" + programID + "&courseCode=" + courseCode +
                            "&courseName=" + courseName + "&section=" + section + "&file="+request.getParameter("file");
                }

            }else if(cmdCall.equals("confirm")){
                String id = request.getParameter("cycle");
                String name = request.getParameter("term");
                String programID = request.getParameter("programID");
                String courseCode = request.getParameter("courseCode");
                String courseName = request.getParameter("courseName");
                String section = request.getParameter("section");
                String dataType = request.getParameter("data");
                /*pageName = "upload.jsp?cycle=" + id + "&term=" + name + "&programID" + programID + "&courseCode=" + courseCode +
                        "&courseName=" + courseName + "&section=" + section + "&data="+ dataType;*/
                if(request.getParameter("data").equals("students")) {
                    pageName = "upload.jsp?cycle=" + id + "&term=" + name + "&programID" + programID + "&courseCode=" + courseCode +
                            "&courseName=" + courseName + "&section=" + section + "&data="+dataType;
                }else if(request.getParameter("data").equals("pis")){
                    pageName = "upload.jsp?cycle=" + id + "&term=" + name + "&programID" + programID + "&data="+dataType;
                }
            }
        }else if(request.getParameter("back") != null){
            String backPage = request.getParameter("back");
            String id = request.getParameter("cycle");
            String name = request.getParameter("term");
            String programID = request.getParameter("programID");
            String courseCode = request.getParameter("courseCode");
            String courseName = request.getParameter("courseName");
            String section = request.getParameter("section");

            if(backPage.equals("students")){
                pageName = "studentList.jsp?cycle=" + id + "&term=" + name + "&programID" + programID + "&courseCode=" + courseCode +
                        "&courseName=" + courseName + "&section=" + section;
            }else if(backPage.equals("pis")){
                pageName = "piList.jsp?cycle=" + id + "&term=" + name + "&programID" + programID + "&courseCode=" + courseCode +
                        "&courseName=" + courseName + "&section=" + section;
            }else if(backPage.equals("courses")){
                pageName = "CoursesList.jsp?name=" + name + "&id=" + id;
            }

        }else {
            pageName = "cycleList.jsp";
        }
        //-----------------------------------------------POST
    }else if(request.getMethod().equals("POST")) {
        pageName = null;
        String pageCall = request.getParameter("page");
        System.out.println(pageCall);
        if(pageCall != null){
            if(pageCall.equals("update")){
                String id = request.getParameter("id");
                if(id != null){
                    pageName = "cycleView.jsp?id="+id;
                }else {
                    pageName = "cycleList.jsp";
                }
            }else if(pageCall.equals("addTerm")) {

                if(request.getParameter("cycle") != null){
                    pageName = "addTerm.jsp?cycle="+request.getParameter("cycle");
                }else {
                    //display error page
                }
            }/*else if(pageCall.equals("addTerm")) {

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    pageName = "piList.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term") ;
                }else {
                    //display error page
                }
            }*/else if(pageCall.equals("CourseInfo")) {
                String programID=request.getParameter("programID");
                String courseCode=request.getParameter("courseCode");

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    pageName = "CourseInfo.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&programID=" + programID+"&courseCode="+courseCode;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("LinkPIOutList")) {

                String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                    pageName = "LinkPIOutList.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&programID=" + programID;
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

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU  " + request.getParameter("cycle")+"     "+request.getParameter("term")+"   "+LinkID+"    "+OutValue+"   "+PIValue+"    "+CourseValue+"   "+TypeValue+"     "+PValue+"    "+RubricValue+"  "+TermValue+"   "+programID);
                    pageName = "addPILinks.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&LinkID="+LinkID+"&OutValue="+OutValue+"&PIValue="+PIValue+"&CourseValue=" + CourseValue+"&TypeValue="+TypeValue+"&PValue=" + PValue+"&RubricValue="+RubricValue+"&TermValue=" + TermValue+"&programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("piList")) {

                //String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null){
                    System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ  " + request.getParameter("cycle")+"     "+request.getParameter("cycle"));
                    pageName = "piList.jsp?cycle="+request.getParameter("cycle");//+"&programID=" + programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("studentList")) {

                String courseCode=request.getParameter("courseCode");
                String courseName=request.getParameter("courseName");
                String programID=request.getParameter("programID");
                //String programName=request.getParameter("programName");
                String section=request.getParameter("section");

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    if(request.getParameter("status") != null){
                        System.out.println("ddddddddddddddddddddddddddd  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                        pageName = "studentList.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                                "&programID=" + programID+"&courseCode="+courseCode+"&courseName="+courseName+"&section="+section +
                                "&status="+ request.getParameter("status");
                    }else {
                        System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQ  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                        pageName = "studentList.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                                "&programID=" + programID+"&courseCode="+courseCode+"&courseName="+courseName+"&section="+section;
                    }
                }else {
                    //display error page
                }
            }else if(pageCall.equals("updateStudent")) {

                String courseCode=request.getParameter("courseCode");
                String courseName=request.getParameter("courseName");
                String programID=request.getParameter("programID");
                String programName=request.getParameter("programName");
                String section=request.getParameter("section");
                String S_ID=request.getParameter("S_ID");
                String NValue=request.getParameter("NValue");
                String IDValue=request.getParameter("IDValue");

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG  " + request.getParameter("cycle")+"     "+request.getParameter("term"));
                    pageName = "addStudent.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                            "&programID="+ programID+"&courseCode="+courseCode+"&courseName="+courseName+"&programName="+programName+
                            "&section="+section+"&S_ID="+S_ID+"&NValue="+NValue+"&IDValue="+IDValue;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("includeCourse")) {

                String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null && request.getParameter("term") != null && programID!=null){
                    System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ  " + request.getSession().getAttribute("id")+"     "+request.getSession().getAttribute("Termid")+"        "+programID);
                    pageName = "includeCourse.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+"&programID=" + programID;
                }else {
                    pageName = "includeCourse.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term");
                }
            }else if(pageCall.equals("addRubrics")) {
                System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY  " + request.getParameter("cycle")+"     "+request.getParameter("term"));

                if(request.getParameter("cycle") != null && request.getParameter("term") != null){

                    System.out.println("fdsfdfsdfdfsdfsfdfdfsf");
                    pageName = "addRubrics.jsp";
                }else {
                    //display error page
                }
            }else if(pageCall.equals("updateCycle")) {
                System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY  ");
                String id=request.getParameter("cycle");

                if(request.getParameter("cycle") != null){
                    System.out.println("fdsfdfsdfdfsdfsfdfdfsf");
                    pageName = "addTerm.jsp?cycle="+id;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("updatePI")) {

                String PILabel=request.getParameter("PILabel");
                String PIValue=request.getParameter("PIValue");
                String programID=request.getParameter("programID");

                if(request.getParameter("cycle") != null){
                    System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY  " +PILabel +"    "+ PIValue+"     "+programID);
                    pageName = "addPI.jsp?cycle="+request.getParameter("cycle")+"&PILabel=" + PILabel + "&PIValue="+PIValue+"&programID="+programID;
                }else {
                    System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                    //display error page
                }
            }else if(pageCall.equals("addPI")) {

                String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null){
                    pageName = "addPI.jsp?cycle="+request.getParameter("cycle")+"&programID="+programID;
                }else {
                    //display error page
                }
            }else if(pageCall.equals("addSection")) {

                String programID=request.getParameter("programID");
                if(request.getParameter("cycle") != null && request.getParameter("term") != null){
                    pageName = "addSection.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                            "&programID="+programID+"&programName="+request.getParameter("programName")+"&courseCode="+
                            request.getParameter("courseCode")+"&courseName="+request.getParameter("courseName");
                }else {
                    //display error page
                }
            }else if(pageCall.equals("updateSection")) {


                if(request.getParameter("cycle") != null && request.getParameter("term") != null && request.getParameter("section") != null){
                    pageName = "addSection.jsp?cycle="+request.getParameter("cycle")+"&term="+request.getParameter("term")+
                            "&programID="+request.getParameter("programID")+"&programName="+request.getParameter("programName")+
                            "&courseCode="+ request.getParameter("courseCode")+"&courseName="+request.getParameter("courseName")+
                            "&section="+ request.getParameter("section");
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
    <%-- <link href="/css/ct-paper.css" rel="stylesheet"/>
     <link href="/css/demo.css" rel="stylesheet" /> --%>
    <link href="/css/users.css" rel="stylesheet" />
    <link href="/css/bootstrap-select.min.css" rel="stylesheet" />
    <link href="/css/cus.css" rel="stylesheet" />
    <link href="/css/flat-ui.css" rel="stylesheet" />




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
<%--<script src="/js/ct-paper-checkbox.js"></script>
<script src="/js/ct-paper-radio.js"></script>--%>
<script src="/js/bootstrap-select.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/jquery.bsFormAlerts.js"></script>
<script src="/js/del-form-listener.js"></script>
<script src="/js/import-popup.js"></script>
<script src="/js/pageloading.js"></script>
<%--<script src="/js/ct-paper.js"></script>--%>

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




</script>

</html>