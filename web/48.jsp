<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/25/2016
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>


<div style="width: 70%; margin-left: auto; margin-right: auto; background-color: lightgray">

    <div style="width: 50%; margin-left: auto; margin-right: auto">

        <h1 style="text-align: center">Add Course</h1>

        <p>You need to enter the name of the course, code, level and type:</p>

        <form>

            <div class="form-group">

                <label>Course Name</label>

                <input type="text" class="form-control" placeholder="Course Name" required>

            </div>

            <div class="form-group">

                <label>Course Code</label>

                <input type="text" class="form-control" placeholder="Course Code" required>

            </div>


            <div class="dropdown">

                <label>Level</label>

                <br>

                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    Dropdown
                    <span class="caret"></span>

                </button>

                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

                    <li><a href="#">Action</a></li>

                    <li><a href="#">Another action</a></li>

                    <li><a href="#">Something else here</a></li>

                    <li role="separator" class="divider"></li>

                    <li><a href="#">Separated link</a></li>

                </ul>

            </div>

            <br>
            <br>

            <button type="button" class="btn btn-default" value="Add">Add</button>

            <button type="button" class="btn btn-default" value="Cancel">Cancel</button>

        </form>
    </div>
</div>





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>