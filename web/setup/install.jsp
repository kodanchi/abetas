<%@ page import="com.database.ASDB" %>
<%@ page import="com.database.InstallDB" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/26/2016
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.

  http://stackoverflow.com/questions/6327965/html-upload-max-file-size-does-not-appear-to-work
--%>
<%
InstallDB dbCon = new InstallDB(null);
    if(dbCon.setUpChk()){
        //System.out.println("insiiiiiide");
        response.sendRedirect("/setup");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ABETAS Setup Wizard</title>
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/prettify.css" rel="stylesheet">
</head>
<body>
<div class='container'>

    <section id="wizard">
        <form name="myform" id="setUpForm" method="post" enctype="multipart/form-data" action="/install">
            <div class="page-header">
                <h1>ABETAS SETUP</h1>
            </div>

            <div id="rootwizard">
                <div id="alert"  class="alert alert-danger fade in"  role="alert" >
                    <strong id="alertt" ></strong>
                </div>
                <div class="navbar">
                    <div class="navbar-inner">
                        <div class="container">
                            <ul>
                                <li><a href="#tab1" data-toggle="tab">Welcome</a></li>
                                <li><a href="#tab2" data-toggle="tab">University</a></li>
                                <li><a href="#tab3" data-toggle="tab">Logo</a></li>
                                <li><a href="#tab4" data-toggle="tab">Admin sign up</a></li>
                                <li><a href="#tab5" data-toggle="tab">Admin details</a></li>
                                <li><a href="#tab6" data-toggle="tab">Confirm</a></li>

                            </ul>
                        </div>
                    </div>
                </div>
                <div id="bar" class="progress">
                    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
                </div>

                <div class="tab-content">
                    <div class="tab-pane" id="tab1">
                        <div class="jumbotron">
                            <h2>Welcome to ABETAS</h2>
                            <p>This wizard will help you to setup ABETAS, you need set of information to fill. use the navigator above to move around, or you click on next and previous buttons.</p>

                        </div>
                    </div>

                    <div class="tab-pane" id="tab2">
                        <div class="jumbotron">
                            <div class="form-group">
                                <label for="uname">University Name  <h6 class="label label-danger">required</h6></label>
                                <input type="text" id="uname" class="form-control" placeholder="University Name" name="uname" required>
                            </div>

                            <div class="form-group">

                                <label for="cname">College Name  <h6 class="label label-danger">required</h6></label>

                                <input type="text" id="cname" class="form-control" placeholder="College Name" name="cname" required>

                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab3">
                        <div class="jumbotron form-group">
                            <h4>Choose the logo of the university to be uploaded</h4>
                            <h5>You can upload the logo later on from system settings.</h5>
                            <label for="ulogo" >University Logo</label>
                            <input onchange="return fileChk(2000000);"  type="file" id="ulogo" name="ulogo" accept="image/png">
                            <script >
                                function fileChk(max_img_size) {

                                    var input = document.getElementById("ulogo");
                                    var ext = $('#ulogo').val().split('.').pop().toLowerCase();
                                    //alert("file chk "+input.files[0].size);
                                    if(input.files && input.files.length == 1)
                                    {
                                        if (input.files[0].size > max_img_size)
                                        {
                                            document.getElementById("alert").style.visibility = "visible";
                                            $('#alertt').html('university logo must not exceeds 2 MB');
                                            input.value = "";
                                            return false;
                                        }else if(ext != "png"){
                                            document.getElementById("alert").style.visibility = "visible";
                                            $('#alertt').html('university logo must be type of PNG');
                                            input.value = "";
                                            return false;
                                        }
                                    }
                                    document.getElementById("alert").style.visibility = "hidden";
                                    return true;
                                    /*if($('#ulogo').files.length < 200000 ){
                                        document.getElementById("alert").style.visibility = "visible";
                                        $('#alertt').html('university logo must not exceeds 2 MB');
                                        $('#rootwizard').find("a[href*='tab3']").trigger('click');
                                        $('#ulogo').focus();
                                    }*/
                                }
                            </script>
                            <h5 class="help-block">Allowed size and type: 2MB, png</h5>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab4">
                        <div class="jumbotron">
                            <h4>Enter Administrator's details (All fields are required):</h4>

                            <div class=" form-group">
                                <label for="adminUsername">Admin Username  <h6 class="label label-danger">required</h6></label>
                                <input id="adminUsername" name="adminUsername" type="text" class="form-control" placeholder="Admin Username" required>
                            </div>

                            <div class=" form-group">

                                <label for="txtPassword">Admin Password  <h6 class="label label-danger">required</h6></label>

                                <input name="adminpassword" id="txtPassword" type="password" class="form-control" placeholder="Admin Password" required>

                            </div>

                            <div class=" form-group">

                                <label for="txtConfirmPassword">Re-Enter Password  <h6 class="label label-danger">required</h6></label>

                                <input name="rePassword" id="txtConfirmPassword" type="password" class="form-control" placeholder="Re-Enter Password" required>

                            </div>

                            <div class=" form-group">
                                <label for="adminemail">Admin Email  <h6 class="label label-danger">required</h6></label>
                                <input id="adminemail" name="adminemail" type="email" class="form-control" placeholder="Admin Email" required>
                            </div>

                        </div>
                    </div>
                    <div class="tab-pane" id="tab5">
                        <div class="jumbotron" >
                            <h4>Fill the admin's first, middle and last names:</h4>
                            <div class=" form-group">
                                <label>Admin First Name  <h6 class="label label-danger">required</h6></label>
                                <input id="adminFirstName" name="adminFirstName" type="text" class="form-control" placeholder="Admin First Name" required>
                            </div>


                            <div class=" form-group">
                                <label>Admin Middle Name  <h6 class="label label-danger">required</h6></label>
                                <input id="adminMiddleName" name="adminMiddleName" type="text" class="form-control" placeholder="Admin Middle Name" required>
                            </div>


                            <div class=" form-group">
                                <label>Admin Last Name  <h6 class="label label-danger">required</h6></label>
                                <input id="adminLastName" name="adminLastName" type="text" class="form-control" placeholder="Admin Last Name" required>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab6">
                        <div class="jumbotron">
                            <p>
                                Please confirm all the previous data you entered, and then click finish to begin setting up the database.
                            </p>
                        </div>
                    </div>

                    <ul class="pager wizard">
                        <li class="previous first" style="display:none;"><a href="#">First</a></li>
                        <li class="previous"><a href="#">Previous</a></li>
                        <li class="next last" style="display:none;"><a href="#">Last</a></li>
                        <li class="next"><a href="#">Next</a></li>
                        <li class="finish"><a href="javascript:;">Finish</a></li>
                    </ul>
                </div>
            </div>

        </form>
    </section>
</div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.bootstrap.wizard.js"></script>
<script src="/js/prettify.js"></script>
<script>
    $(document).ready(function() {

        document.getElementById("alert").style.visibility = "hidden";
        $('#rootwizard').bootstrapWizard({onNext: function(tab, navigation, index) {

            if(index==2) {
                // Make sure we entered the name
                if(!$('#uname').val()) {
                    document.getElementById("alert").style.visibility = "visible";
                    $('#alertt').html('You must enter university name');
                    $('#uname').focus();
                    return false;
                }else if(!$('#cname').val()) {
                    document.getElementById("alert").style.visibility = "visible";
                    $('#alertt').html('You must enter college name');
                    $('#cname').focus();
                    return false;
                }
            }
            if(index==6) {


            }

            // Set the name for the next tab
            //$('#tab3').html('Please Enter dean\'s of ' + $('#collegeName').val() + ' full name');

        }, onTabShow: function(tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index+1;
            var $percent = ($current/$total) * 100;
            $('#rootwizard .progress-bar').css({width:$percent+'%'});

        }});
        $('#rootwizard .finish').click(function() {
            //alert('Finished!, Starting over!');
            //$('#rootwizard').find("a[href*='tab1']").trigger('click');


            var cnaSubmit = false;
            if(!$('#uname').val()) {

                document.getElementById("alert").style.visibility = "visible";
                $('#alertt').html('You must enter university name');
                $('#rootwizard').find("a[href*='tab2']").trigger('click');
                $('#uname').focus();
                return false;
            }else if(!$('#cname').val()) {
                document.getElementById("alert").style.visibility = "visible";
                $('#alertt').html('You must enter college name');
                $('#rootwizard').find("a[href*='tab2']").trigger('click');
                $('#cname').focus();
                return false;
            }else if($('#ulogo').val()) {
                var input = document.getElementById("ulogo");
                if(input.files && input.files.length == 1)
                {
                    if (input.files[0].size > 2000000)
                    {
                        document.getElementById("alert").style.visibility = "visible";
                        $('#alertt').html('university logo must not exceeds 2 MB');
                        return false;
                    }
                }
            }else if(!$('#adminUsername').val()) {
                document.getElementById("alert").style.visibility = "visible";
                $('#alertt').html('You must enter admin username');
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#adminUsername').focus();
                return false;
            }else if(!$('#txtPassword').val()) {
                document.getElementById("alert").style.visibility = "visible";
                $('#alertt').html('You must enter admin password');
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#ulogo').focus();
                return false;
            }else if(!($('#txtPassword').val() == $('#txtConfirmPassword').val())) {
                document.getElementById("alert").style.visibility = "visible";
                $('#alertt').html('You must re-enter the same new password');
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#txtConfirmPassword').focus();
                return false;
            }else if(!$('#adminemail').val()) {
                document.getElementById("alert").style.visibility = "visible";
                $('#alertt').html('You must enter admin email');
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#adminemail').focus();
                return false;
            }else if(!$('#adminFirstName').val()) {
                document.getElementById("alert").style.visibility = "visible";
                $('#alertt').html('You must enter admin first name');
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminFirstName').focus();
                return false;
            }else if(!$('#adminMiddleName').val()) {
                document.getElementById("alert").style.visibility = "visible";
                $('#alertt').html('You must enter admin middle name');
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminMiddleName').focus();
                return false;
            }else if(!$('#adminLastName').val()) {
                document.getElementById("alert").style.visibility = "visible";
                $('#alertt').html('You must enter admin last name');
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminLastName').focus();
                return false;
            }else {
                $('#alertt').html('').className = "alert alert-danger hidden";
            }

            if($('#adminemail').val()) {
                var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                if(!re.test($('#adminemail').val())){
                    document.getElementById("alert").style.visibility = "visible";
                    $('#alertt').html('You must enter valid admin email');
                    //alert('You must enter valid admin email');
                    $('#rootwizard').find("a[href*='tab4']").trigger('click');
                    $('#adminemail').focus();
                }else {
                    canSubmit = true;
                }
            }

            //binds to onchange event of your input field
            $('#ulogo').bind('change', function() {

                //this.files[0].size gets the size of your file.
                alert("dd");
                if(this.files[0].length < 200000 ){
                    $('#alertt').html('university logo must not exceeds 2 MB');
                }

            });


            if(canSubmit){
                $('#setUpForm').submit();
            }



        });
    });
</script>
</body>
</html>
