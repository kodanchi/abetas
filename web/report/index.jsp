
<%@ page import="EDB.EncDec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /**
     * used to display the structure of the user pages by including header and footer and main container based on the
     * request parameter
     */
    String pageName = "evaList.jsp";
    if(request.getMethod().equals("GET")){
        pageName = null;
        String pageCall = request.getParameter("page");
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

                 if(request.getParameter("type")!= null) {
                     if (EncDec.getDecr(request.getParameter("type")).equals("formative")) {

                         pageName = "formativeDisplay.jsp?id=" + EncDec.getDecr(request.getParameter("id"));

                     }
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
    <link href="/css/flat-ui.css" rel="stylesheet" />




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
<script src="/js/pageloading.js"></script>

<script>
    $.ajaxPrefilter(function( options, originalOptions, jqXHR ) { options.async = true; });

</script>

</html>