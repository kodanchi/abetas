<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.database.ASDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/users.js"></script>
<div class="section">
    <div class="container">
        <!--         what is row -->
        <div class="row tim-row">
            <h2 class="text-center">Add User</h2>
            <legend></legend>
            <div class="col-md-8 col-md-offset-2">
                <div id="alert"  class="alert alert-danger fade in"  role="alert" >
                    <strong id="alertt" ></strong>
                </div>

                <form id="UserAddForm" method="post" class="form-horizontal" action="/Update">

                    <div class="form-group">
                        <label class="col-xs-3 control-label">User Type</label>
                        <div class="col-xs-5 selectContainer">
                            <select class="form-control" name="userType"  id="userType" onchange="onUserTypeChng()" >
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
                        <input type="text" class="form-control" placeholder="First Name" name="fname" id="fname" required>
                    </div>

                    <div class="form-group">

                        <label>Middle Name</label>

                        <input type="text" class="form-control" placeholder="Middle Name" name="mname" id="mname" required>

                    </div>

                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control" placeholder="Last Name" name="lname" id="lname" required>
                    </div>

                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" class="form-control" placeholder="Username" name="uname" id="uname" required>
                    </div>

                    <br>

                    <div class="form-group" id="emailDiv">


                    </div>

                    <a type="submit" onclick="onSubmitAddUser()" class="btn btn-default" value="Add">Add</a>

                    <a type="cancel" onclick="cancel()" class="btn btn-default" value="Cancel">Cancel</a>

                </form>


                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
