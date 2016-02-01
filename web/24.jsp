<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 1/24/2016
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%! %>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap.min.js"></script>


<script src="js/users.js"></script>




<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-2.2.0.min.js"></script>





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

        <h1 style="text-align: center">Add User</h1>

        <form id="UserAddForm" method="post" class="form-horizontal" action="/Update">

            <div class="form-group">
                <label class="col-xs-3 control-label">User Type</label>
                <div class="col-xs-5 selectContainer">
                    <select class="form-control"  id="userType" onchange="onUserTypeChng()" >
                        <option value="">User Type</option>
                        <option value="Superuser">Superuser</option>
                        <option value="Faculty_Member">Faculty Member</option>
                        <option value="Evaluator">Evaluator</option>
                    </select>
                </div>
            </div>

            <br>
            <div class="form-group">
                <label>First Name</label>
                <input type="text" class="form-control" placeholder="First Name" name="fname" id="w" required>
            </div>

            <div class="form-group">

                <label>Middle Name</label>

                <input type="text" class="form-control" placeholder="Middle Name" name="mname" required>

            </div>

            <div class="form-group">
                <label>Last Name</label>
                <input type="text" class="form-control" placeholder="Last Name" name="lname" required>
            </div>

            <div class="form-group">
                <label>Username</label>
                <input type="text" class="form-control" placeholder="Username" name="uname" required>
            </div>

            <br>

            <div class="form-group" id="emailDiv">


            </div>

            <button type="submit" class="btn btn-default" value="Add">Add</button>

            <button type="cancel" class="btn btn-default" value="Cancel">Cancel</button>

        </form>
    </div>
</div>

<!-- Include Bootstrap Combobox -->
<link rel="stylesheet" href="css/bootstrap-combobox.css">

<script src="js/bootstrap-combobox.js"></script>

<style type="text/css">
    /* Adjust feedback icon position */
    #UserAddForm .selectContainer .form-control-feedback,
    #UserAddForm .inputGroupContainer .form-control-feedback {
        right: -15px;
    }
</style>










<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>