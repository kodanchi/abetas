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
    String cmdCall = request.getParameter("cmd");
    String stutsCall = request.getParameter("status");
    if(request.getMethod().equals("GET")){
        //pageName = null;
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
                if (stutsCall != null){
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    if(stutsCall.equals("success")){
                        if(page.equals("obj")){
                            pageName = "CoursesList.jsp?name=" + name + "&id=" + id +"&status="+stutsCall;
                        }else if (page.equals("outcomes")){

                        }
                    }
                }else {
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    System.out.println(name + "   " + id);
                    if (id != null) {
                        System.out.println(name + "   " + id);
                        pageName = "CoursesList.jsp?name=" + name + "&id=" + id;
                    } else {
                        //display error page
                    }
                }
            }else if (pageCall.equals("ObjList")) {
                if (stutsCall != null){
                    //String page = request.getParameter("page");
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    if(stutsCall.equals("success")){
                        if(page.equals("obj")){
                            pageName = "programObjectiveList.jsp?name=" + name + "&id=" + id +"&status="+stutsCall;
                        }else if (page.equals("outcomes")){

                        }
                    }
                }else {
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    System.out.println(name + "   " + id);
                    if (id != null) {
                        System.out.println(name + "   " + id);
                        pageName = "programObjectiveList.jsp?name=" + name + "&id=" + id;
                    } else {
                        //display error page
                    }
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
                    if(request.getParameter("status")!= null){
                        pageName = "AddOutObjLink.jsp?name=" + name + "&id=" + id;
                    }else {
                        pageName = "linkSOWithO.jsp?name=" + name + "&id=" + id;
                    }
                } else {
                    //display error page
                }
            }else if (pageCall.equals("OutcomeList")) {
                if (stutsCall != null){
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    if(stutsCall.equals("success")){
                        if(page.equals("obj")){
                            pageName = "SOList.jsp?name=" + name + "&id=" + id +"&status="+stutsCall;
                        }else if (page.equals("outcomes")){

                        }
                    }
                }else {
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    if (id != null) {
                        pageName = "SOList.jsp?name=" + name + "&id=" + id;
                    } else {
                        //display error page
                    }
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
                String ProgramName = request.getParameter("pname");
                if(id != null){
                    pageName = "addProgram.jsp?id="+id+"&ProgramName="+ProgramName;
                }else {
                    //display error page
                }
            }else if (pageCall.equals("updateProgram")) {
                String id = request.getParameter("id");
                String ProgramName = request.getParameter("ProgramName");
                String ProgramMission = request.getParameter("ProgramMission");
                if (id != null) {
                    pageName = "addProgram.jsp?id=" + id+"&ProgramName="+ ProgramName+"&ProgramMission="+ProgramMission;
                } else {
                    //display error page
                }
            }else if(pageCall.equals("import")){
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String data = request.getParameter("data");
                if(data != null){
                    if(request.getParameter("data").equals("obj")){
                        pageName = "import.jsp?name="+name+"&id="+id+"&data=obj";
                    }else if(request.getParameter("data").equals("outcomes")){
                        pageName = "import.jsp?name="+name+"&id="+id+"&data=outcomes";
                    }else if(request.getParameter("data").equals("courses")){
                        pageName = "import.jsp?name="+name+"&id="+id+"&data=courses";
                    } else {

                    }

                }else {
                    //display error page
                }
            }else if (pageCall.equals("updateLink")) {
                String name = request.getParameter("name");
                String id = request.getParameter("id");
                String Linkid = request.getParameter("Linkid");
                String ObjLinkValue = request.getParameter("ObjLinkValue");
                String OutLinkValue = request.getParameter("OutLinkValue");
                if (id != null) {
                    System.out.println(id+"      "+name+"       "+Linkid+"        "+ObjLinkValue+"        "+OutLinkValue);
                    pageName = "AddOutObjLink.jsp?id=" + id+"&Linkid="+ Linkid+"&ObjLinkValue="+ObjLinkValue+"&OutLinkValue="+OutLinkValue+"&name="+name;
                } else {
                    //display error page
                }
            }else if (pageCall.equals("updateCourses")) {
                String name = request.getParameter("name");
                String id = request.getParameter("id");
                String Courseid = request.getParameter("Courseid");
                String CourseName = request.getParameter("CourseName");
                String CourseLevel = request.getParameter("CourseLevel");
                String Cid = request.getParameter("Cid");

                if (id != null) {
                    System.out.println(id+"      "+name+"       "+Courseid+"        "+CourseName+"        "+CourseLevel);
                    pageName = "addCourses.jsp?id=" + id+"&Courseid="+ Courseid+"&CourseName="+CourseName+"&CourseLevel="+CourseLevel+"&name="+name+"&Cid="+Cid;
                } else {
                    //display error page
                }
            }else {
                pageName = "programList.jsp";
            }
        }else if(cmdCall !=null){
            if(cmdCall.equals("upload")){
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if(request.getParameter("err")!= null){
                    pageName = "import.jsp?name="+name+"&id="+id+"&err="+request.getParameter("err");
                }else {
                    pageName = "import.jsp?name="+name+"&id="+id+"&file="+request.getParameter("file");
                }

            }else if(cmdCall.equals("confirm")){
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String dataType = request.getParameter("data");
                pageName = "upload.jsp?name="+name+"&id="+id+"&data="+dataType;
            }
        }else if(request.getParameter("back") != null){
            String backPage = request.getParameter("back");
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            if(backPage.equals("obj")){
                pageName = "programObjectiveList.jsp?name=" + name + "&id=" + id;
            }else if(backPage.equals("outcomes")){
                pageName = "SOList.jsp?name=" + name + "&id=" + id;
            }else if(backPage.equals("courses")){
                pageName = "CoursesList.jsp?name=" + name + "&id=" + id;
            }

        }
        //------------------------------------------------POST
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
            } else if (pageCall.equals("LinkOutObj")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if (id != null) {
                    pageName = "linkSOWithO.jsp?name=" + name + "&id=" + id;
                } else {
                    //display error page
                }
            } else if (pageCall.equals("updateProgram")) {
                String id = request.getParameter("id");
                String ProgramName = request.getParameter("ProgramName");
                String ProgramMission = request.getParameter("ProgramMission");
                if (id != null) {
                    pageName = "addProgram.jsp?id=" + id+"&ProgramName="+ ProgramName+"&ProgramMission="+ProgramMission;
                } else {
                    //display error page
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
                    //display error page
                }
            }else if (pageCall.equals("updateOut")) {
                String name = request.getParameter("name");
                String id = request.getParameter("id");
                String outid = request.getParameter("Outid");
                String outValue = request.getParameter("OutValue");
                if (id != null) {
                    System.out.println(id+"      "+name+"       "+outid+"        "+outValue);
                    pageName = "AddStudentOut.jsp?id=" + id+"&Outid="+ outid+"&OutValue="+outValue+"&name="+name;
                } else {
                    //display error page
                }
            }else if (pageCall.equals("updateLink")) {
                String name = request.getParameter("name");
                String id = request.getParameter("id");
                String Linkid = request.getParameter("Linkid");
                String ObjLinkValue = request.getParameter("ObjLinkValue");
                String OutLinkValue = request.getParameter("OutLinkValue");
                if (id != null) {
                    System.out.println(id+"      "+name+"       "+Linkid+"        "+ObjLinkValue+"        "+OutLinkValue);
                    pageName = "AddOutObjLink.jsp?id=" + id+"&Linkid="+ Linkid+"&ObjLinkValue="+ObjLinkValue+"&OutLinkValue="+OutLinkValue+"&name="+name;
                } else {
                    //display error page
                }
            }else if (pageCall.equals("updateCourses")) {
                String name = request.getParameter("name");
                String id = request.getParameter("id");
                String Courseid = request.getParameter("Courseid");
                String CourseName = request.getParameter("CourseName");
                String CourseLevel = request.getParameter("CourseLevel");
                String Cid = request.getParameter("Cid");

                if (id != null) {
                    System.out.println(id+"      "+name+"       "+Courseid+"        "+CourseName+"        "+CourseLevel);
                    pageName = "addCourses.jsp?id=" + id+"&Courseid="+ Courseid+"&CourseName="+CourseName+"&CourseLevel="+CourseLevel+"&name="+name+"&Cid="+Cid;
                } else {
                    //display error page
                }
            }else {
                pageName = "programList.jsp";
            }
        }else if(cmdCall !=null){
            String name = request.getParameter("name");
            String id = request.getParameter("id");
            if(cmdCall.equals("upload")){
                if(request.getParameter("err")!= null){
                    pageName = "import.jsp?name="+name+"&id="+id+"&err="+request.getParameter("err");
                }else {
                    pageName = "import.jsp?name="+name+"&id="+id+"&file="+request.getParameter("file");
                }

            }else if(cmdCall.equals("confirm")){
                String dataType = request.getParameter("data");
                pageName = "upload.jsp?id="+id+"&data="+dataType;
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
    <link href="/css/bootstrap-select.min.css" rel="stylesheet" />
   <%-- <link href="/css/ct-paper.css" rel="stylesheet"/>
    <link href="/css/demo.css" rel="stylesheet" /> --%>
    <link href="/css/style.css" rel="stylesheet" />
    <link href="/css/users.css" rel="stylesheet" />
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

    <div id="main">
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
<%--
<script src="/js/ct-paper.js"></script>--%>

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