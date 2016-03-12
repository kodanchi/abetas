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

<div class="section">
    <div class="container">
        <!--         what is row -->
        <div class="row ">

            <div class="col-sm-3 col-xs-6 mainblock">
                <div class="tile">
                    <a href="/users">
                    <img src="img/users.png" alt="Compas" class="tile-image big-illustration">
                    <h6 class="tile-title text-uppercase"  data-toggle="tooltip" title="Add/update/delete superuser, faculty or evaluator">User Management</h6>
                    </a>
                </div>
            </div>

            <div class="col-sm-3 col-xs-6 mainblock">
                <div class="tile block">
                    <a href="/program">
                    <img src="img/programs.png" alt="Compas" class="tile-image big-illustration">
                    <h6 class="tile-title text-uppercase" data-toggle="tooltip" title="Add/update/delete programs, students outcomes, courses" >Program Management</h6>
                    </a>
                </div>
            </div>


            <div class="col-sm-3 col-xs-6 mainblock">
                <div class="tile block">
                    <a href="/cycle">
                    <img src="img/cycle.png" alt="Compas" class="tile-image big-illustration">
                    <h6 class="tile-title text-uppercase"  data-toggle="tooltip" title="Add/update/delete cycles, performance indicators">Cycle Management</h6>
                    </a>
                </div>
            </div>

            <div class="col-sm-3 col-xs-6 mainblock">
                <div class="tile block">
                    <a href="/vis">
                    <img src="img/graph.png" alt="Compas" class="tile-image big-illustration">
                    <h6 class="tile-title text-uppercase" data-toggle="tooltip" title="Observe related graphs for each performance indicator">Reports</h6>
                    </a>
                </div>
            </div>

            <div class="col-sm-3 col-xs-6 mainblock">
                <div class="tile block">
                    <a href="/users/index.jsp?page=log">
                    <img src="img/log.png" alt="Compas" class="tile-image big-illustration">
                    <h6 class="tile-title text-uppercase" data-toggle="tooltip" title="All actions of users are registered and documented here">System Log</h6>
                    </a>
                </div>
            </div>


        </div>
    </div>
</div>

<div id="footer">
    <jsp:include page="/Footer.jsp"/>
</div>

</body>
</html>