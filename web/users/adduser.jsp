<%--
  Created by IntelliJ IDEA.
  User: Mojahed
  Date: 1/29/2016
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ASDB.U_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<%

    String id = "";
    String type = "";
    if(request.getParameter("id")!= null){
        id = request.getParameter("id");
        type = request.getParameter("type");
    }
    String pageTitle = "Add User";
    String formURL = "/addUser";
    String submit = "Add";

    String ulvl = "";
    String uOldlvl = "";
    String ufname = "";
    String umname = "";
    String ulname = "";
    String uname = "";
    String uemail = "";


    if(id != null && type != null){
        if(type.equals("Superuser") && (!id.equals("1"))){
            uOldlvl = "Superuser";
            U_AS_Select db = new U_AS_Select();
            ArrayList<String> selUsrData = db.selectSuperuser(Integer.parseInt(id));

            if(selUsrData != null){
                ulvl = "Superuser";
                ufname = selUsrData.get(1);
                umname = selUsrData.get(2);
                ulname = selUsrData.get(3);
                uname = selUsrData.get(4);
                uemail = selUsrData.get(5);
                pageTitle = "Update User";
                submit = "Update";
                formURL = "/updateUser";
            }
        }else if(type.equals("Faculty_Member")){
            uOldlvl = "Faculty_Member";
            U_AS_Select db = new U_AS_Select();
            ArrayList<String> selUsrData = db.selectFaculty(Integer.parseInt(id));

            if(selUsrData != null){
                ulvl = "Faculty_Member";
                ufname = selUsrData.get(1);
                umname = selUsrData.get(2);
                ulname = selUsrData.get(3);
                uname = selUsrData.get(4);
                uemail = selUsrData.get(5);
                pageTitle = "Update User";
                submit = "Update";
                formURL = "/updateUser";
            }
        } else if(type.equals("Evaluator")){
            uOldlvl = "Evaluator";
            U_AS_Select db = new U_AS_Select();
            ArrayList<String> selUsrData = db.selectEvaluator(Integer.parseInt(id));

            if(selUsrData != null){
                ulvl = "Evaluator";
                ufname = selUsrData.get(1);
                umname = selUsrData.get(2);
                ulname = selUsrData.get(3);
                uname = selUsrData.get(4);
                //uemail = selUsrData.get(5);
                pageTitle = "Update User";
                submit = "Update";
                formURL = "/updateUser";
            }
        }
    }
%>
<script src="/js/users.js"></script>
    <div class="container">

        <%

            if(request.getSession().getAttribute("errMsg") != null){

                    String[] userOldVal = (request.getSession().getAttribute("userValue") != null ? (String[]) request.getSession().getAttribute("userValue") : null);
                    System.out.print("arry of user data : "+ userOldVal);
                if(userOldVal != null){
                        ulvl = userOldVal[0];
                        ufname = userOldVal[1];
                        umname = userOldVal[2];
                        ulname = userOldVal[3];
                        uname = userOldVal[4];
                        uemail = userOldVal[5];
                    request.getSession().removeAttribute("userValue");
                    }

                        /*out.print("<div id=\"alert\"  class=\"alert alert-danger fade in\"  role=\"alert\" >\n" +
                                "                        <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                                "                            <span aria-hidden=\"true\">&times;</span>\n" +
                                "                        </button>\n" +
                                "                        <strong id=\"alertt\" >\n" +
                                "                            " + request.getParameter("err")+
                                "                        </strong>\n" +
                                "                    </div>");*/

                out.print("<script type=\"text/javascript\">\n" +
                        "    $(window).load(function(){\n" +
                        "        $('#myModal').modal('show');\n" +
                        "    });\n" +
                        "</script>" +
                        "<!-- Modal -->\n" +
                        "                    <div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                        "                        <div class=\"modal-dialog\">\n" +
                        "                            <div class=\"modal-content\">\n" +
                        "                                <div class=\"modal-header\">\n" +
                        "                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n" +
                        "                                    <h4 class=\"modal-title\" id=\"myModalLabel\">INFO</h4>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"modal-body\">\n");
                out.print(request.getSession().getAttribute("errMsg"));
                request.getSession().removeAttribute("errMsg");

                out.print("                                </div>\n" +
                        "                                <div class=\"modal-footer\">\n" +
                        "\n" +
                        "                                    <div class=\"text-center\">\n" +
                        "                                        <a type=\"button\"  data-dismiss=\"modal\"  class=\"btn btn-default btn-simple\">OK</a>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                    </div>");


            }

        %>

        <!-- Here is row -->
        <div class="row">
            <h2 class="text-center"><%=pageTitle%></h2>
            <div class="col-md-8 col-md-offset-2">
                <div id="alert"  class="alert alert-danger fade in"  role="alert" >
                    <strong id="alertt" ></strong>
                </div>

                <form id="UserAddForm" method="post" class="form-horizontal" action="<%=formURL%>">

                    <input name="oldLvl" value="<%=uOldlvl%>" hidden/>
                    <input name="id" value="<%=id%>" hidden/>
                    <input name="olduname" value="<%=uname%>" hidden/>
                    <input name="olduemail" value="<%=uemail%>" hidden/>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">User Type</label>
                        <div class="col-xs-5 selectContainer">
                            <select class="form-control" name="userType"  id="userType" onchange="onUserTypeChng()" >
                                <option value="">User Type</option>
                                <option value="Superuser"  <%if((ulvl.equals("Superuser"))){ %> selected <% }%> >Superuser</option>
                                <option value="Faculty_Member"  <%if((ulvl.equals("Faculty_Member"))){%> selected <%}%> >Faculty_Member</option>
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

                    <div class="form-group" id="emailDiv">

                        <label>Email address</label>
                        <input type="email" name="uemail" id="userEmail" class="form-control" placeholder="Email" value="<%=uemail%>" required>
                    </div>

                    <button type="submit" onclick="onSubmitAddUser()" class="btn btn-primary" value="Add"><%=submit%></button>
                    <button type="cancel" href="index.jsp" class="btn btn-default pull-right" value="Cancel">Cancel</button>



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

    </div>
