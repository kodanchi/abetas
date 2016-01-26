<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/19/2016
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>






<div class="container">




  <div>

    <h1>Import Excel File</h1>

    <p class="lead">Please insure that the excel file format is following the same format as the figure, you are responsible for any wrong important data.</p>


    <img src="cinqueterre.jpg" class="img-responsive" alt="Cinque Terre" width="304" height="236">

    <h1>Insert</h1>
    <form name="myform" action="/Insert" method="post">
      <label>name: </label><input type="text" name="name" value="" size="50"/>
      <label>logo: </label><input type="text" name="logo" value="" size="50"/>
      <input class="btn btn-lg btn-success" type="submit" value="add" name="Add"/>
    </form>
    <h1>Update</h1>
    <form name="myform" action="/Update" method="post">
      <label>name: </label><input type="text" name="name" value="" size="50"/>
      <label>logo: </label><input type="text" name="logo" value="" size="50"/>
      <input class="btn btn-lg btn-success" type="submit" value="add" name="Add"/>
    </form>
    <h1>Delete</h1>
    <form name="myform" action="/Delete" method="post">
      <label>name: </label><input type="text" name="name" value="" size="50"/>
      <label>logo: </label><input type="text" name="logo" value="" size="50"/>
      <input class="btn btn-lg btn-success" type="submit" value="add" name="Add"/>
    </form>
    <h1>Select</h1>
    <form name="myform" action="/Select" method="post">
      <label>name: </label><input type="text" name="name" value="" size="50"/>
      <label>logo: </label><input type="text" name="logo" value="" size="50"/>
      <input class="btn btn-lg btn-success" type="submit" value="add" name="Add"/>
    </form>

  </div>

</div>

</body>
</html>