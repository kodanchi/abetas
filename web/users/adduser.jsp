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
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<%
    String id = request.getParameter("id");
    String type = request.getParameter("type");
    String pageTitle = "Add User";
    String formURL = "/Update";
    String submit = "Add";

    String ulvl = "";
    String ufname = "";
    String umname = "";
    String ulname = "";
    String uname = "";
    String uemail = "";


    if(id != null && type != null){
        if(type.equals("superuser") && (!id.equals("1"))){
            ASDB db = new ASDB();
            ArrayList<String> selUsrData = db.selectSuperuser(Integer.parseInt(id));

            if(selUsrData != null){
                ulvl = "superuser";
                ufname = selUsrData.get(1);
                umname = selUsrData.get(2);
                ulname = selUsrData.get(3);
                uname = selUsrData.get(4);
                uemail = selUsrData.get(5);
                pageTitle = "Update User";
                submit = "Update";
            }
        }else if(type.equals("faculty")){
            ASDB db = new ASDB();
            ArrayList<String> selUsrData = db.selectFaculty(Integer.parseInt(id));

            if(selUsrData != null){
                ulvl = "superuser";
                ufname = selUsrData.get(1);
                umname = selUsrData.get(2);
                ulname = selUsrData.get(3);
                uname = selUsrData.get(4);
                uemail = selUsrData.get(5);
                pageTitle = "Update User";
                submit = "Update";
            }
        }
    }
%>
<script src="/js/users.js"></script>
<div class="section">
    <div class="container">
        <!--         what is row -->
        <div class="row tim-row">
            <h2 class="text-center"><%=pageTitle%></h2>
            <legend></legend>
            <div class="col-md-8 col-md-offset-2">
                <div id="alert"  class="alert alert-danger fade in"  role="alert" >
                    <strong id="alertt" ></strong>
                </div>

                <form id="UserAddForm" method="post" class="form-horizontal" action="<%=formURL%>">

                    <div class="form-group">
                        <label class="col-xs-3 control-label">User Type</label>
                        <div class="col-xs-5 selectContainer">
                            <select class="form-control" name="userType"  id="userType" onchange="onUserTypeChng()" >
                                <option value="">User Type</option>
                                <option value="Superuser"  <%if((ulvl.equals("superuser"))){%> selected <%}%> >Superuser</option>
                                <option value="Faculty_Member"  <%if((ulvl.equals("faculty"))){%> selected <%}%> >Faculty_Member</option>
                                <option value="Evaluator"  <%if((ulvl.equals("Evaluator"))){%> selected <%}%> >Evaluator</option>
                            </select>
                        </div>
                    </div>

                    <br>
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" class="form-control" placeholder="First Name" name="fname" id="fname" value="<%=ufname%>" required>
                    </div>

                    <div class="form-group">

                        <label>Middle Name</label>

                        <input type="text" class="form-control" placeholder="Middle Name" name="mname" id="mname" value="<%=umname%>" required>

                    </div>

                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control" placeholder="Last Name" name="lname" id="lname" value="<%=ulname%>" required>
                    </div>

                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" class="form-control" placeholder="Username" name="uname" id="uname" value="<%=uname%>" required>
                    </div>

                    <br>

                    <div class="form-group" id="emailDiv">

                        <label>Email address</label>
                        <input type="email" name="uemail" id="userEmail" class="form-control" placeholder="Email" value="<%=uemail%>" required>
                    </div>

                    <a type="submit" onclick="onSubmitAddUser()" class="btn btn-default" value="Add"><%=submit%></a>

                    <a type="cancel" href="index.jsp" class="btn btn-default" value="Cancel">Cancel</a>

                </form>
                <script>
                    $(this).ready(function(){
                        document.getElementById("alert").style.visibility = "hidden";
                        document.getElementById("emailDiv").style.visibility = "hidden";
                        onUserTypeChng();
                    })

                </script>

                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>


        <!-- Modal Bodies come here -->
    </div>
</div>
