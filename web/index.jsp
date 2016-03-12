<%@ page import="sessionListener.CookiesControl" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/19/2016
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.js" type="text/javascript"></script>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>ABETAS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/demo.css" rel="stylesheet" />
    <link href="css/users.css" rel="stylesheet" />
    <link href="css/bootstrap-select.min.css" rel="stylesheet" />
    <link href="css/cus.css" rel="stylesheet" />
    <link href="css/flat-ui.css" rel="stylesheet" />
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

</head>
<body>



<div id="header">
    <jsp:include page="/Header.jsp"/>
</div>
<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>

    <div class="container">
        <div class="row">

            <h2 class="text-center">Superuser's main page</h2>

            <div class="col-md-3 col-sm-6 mainblock">
                <a href="/users">
                <div class=" block">

                        <i class="fa fa-users"></i>

                        <h6 class="-title text-uppercase"  data-toggle="tooltip" title="Add/update/delete superuser, faculty or evaluator">User Management</h6>

                </div>
                </a>
            </div>

            <div class="col-md-3 col-sm-6 mainblock">
                <a href="/program">
                    <div class=" block">

                            <i class="fa fa-cogs"></i>
                        <h6 class="-title text-uppercase" data-toggle="tooltip" title="Add/update/delete programs, students outcomes, courses" >Program Management</h6>

                    </div>
                </a>
            </div>


            <div class="col-md-3 col-sm-6 mainblock">
                <a href="/cycle">
                <div class=" block">

                        <i class="fa fa-refresh"></i>
                    <h6 class="-title text-uppercase"  data-toggle="tooltip" title="Add/update/delete cycles, performance indicators">Cycle Management</h6>

                </div>
                </a>
            </div>

            <div class="col-md-3 col-sm-6 mainblock">
                <a href="/vis">
                    <div class=" block">

                            <i class="fa fa-bar-chart"></i>
                        <h6 class="-title text-uppercase" data-toggle="tooltip" title="Observe related graphs for each performance indicator">Reports</h6>

                    </div>
                </a>
            </div>

            <div class="col-md-3 col-sm-6 mainblock">
                <a href="/users/index.jsp?page=log">
                    <div class=" block">
                            <i class="fa fa-list-alt"></i>
                        <h6 class="-title text-uppercase" data-toggle="tooltip" title="All actions of users are registered and documented here">System Log</h6>

                    </div>
                </a>
            </div>


        </div>
    </div>

<div id="footer">
    <jsp:include page="/Footer.jsp"/>
</div>

</body>
</html>