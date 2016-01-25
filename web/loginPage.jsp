<%--
  Created by IntelliJ IDEA.
  User: Mohammed
  Date: 1/25/2016
  Time: 4:00 PM
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

</head>

<body>
<div class="row">
    <div class="col-md-4 col-md-offset-4 well vcenter">
        <form class="form-signin">

            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <button class="btn btn-link">Forgot your password?</button>

        </form>

    </div>
    <!-- /col-md -->
</div>
<!-- /row -->

</body>

</html>
