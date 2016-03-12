<%--
  Created by IntelliJ IDEA.
  User: Mohammed
  Date: 1/25/2016
  Time: 7:49 PM
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
        <p>Enter the code you just recieved in your email.</p>

        <form method="post" action="/Passcode" onclick="verify()">
            <div class="form-group">
                <input type="email" class="form-control" name="emailReset" placeholder="email" size="25" required>
                <input type="number" class="form-control" name="emailPassCode" placeholder="Passcode" size="25" required>
            </div>
            <button type="submit" class="btn btn-warning">cancel</button>
            <button type="button" class="btn btn-success">Enter the new password</button>
        </form>


    </div>


</div>


</body>

</html>
