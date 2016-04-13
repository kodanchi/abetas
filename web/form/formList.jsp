<%@ page import="FDB.F_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="application/javascript"></script>
<script src="/js/bootstrap.min.js" type="application/javascript"></script>
<script src="/js/bootbox.min.js" type="application/javascript"></script>


/**
* used to display form list page form faculty member.
*/


<div class="row">
    <!-- Here is row -->
    <div class="col-md-3 col-md-offset-3">
        <div class="list-group">
            <h4>Uncompleted forms</h4>
            <%

                if(request.getSession().getAttribute("Msg")!= null){
                    out.print("<script>");
                    out.print("\n" +
                            "bootbox.alert(\""+request.getSession().getAttribute("Msg")+"\");");

                    out.print("</script>");

                    request.getSession().removeAttribute("Msg");
                }
            %>
            <%
                F_Select sdb = new F_Select();
                Integer fid = Integer.parseInt((String) request.getSession().getAttribute("userId"));

                if(fid != null){
                    ArrayList<ArrayList<String>> formativeFormsList = sdb.selectFacultyUnsubmittedFForms(fid);
                    ArrayList<String> frsRow;

                    for (int i=0; i<formativeFormsList.size(); i++){
                        frsRow = formativeFormsList.get(i);
                        out.print("<a href=\"index.jsp?page=fillForm&type=formative&id="+frsRow.get(0)+"\" class=\"list-group-item\">"+
                                frsRow.get(2)+" </br> (Course : "+frsRow.get(1)+") </br>");
                        out.print("<div class=\"pull-right\"  >Formative</div></br>");
                        out.print("</a><br>");
                    }


                    ArrayList<ArrayList<String>> summativeFormsList = sdb.selectFacultyUnsubmittedSForms(fid);
                    ArrayList<String> srsRow;

                    for (int i=0; i<summativeFormsList.size(); i++){
                        srsRow = summativeFormsList.get(i);
                        out.print("<a href=\"index.jsp?page=fillForm&type=summative&id="+srsRow.get(0)+"\" class=\"list-group-item\">"+
                                srsRow.get(2)+" </br> (Course : "+srsRow.get(1)+")</br>");
                        out.print("<div class=\"pull-right\" >Summative</div></br>");
                        out.print("</a><br>");
                    }
                }
            %>
        </div>

    </div>
    <div class="col-md-3">
        <div class="list-group">
            <h4>Completed forms</h4>


            <%

                F_Select dbs = new F_Select();
                ArrayList<String> fInfo = new ArrayList<String>();
                String name = "";
                String userName = "";

                try {
                    fInfo = dbs.selectFaculty(fid);
                    for (int i=0;i<fInfo.size();i++){
                        name = fInfo.get(1)+" "+fInfo.get(2)+" "+fInfo.get(3);

                        userName = fInfo.get(4);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                if(fid != null){
                    ArrayList<ArrayList<String>> formativeFormsList = sdb.selectFacultySubmittedFForms(fid);
                    ArrayList<String> frsRow;

                    for (int i=0; i<formativeFormsList.size(); i++){
                        frsRow = formativeFormsList.get(i);
                        out.print("<a href=\"index.jsp?page=showForm&type=formative&id="+frsRow.get(0)+"\" class=\"list-group-item\">"+
                                frsRow.get(2)+" </br> (Course : "+frsRow.get(1)+")</br>");
                        out.print("<div class=\"pull-right\">Formative</div></br>");
                        out.print("<form method=\"post\" action=\"/Emailreq\">");
                        out.print("<input type=\"hidden\" name=\"Fullname\" value=\""); out.print(name); out.print("\" hidden />");
                        out.print("<input type=\"hidden\" name=\"Username\" value=\""); out.print(userName); out.print("\" hidden />");
                        out.print("<input type=\"hidden\" name=\"course\" value=\""); out.print(frsRow.get(1)); out.print("\" hidden />");
                        out.print("<input type=\"hidden\" name=\"pi\" value=\""); out.print(frsRow.get(2)); out.print("\" hidden />");
                        out.print("<input type=\"hidden\" name=\"type\" value=\""); out.print("Formative"); out.print("\" hidden />");
                        out.print("<button  type=\"submit\" title=\"Send Request\" class=\"btn btn-primary pull-left \">Send Request</button>\n" +
                                " </form>");
                        out.print("</br></br></a>");
                    }


                    ArrayList<ArrayList<String>> summativeFormsList = sdb.selectFacultySubmittedSForms(fid);
                    ArrayList<String> srsRow;

                    for (int i=0; i<summativeFormsList.size(); i++){
                        srsRow = summativeFormsList.get(i);
                        out.print("<a href=\"index.jsp?page=showForm&type=summative&id="+srsRow.get(0)+"\" class=\"list-group-item\">"+
                                srsRow.get(2)+" </br> (Course : "+srsRow.get(1)+")</br>");
                        out.print("<div class=\"pull-right\">Summative</div></br>");
                        out.print("<form method=\"post\" action=\"/Emailreq\">");
                        out.print("<input type=\"hidden\" name=\"Fullname\" value=\""); out.print(name); out.print("\" hidden />");
                        out.print("<input type=\"hidden\" name=\"Username\" value=\""); out.print(userName); out.print("\" hidden />");
                        out.print("<input type=\"hidden\" name=\"course\" value=\""); out.print(srsRow.get(1)); out.print("\" hidden />");
                        out.print("<input type=\"hidden\" name=\"pi\" value=\""); out.print(srsRow.get(2)); out.print("\" hidden />");
                        out.print("<input type=\"hidden\" name=\"type\" value=\""); out.print("Summative"); out.print("\" hidden />");
                        out.print("<button  type=\"submit\" title=\"Send Request\" class=\"btn btn-primary pull-left \">Send Request</button>\n" +
                                " </form>");
                        out.print("</br></br></a>");
                    }



                }
            %>
        </div>

    </div>

</div>
