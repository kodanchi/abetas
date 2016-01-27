<%--
  Created by IntelliJ IDEA.
  User: Mohammed
  Date: 1/26/2016
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ABETAS</title>
    <meta name="description" content="An interactive getting started guide for Brackets.">

    <link rel="stylesheet" href="css/loginPage.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap.css">


</head>

<body>
<div class="row">
    <div class="col-md-6 col-xs-6 col-md-offset-3 well base">
        <h1>ABETAS</h1>
        <p>Enter the new password two times please.</p>

        <form>
            <div class="form-group">
                <input type="password" class="form-control" name="newPassword" placeholder="New password" size="25">
                <input type="password" class="form-control" name="newPasswordconfirm" placeholder="Re-enter new password" size="25">

            </div>
            <button type="submit" class="btn btn-warning">Cancel</button>
            <button type="submit" class="btn btn-success">Login</button>
        </form>


    </div>


</div>


</body>

</html>
