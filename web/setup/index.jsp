<%@ page import="com.database.ASDB" %><%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/26/2016
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Wizard With Form Validation</title>
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/prettify.css" rel="stylesheet">
</head>
<body>
<div class='container'>

    <section id="wizard">
        <form name="myform" id="setUpForm" method="post" action="/Insert">
            <div class="page-header">
                <h1>Wizard With Form Validation</h1>
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
                    <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
                </div>
                <div class="tab-content">
                    <div class="tab-pane" id="tab1">
                        <div id="welcome">
                            <h2>Welcome to ABETAS</h2>
                            <p>This is the setup wizard of the system, this wizard will require set of some information. use the navigator ubove to move around, or you can fill them later by going to system setting area.</p>

                        </div>
                    </div>

                    <div class="tab-pane" id="tab2">
                        <div class="form-group">
                            <label for="uname">University Name</label>
                            <input type="text" id="uname" class="form-control" placeholder="University Name" name="uname" required>
                        </div>

                        <div class="form-group">

                            <label for="cname">College Name</label>

                            <input type="text" id="cname" class="form-control" placeholder="College Name" name="cname" required>

                        </div>
                    </div>
                    <div class="tab-pane" id="tab3">
                        <div class="form-group">
                            <label for="ulogo" >University Logo</label>
                            </br>
                            <input  type="file" id="ulogo" accept="image/png">
                            <p class="help-block">maximum file size 10MB.</p>
                            </div>
                    </div>
                    <div class="tab-pane" id="tab4">
                        <div>
                            <p>Fill the following required field to create Administrator</p>

                            <div class="form-group">
                                <label for="adminUsername">Admin Username</label>
                                <input id="adminUsername" name="adminUsername" type="text" class="form-control" placeholder="Admin Username" required>
                            </div>

                            <div class="form-group">

                                <label for="txtPassword">Admin Password</label>

                                <input name="adminpassword" id="txtPassword" type="password" class="form-control" placeholder="Admin Password" required>

                            </div>

                            <div class="form-group">

                                <label for="txtConfirmPassword">Re-Enter Password</label>

                                <input name="rePassword" id="txtConfirmPassword" type="password" class="form-control" placeholder="Re-Enter Password" required>

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
                                <label for="adminemail">Admin Email</label>
                                <input id="adminemail" name="adminemail" type="email" class="form-control" placeholder="Admin Email" required>
                            </div>

                        </div>
                    </div>
                    <div class="tab-pane" id="tab5">
                        <div >
                            <div class="form-group">
                                <label>Admin First Name</label>
                                <input id="adminFirstName" name="adminFirstName" type="text" class="form-control" placeholder="Admin First Name" required>
                            </div>


                            <div class="form-group">
                                <label>Admin Middle Name</label>
                                <input id="adminMiddleName" name="adminMiddleName" type="text" class="form-control" placeholder="Admin Middle Name" required>
                            </div>


                            <div class="form-group">
                                <label>Admin Last Name</label>
                                <input id="adminLastName" name="adminLastName" type="text" class="form-control" placeholder="Admin Last Name" required>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab6">
                        6
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

        $('#rootwizard').bootstrapWizard({onNext: function(tab, navigation, index) {
            if(index==2) {
                // Make sure we entered the name
                if(!$('#uname').val()) {
                    alert('You must enter university name');
                    $('#uname').focus();
                    return false;
                }else if(!$('#cname').val()) {
                    alert('You must enter college name');
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
                alert('You must enter university name');
                $('#rootwizard').find("a[href*='tab2']").trigger('click');
                $('#uname').focus();
                return false;
            }else if(!$('#cname').val()) {
                alert('You must enter college name');
                $('#rootwizard').find("a[href*='tab2']").trigger('click');
                $('#cname').focus();
                return false;
            }else if(!$('#ulogo').val()) {
                alert('You must enter university logo');
                $('#rootwizard').find("a[href*='tab3']").trigger('click');
                $('#ulogo').focus();
                return false;
            }else if(!$('#adminUsername').val()) {
                alert('You must enter admin username');
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#adminUsername').focus();
                return false;
            }else if(!$('#txtPassword').val()) {
                alert('You must enter admin password');
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#ulogo').focus();
                return false;
            }else if(!$('#txtPassword').val() == $('#txtConfirmPassword').val()) {
                alert('You must re-enter the same new password');
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#txtConfirmPassword').focus();
                return false;
            }else if(!$('#adminemail').val()) {
                alert('You must enter admin email');
                $('#rootwizard').find("a[href*='tab4']").trigger('click');
                $('#adminemail').focus();
                return false;
            }else if(!$('#adminFirstName').val()) {
                alert('You must enter admin first name');
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminFirstName').focus();
                return false;
            }else if(!$('#adminMiddleName').val()) {
                alert('You must enter admin middle name');
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminMiddleName').focus();
                return false;
            }else if(!$('#adminLastName').val()) {
                alert('You must enter admin last name');
                $('#rootwizard').find("a[href*='tab5']").trigger('click');
                $('#adminLastName').focus();
                return false;
            }

            if($('#adminemail').val()) {
                var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                if(!re.test($('#adminemail').val())){
                    alert('You must enter valid admin email');
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
