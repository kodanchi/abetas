<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/25/2016
  Time: 7:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

    <div>

        <h1>Add Program Objective</h1>

        <p>You need to select a program and enter its objectives.</p>

        <form role="form" name="myform" action="#" method="post">
            <p><label>Program:</label><select name="selected">
                <option value="volvo">Volvo</option>
                <option value="saab">Saab</option>
                <option value="opel">Opel</option>
                <option value="audi">Audi</option>
            </select>*Required</p>
            <p><div class="form-group">
            <label for="comment">Program Objectives:</label>
            <textarea class="form-control" rows="5" id="comment"></textarea>
        </div>*Required</p>
        </form>

        <p><a class="btn btn-lg btn-success" href="#" role="button">Add</a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a class="btn btn-lg btn-success" href="#" role="button">Cancel</a></p>

    </div>

</div>

</body>
</html>
