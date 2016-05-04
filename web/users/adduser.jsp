
<%@ page import="ASDB.U_AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
<script src="/js/bootbox.min.js" type="text/javascript"></script>
<script src="/js/jquery.bsFormAlerts.js" type="text/javascript"></script>
<%
    /**
     * adduser page used to list users,add new user,delete user and update user
     */
    String id = "";
    String type = "";
    if(request.getParameter("id")!= null){
        id = request.getParameter("id");
        type = request.getParameter("type");
    }
    String pageTitle = "Add New User";
    String formURL = "/addUser";
    String submit = "Add";

    String ulvl = "";
    String uOldlvl = "";
    String ufname = "";
    String umname = "";
    String ulname = "";
    String uname = "";
    String uemail = "";
    String evaluatorPro = "";


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
                evaluatorPro = selUsrData.get(5) != null ? selUsrData.get(5) : "";
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
                if(userOldVal != null){
                        ulvl = userOldVal[0];
                        ufname = userOldVal[1];
                        umname = userOldVal[2];
                        ulname = userOldVal[3];
                        uname = userOldVal[4];
                        uemail = userOldVal[5];
                    request.getSession().removeAttribute("userValue");
                }

                out.print("<script type=\"text/javascript\">\n" +
                        "    $(window).load(function(){\n" +
                        "       bootbox.alert(\""+request.getSession().getAttribute("errMsg")+"\")\n" +
                        "    });\n" +
                        "</script>");
                request.getSession().removeAttribute("errMsg");

            }

        %>

        <!-- Here is row -->
        <div class="row">
            <h2 class="text-center"><%=pageTitle%></h2>
            <div class="col-md-8 col-md-offset-2">


                <form id="UserAddForm" method="post" class="form-horizontal" action="<%=formURL%>">

                    <input name="oldLvl" value="<%=uOldlvl%>" hidden/>
                    <input name="id" value="<%=id%>" hidden/>
                    <input name="olduname" value="<%=uname%>" hidden/>
                    <input name="olduemail" value="<%=uemail%>" hidden/>
                    <input name="oldEProgram" value="<%=evaluatorPro%>" hidden/>


                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" class="form-control capit" placeholder="First Name" name="fname" id="fname"
                               value="<%=ufname%>"  required >
                        <span data-alertid="fname"></span>
                    </div>

                    <div class="form-group">

                        <label>Middle Name</label>

                        <input type="text" class="form-control capit" placeholder="Middle Name" name="mname" id="mname" value="<%=umname%>" required>
                        <span data-alertid="mname"></span>
                    </div>

                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control capit" placeholder="Last Name" name="lname" id="lname" value="<%=ulname%>" required>
                        <span data-alertid="lname"></span>
                    </div>

                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" class="form-control" placeholder="Username" name="uname" id="uname" value="<%=uname%>" required>
                        <span data-alertid="uname"></span>
                    </div>

                    <div class="form-group">
                        <label>User Type</label>
                        <div class="selectContainer">
                            <select class="form-control" name="userType"  id="userType" onchange="onUserTypeChng()" >
                                <option value="">User Type</option>
                                <option value="Superuser"  <%if((ulvl.equals("Superuser"))){ %> selected <% }%> >Superuser</option>
                                <option value="Faculty_Member"  <%if((ulvl.equals("Faculty_Member"))){%> selected <%}%> >Faculty_Member</option>
                                <option value="Evaluator"  <%if((ulvl.equals("Evaluator"))){%> selected <%}%> >Evaluator</option>
                            </select>
                            <span data-alertid="lvl"></span>
                        </div>
                    </div>


                    <div class="form-group" id="emailDiv">

                        <label>Email address</label>
                        <input type="email" name="uemail" id="userEmail" class="form-control" placeholder="Email" value="<%=uemail%>" required>
                        <span data-alertid="email"></span>
                    </div>





                    <div class="form-group" id="evaluatorDiv">
                        <label>Evaluator Program</label>
                        <div class="selectContainer">
                            <select class="form-control" name="evaluatorProg"  id="evaluatorProgram">
                                <option >Evaluator Program</option>
                                <%

                                    U_AS_Select dbs = new U_AS_Select();
                                    try {
                                        ArrayList<String> rs = dbs.selectProgramNames();

                                        for (int i=0; i<rs.size();i++){

                                            out.print("<option value=\"");out.print(rs.get(i));out.print("\"");
                                            if (evaluatorPro.equals(rs.get(i))) {
                                                out.print(" selected ");
                                            }

                                            out.print(">");out.print(rs.get(i));out.print("</option>");
                                        }

                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }

                                %>

                            </select>

                            <span data-alertid="evaluatorProgram"></span>
                        </div>
                    </div>


                    <a type="submit" onclick="onSubmitAddUser()" class="btn btn-primary" value="Add"><%=submit%></a>
                    <a type="cancel" href="index.jsp" class="btn btn-default pull-right" value="Cancel">Cancel</a>



                </form>
                <script>

                    $(function(){
                        document.getElementById("emailDiv").style.visibility = "hidden";
                        $('#evaluatorDiv').hide();
                        onUserTypeChng();
                    });

                </script>

                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>

    </div>
