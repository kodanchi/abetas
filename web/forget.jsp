<%--
  Created by IntelliJ IDEA.
  User: Mohammed
  Date: 1/25/2016
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
        <p>Enter your registered email to send you a recovery passcode.</p>

        <form method="post" action="/enterPasscode.jsp">
            <div class="form-group">
                <input type="email" class="form-control" name="exampleInputEmail1" placeholder="Email" size="25" required>
                <p style="color:#c0392b;">This email is not registered!</p>
                <p style="color:#2980b9;">Now check your email</p>

            </div>
            <button type="button" class="btn btn-warning">cancel</button>
            <button type="submit" class="btn btn-success">Retrieve password</button>
        </form>


    </div>


</div>


</body>

</html>
