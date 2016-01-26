<%--
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
        <form>
            <div class="page-header">
                <h1>Wizard With Form Validation</h1>
            </div>

            <div id="rootwizard">
                <div class="navbar">
                    <div class="navbar-inner">
                        <div class="container">
                            <ul>
                                <li><a href="#tab1" data-toggle="tab">Welcome</a></li>
                                <li><a href="#tab2" data-toggle="tab">Second</a></li>
                                <li><a href="#tab3" data-toggle="tab">Third</a></li>
                                <li><a href="#tab4" data-toggle="tab">Fourth</a></li>
                                <li><a href="#tab5" data-toggle="tab">Fifth</a></li>
                                <li><a href="#tab6" data-toggle="tab">Sixth</a></li>
                                <li><a href="#tab7" data-toggle="tab">Seventh</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div id="bar" class="progress">
                    <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
                </div>
                <div class="tab-content">
                    <div class="tab-pane" id="tab1">
                        <div id="welcome" class="col-md-6 col-md-offset-3 jumbotron" style="background-color:#ecf0f1;">
                            <h2>Welcome to ABETAS</h2>
                            <p>This is the setup wizard of the system, this wizard will require set of some information. use the navigator ubove to move around, or you can fill them later by going to system setting area.</p>

                        </div>
                    </div>
                    <div class="tab-pane" id="tab2">
                        <div class="form-group">
                            <label for="uniName">University Name</label>
                            <input type="text" class="form-control" id="uniName" placeholder="University Name" size="25">
                        </div>
                        <div class="form-group">
                            <label for="collegeName">College Name</label>
                            <input type="password" class="form-control" id="collegeName" placeholder="College Name">
                        </div>
                    </div>
                    <div class="tab-pane" id="tab3">
                        <div class="form-group">
                            <label for="dfn">Dean First Name</label>
                            <input type="text" class="form-control" id="dfn" placeholder="Dean First Name" size="25">
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
                    </div>
                    <div class="tab-pane" id="tab4">
                        4
                    </div>
                    <div class="tab-pane" id="tab5">
                        5
                    </div>
                    <div class="tab-pane" id="tab6">
                        6
                    </div>
                    <div class="tab-pane" id="tab7">
                        7
                    </div>
                    <ul class="pager wizard">
                        <li class="previous first" style="display:none;"><a href="#">First</a></li>
                        <li class="previous"><a href="#">Previous</a></li>
                        <li class="next last" style="display:none;"><a href="#">Last</a></li>
                        <li class="next"><a href="#">Next</a></li>
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
                if(!$('#UniName').val()) {
                    alert('You must enter university name');
                    $('#UniName').focus();
                    return false;
                }else if(!$('#collegeName').val()) {
                    alert('You must enter college name');
                    $('#collegeName').focus();
                    return false;
                }
            }

            // Set the name for the next tab
            $('#tab3').html('Please Enter dean\'s of ' + $('#collegeName').val() + ' full name');

        }, onTabShow: function(tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index+1;
            var $percent = ($current/$total) * 100;
            $('#rootwizard .progress-bar').css({width:$percent+'%'});
        }});
        window.prettyPrint && prettyPrint()
    });
</script>
</body>
</html>
