<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/26/2016
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en" class="no-js">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/wizardStyle.css">
    <link rel="stylesheet" href="/css/bootstrap.css">

    <title>ABETAS Setup Wizard</title>
</head>

<body>

<div class="row">
    <div id="nav" class="col-md-8 col-md-offset-2">
        <section>
            <h2>System setting</h2>
            <nav>
                <ol class="cd-breadcrumb custom-separator">
                    <li class="current"><a href="#0">Welcome</a></li>
                    <li><a href="#0">College information</a></li>
                    <li><a href="#0">Logo</a></li>
                </ol>
            </nav>
        </section>

        <div id="welcome" class="col-md-6 col-md-offset-3 jumbotron" style="background-color:#ecf0f1;">
            <h2>Welcome to ABETAS</h2>
            <p>This is the setup wizard of the system, this wizard will require set of some information. use the navigator ubove to move around, or you can fill them later by going to system setting area.</p>
            <button class="btn btn-warning">Skip it!</button>
        </div>
    </div>
    <div id="setting" class="col-md-6 col-md-offset-3" style="background-color:#cacfd2;">
        <form>
            <div class="form-group">
                <label for="exampleInputEmail1">University Name</label>
                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="University Name" size="25">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">College Name</label>
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="College Name">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Dean First Name</label>
                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Dean First Name" size="25">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Dean Middle Name</label>
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Dean Middle Name">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Dean Last Name</label>
                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Dean Last Name" size="25">
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-default">Save</button>
            </div>
        </form>

    </div>

    <div id="logo" class="col-md-6 col-md-offset-3" style="background-color:#ecf0f1;">
        <form>
            <div class="form-group">
                <label for="exampleInputEmail1">University Logo</label>
                </br>
                <label for="exampleInputFile">File input</label>
                <input type="file" id="exampleInputFile">
                <p class="help-block">maximum file size 10MB.</p>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Upload</button>
                    <button type="submit" class="btn btn-success">Start ABETAS</button>
                </div></div>
        </form>

    </div>
</div>

<div id="congrats" class="col-md-6 col-md-offset-3 jumbotron" style="background-color:#ecf0f1;">
    <h2>Congratulations</h2>
    <p>ABETAS setup done successfuly, now you need to add a superuser so he/she can enter the programs, courses, create an evaluation cycle.</br>Click on User Management to add and manage users.</p>
    <button class="btn btn-primary">User Management</button>
    <button class="btn btn-primary">Home Page</button>
</div>


</body>

</html>