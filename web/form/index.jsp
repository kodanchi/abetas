
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    /**
     * used to display the structure of the form pages by including header and footer and main container based on the request
     * parameters.
     */

    String pageName = "formList.jsp";
    if(request.getMethod().equals("GET")){
        pageName = null;
        String pageCall = request.getParameter("page");
        if(pageCall != null){
             if(pageCall.equals("fillForm")) {

                if (request.getParameter("type").equals("formative")) {
                    pageName = "Formative.jsp?Formative_ID=" + request.getParameter("id");

                }else if(request.getParameter("type").equals("summative")) {
                    pageName = "Summative.jsp?Summative_ID=" + request.getParameter("id");
                }
            }else if(pageCall.equals("unlockForm")){
                 pageName = "unlockform.jsp";
            }else if(pageCall.equals("unsubmitted")){
                 pageName = "unsubmittedForm.jsp";
            }else if(pageCall.equals("showForm")){
                 if (request.getParameter("type").equals("formative")) {
                     pageName = "formativeDisplay.jsp?Formative_ID=" + request.getParameter("id");

                 }else if(request.getParameter("type").equals("summative")) {
                     pageName = "summativeDisplay.jsp?Summative_ID=" + request.getParameter("id");
                 }
            }else {
                 pageName = "formList.jsp";
             }
        }else {
            pageName = "formList.jsp";
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

    <link href="/css/bootstrap.css" rel="stylesheet" />
    <link href="/css/demo.css" rel="stylesheet" />
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


    <div id="footer">
        <jsp:include page="/Footer.jsp"/>
    </div>
</div>
<div id="loading" ></div>
</body>

<script src="/js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->

<script src="/js/bootstrap-select.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/jquery.bsFormAlerts.js"></script>
<script src="/js/uploadInput.js"></script>

<script>
    $.ajaxPrefilter(function( options, originalOptions, jqXHR ) { options.async = true; });

</script>

</html>