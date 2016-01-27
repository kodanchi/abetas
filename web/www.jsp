<%! %>

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


<div style="width: 70%; margin-left: auto; margin-right: auto">

    <div style="width: 50%; margin-left: auto; margin-right: auto">


        <form name="myform" method="post" action="/Insert">

            <div class="form-group">
                <label>University Name</label>
                <input type="text" class="form-control" placeholder="University Name" name="uname" required>
            </div>

            <div class="form-group">

                <label>College Name</label>

                <input type="text" class="form-control" placeholder="College Name" name="cname" required>

            </div>

        <hr>

        <p>Please select the university logo be uploaded.</p>
        <img src="..." alt="..." class="img-rounded" style="float: right">
            <div class="form-group">

                <label>University logo</label>

                <input type="text" class="form-control" placeholder="University Logo" name="universityLogo" required>

            </div>

        <hr>

        <p>Fill the following required field to create Administrator</p>

            <div class="form-group">
                <label>Admin Username</label>
                <input name="adminUsername" type="text" class="form-control" placeholder="Admin Username" required>
            </div>

            <div class="form-group">

                <label>Admin Password</label>

                <input name="adminpassword" id="txtPassword" type="password" class="form-control" placeholder="Admin Password" required>

            </div>

            <div class="form-group">

                <label>Re-Enter Password</label>

                <input name="rePassword" id="txtConfirmPassword" type="password" class="form-control" placeholder="Re-Enter Password" onchange="checkPasswordMatch();" required>

            </div>

            <script type="text/javascript">
                function Validate() {
                    var password = document.getElementById("txtPassword").value;
                    var confirmPassword = document.getElementById("txtConfirmPassword").value;
                    if (password != confirmPassword) {
                        alert("Passwords do not match.");
                        return false;
                    }
                    return true;
                }
            </script>

            <div class="form-group">
                <label>Admin Email</label>
                <input name="adminemail" type="email" class="form-control" placeholder="Admin Email" required>
            </div>


            <div class="form-group">
                <label>Admin First Name</label>
                <input name="adminFirstName" type="text" class="form-control" placeholder="Admin First Name" required>
            </div>


            <div class="form-group">
                <label>Admin Middle Name</label>
                <input name="adminMiddleName" type="text" class="form-control" placeholder="Admin Middle Name" required>
            </div>


            <div class="form-group">
                <label>Admin Last Name</label>
                <input name="adminLastName" type="text" class="form-control" placeholder="Admin Last Name" required>
            </div>


            <a href="11.jsp"><button type="submit" class="btn btn-default" value="Finish" style="float:right" onclick="return Validate()">Finish</button></a>
            </form>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>