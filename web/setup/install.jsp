
<%@ page import="Setup.InstallDB" %>
<%
    /**
     * used to display the setup page of the system.
     */

    InstallDB dbCon = new InstallDB(null);
    if(dbCon.setUpChk()){
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
    <link href="/css/bootstrap-select.min.css" rel="stylesheet" />
    <link href="/css/cus.css" rel="stylesheet" />
    <link href="/css/flat-ui.css" rel="stylesheet" />
</head>
<body>


<div class='container'>
    <section id="wizard">
        <form name="myform" id="setUpForm" method="post" enctype="multipart/form-data" action="/install">
            <div class="page-header">
                <h2 class="text-center">ABETAS SETUP</h2>
            </div>

            <div id="rootwizard">

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
                                <span data-alertid="uname"></span>
                            </div>

                            <div class="form-group">

                                <label for="cname">College Name  <h6 class="label label-danger">required</h6></label>

                                <input type="text" id="cname" class="form-control" placeholder="College Name" name="cname" required>
                                <span data-alertid="cname"></span>

                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab3">
                        <div class="jumbotron form-group">
                            <h4>Choose the logo of the university to be uploaded</h4>
                            <h5>You can upload the logo later on from system settings.</h5>
                            <label for="ulogo" >University Logo</label>

                            <div class="input-group form-group">
    <span class="input-group-btn">
                    <span class="btn btn-file" style="color:#ecf0f1; background-color: #7f8c8d;">
                        Browse&hellip; <input type="file" id="ulogo" onchange="return fileChk(2000000);"
                                              ACCEPT="image/png" name="ulogo" required>
                    </span>
    </span>
                                <input type="text" class="form-control" readonly>
                            </div>

                            <script >
                                function fileChk(max_img_size) {

                                    $(document).trigger("clear-alert-id.ulogo");
                                    var input = document.getElementById("ulogo");
                                    var ext = $('#ulogo').val().split('.').pop().toLowerCase();
                                    if(input.files && input.files.length == 1)
                                    {
                                        if (input.files[0].size > max_img_size)
                                        {
                                            document.getElementById("alert").style.visibility = "visible";
                                            $(document).trigger("clear-alert-id.ulogo");
                                            $(document).trigger("set-alert-id-ulogo", [
                                                {
                                                    message: "University logo must not exceeds 2 MB",
                                                    priority: "error"
                                                }
                                            ]);
                                            input.value = "";
                                            return false;
                                        }else if(ext != "png"){
                                            document.getElementById("alert").style.visibility = "visible";
                                            $(document).trigger("clear-alert-id.ulogo");
                                            $(document).trigger("set-alert-id-ulogo", [
                                                {
                                                    message: "University logo must be type of PNG",
                                                    priority: "error"
                                                }
                                            ]);
                                            input.value = "";
                                            return false;
                                        }
                                    }
                                    return true;

                                }
                            </script>
                            <h5 class="help-block small">Allowed size and type: 2MB, png</h5>
                            <span data-alertid="ulogo"></span>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab4">
                        <div class="jumbotron">
                            <h4>Enter Administrator's details (All fields are required):</h4>

                            <div class=" form-group">
                                <label for="adminUsername">Admin Username  <h6 class="label label-danger">required</h6></label>
                                <input id="adminUsername" name="adminUsername" type="text" class="form-control" placeholder="Admin Username" required>
                                <span data-alertid="username"></span>
                            </div>

                            <div class=" form-group">

                                <label for="txtPassword">Admin Password  <h6 class="label label-danger">required</h6></label>

                                <input name="adminpassword" id="txtPassword" type="password" class="form-control" placeholder="Admin Password" required>
                                <span data-alertid="password"></span>
                            </div>

                            <div class=" form-group">

                                <label for="txtConfirmPassword">Re-Enter Password  <h6 class="label label-danger">required</h6></label>

                                <input name="rePassword" id="txtConfirmPassword" type="password" class="form-control" placeholder="Re-Enter Password" required>
                                <span data-alertid="repassword"></span>
                            </div>

                            <div class=" form-group">
                                <label for="adminemail">Admin Email  <h6 class="label label-danger">required</h6></label>
                                <input id="adminemail" name="adminemail" type="email" class="form-control" placeholder="Admin Email" required>
                                <span data-alertid="uemail"></span>
                            </div>

                        </div>
                    </div>
                    <div class="tab-pane" id="tab5">
                        <div class="jumbotron" >
                            <h4>Fill the admin's first, middle and last names:</h4>
                            <div class=" form-group">
                                <label>Admin First Name  <h6 class="label label-danger">required</h6></label>
                                <input id="adminFirstName" name="adminFirstName" type="text" class="form-control" placeholder="Admin First Name" required>
                                <span data-alertid="fname"></span>
                            </div>


                            <div class=" form-group">
                                <label>Admin Middle Name  <h6 class="label label-danger">required</h6></label>
                                <input id="adminMiddleName" name="adminMiddleName" type="text" class="form-control" placeholder="Admin Middle Name" required>
                                <span data-alertid="mname"></span>
                            </div>


                            <div class=" form-group">
                                <label>Admin Last Name  <h6 class="label label-danger">required</h6></label>
                                <input id="adminLastName" name="adminLastName" type="text" class="form-control" placeholder="Admin Last Name" required>
                                <span data-alertid="lname"></span>
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
                        <li class="finish pull-right btn btn-primary"><a href="javascript:;">Finish</a></li>
                    </ul>
                </div>
            </div>

        </form>
    </section>
</div>
<script src="/js/jquery-2.2.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.bootstrap.wizard.js"></script>
<script src="/js/prettify.js"></script>
<script src="/js/jquery.bsFormAlerts.js"></script>
<script src="/js/uploadInput.js"></script>
<script>
    $(document).ready(function() {


        var cnaSubmit = false;
        
        $('#rootwizard').bootstrapWizard({onNext: function(tab, navigation, index) {

        }, onTabShow: function(tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index+1;
            var $percent = ($current/$total) * 100;
            $('#rootwizard .progress-bar').css({width:$percent+'%'});

        }});
        $('#rootwizard .finish').click(function() {

            $(document).trigger("clear-alert-id.uname");
            $(document).trigger("clear-alert-id.cname");
            $(document).trigger("clear-alert-id.ulogo");
            $(document).trigger("clear-alert-id.username");
            $(document).trigger("clear-alert-id.repassword");
            $(document).trigger("clear-alert-id.password");
            $(document).trigger("clear-alert-id.uemail");
            $(document).trigger("clear-alert-id.fname");
            $(document).trigger("clear-alert-id.mname");
            $(document).trigger("clear-alert-id.lname");


            if(!$('#uname').val()) {

                $(document).trigger("clear-alert-id.uname");
                $(document).trigger("set-alert-id-uname", [
                    {
                        message: "Please enter university name",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab2']").trigger('click');
                $('#uname').focus();
                return false;
            }else if(!$('#cname').val()) {

                $(document).trigger("set-alert-id-cname", [
                    {
                        message: "Please enter college name",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab2']").trigger('click');
                $('#cname').focus();
                return false;
            }else if(!/^[A-Za-z\s]+$/g.test($('#uname').val())) {
                $(document).trigger("clear-alert-id.uname");
                $(document).trigger("set-alert-id-uname", [
                    {
                        message: "name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab2']").trigger('click');
                $('#uname').focus();
                return false;
            }else if(!/^[A-Za-z\s]+$/g.test($('#cname').val())) {
                $(document).trigger("clear-alert-id.cname");
                $(document).trigger("set-alert-id-cname", [
                    {
                        message: "name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab2']").trigger('click');
                $('#cname').focus();
                return false;
            }else if($('#ulogo').val()) {
                var input = document.getElementById("ulogo");
                if(input.files && input.files.length == 1)
                {
                    if (input.files[0].size > 2000000)
                    {
                        $(document).trigger("clear-alert-id.ulogo");
                        $(document).trigger("set-alert-id-ulogo", [
                            {
                                message: "University logo must not exceeds 2 MB",
                                priority: "error"
                            }
                        ]);
                        return false;
                    }
                }
            }else if(!$('#adminUsername').val()) {
                $(document).trigger("clear-alert-id.username");
                $(document).trigger("set-alert-id-username", [
                    {
                        message: "Please enter admin username",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#adminUsername').focus();
                return false;
            }else if($('#adminUsername').val().length < 4 || $('#adminUsername').val().length > 20) {
                $(document).trigger("clear-alert-id.username");
                $(document).trigger("set-alert-id-username", [
                    {
                        message: "username must be in range 4-20 characters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#adminUsername').focus();
                return false;
            }else if(!$('#txtPassword').val()) {
                $(document).trigger("clear-alert-id.password");
                $(document).trigger("set-alert-id-password", [
                    {
                        message: "Please enter admin password",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#txtPassword').focus();
                return false;
            }else if($('#txtPassword').val().length < 6 || $('#txtPassword').val().length > 15) {
                $(document).trigger("clear-alert-id.password");
                $(document).trigger("set-alert-id-password", [
                    {
                        message: "Password must be in the range 6-15 characters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#txtPassword').focus();
                return false;
            }else if(($('#txtPassword').val() != "" || $('#txtConfirmPassword').val() != "") && ($('#txtPassword').val() != $('#txtConfirmPassword').val())) {
                $(document).trigger("clear-alert-id.repassword");
                $(document).trigger("set-alert-id-repassword", [
                    {
                        message: "Please re-enter the same password",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#txtConfirmPassword').focus();
                return false;
            }else if(!$('#adminemail').val()) {
                $(document).trigger("clear-alert-id.uemail");
                $(document).trigger("set-alert-id-uemail", [
                    {
                        message: "Please enter admin email",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#adminemail').focus();
                return false;
            }else if($('#adminemail').val() && !/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test($('#adminemail').val()) ) {
                $(document).trigger("clear-alert-id.uemail");
                $(document).trigger("set-alert-id-uemail", [
                    {
                        message: "Enter valid admin email",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#adminemail').focus();
                return false;
            }else if(!$('#adminFirstName').val()) {
                $(document).trigger("clear-alert-id.fname");
                $(document).trigger("set-alert-id-fname", [
                    {
                        message: "Please enter admin first name",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminFirstName').focus();
                return false;
            }else if(!/^[a-zA-Z]*$/g.test($('#adminFirstName').val())) {
                $(document).trigger("clear-alert-id.fname");
                $(document).trigger("set-alert-id-fname", [
                    {
                        message: "name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminFirstName').focus();
                return false;
            }else if($('#adminFirstName').val().length < 3 || $('#adminFirstName').val().length > 20) {
                $(document).trigger("clear-alert-id.fname");
                $(document).trigger("set-alert-id-fname", [
                    {
                        message: "first name must be in range 3-20 characters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminFirstName').focus();
                return false;
            }else if(!$('#adminMiddleName').val()) {
                $(document).trigger("clear-alert-id.mname");
                $(document).trigger("set-alert-id-mname", [
                    {
                        message: "Please enter admin middle name",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminMiddleName').focus();
                return false;
            }else if(!/^[a-zA-Z]*$/g.test($('#adminMiddleName').val())) {
                $(document).trigger("clear-alert-id.mname");
                $(document).trigger("set-alert-id-mname", [
                    {
                        message: "name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminMiddleName').focus();
                return false;
            }else if($('#adminMiddleName').val().length < 3 || $('#adminMiddleName').val().length > 20) {
                $(document).trigger("clear-alert-id.mname");
                $(document).trigger("set-alert-id-mname", [
                    {
                        message: "middle name must be in range 3-20 characters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminMiddleName').focus();
                return false;
            }else if(!$('#adminLastName').val()) {
                $(document).trigger("clear-alert-id.lname");
                $(document).trigger("set-alert-id-lname", [
                    {
                        message: "Please enter admin last name",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminLastName').focus();
                return false;
            }else if(!/^[a-zA-Z]*$/g.test($('#adminLastName').val())) {
                $(document).trigger("clear-alert-id.lname");
                $(document).trigger("set-alert-id-lname", [
                    {
                        message: "name must have only alphabetic letters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminLastName').focus();
                return false;
            }else if($('#adminLastName').val().length < 3 || $('#adminLastName').val().length > 20) {
                $(document).trigger("clear-alert-id.lname");
                $(document).trigger("set-alert-id-lname", [
                    {
                        message: "last name must be in range 3-20 characters",
                        priority: "error"
                    }
                ]);
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminLastName').focus();
                return false;
            }else {
            }

            if($('#adminemail').val()) {
                var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                if(!re.test($('#adminemail').val())){
                    $(document).trigger("clear-alert-id.uemail");
                    $(document).trigger("set-alert-id-uemail", [
                        {
                            message: "Enter valid admin email",
                            priority: "error"
                        }
                    ]);
                    $('#rootwizard').find("a[href*='tab4']").trigger('click');
                    $('#adminemail').focus();
                }else {
                    canSubmit = true;
                }
            }

            if(canSubmit){
                $('#setUpForm').submit();
            }



        });
    });
</script>
</body>
</html>

