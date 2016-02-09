<%@ page import="ASDB.AS_Select" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Abdullah
  Date: 2/1/2016
  Time: 4:10 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Program Performance Indicator</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">

                    <form id="outerform" action="/cycle/index.jsp?page=addPI" method="post"></form>
                    <form id="innerformU" action="/cycle/index.jsp" method="post"></form>
                    <form id="innerformD" action="/DeletePI" method="post"></form>

                    <label>Choose a program: </label>
                        <select class="form-control" name="programID" id="pName" onchange="onProgramChng()" form="outerform" required>
                            <%
                                AS_Select select = new AS_Select();
                                ArrayList<Integer> pid = new ArrayList<Integer>();
                                try {
                                    ArrayList<ArrayList<String>> rs = select.selectAllPrograms();
                                    ArrayList<String> rsRow;


                                    for (int i=0; i<rs.size();i++) {
                                        rsRow = rs.get(i);

                                        pid.add(Integer.valueOf(rsRow.get(0)));
                                        //System.out.println("pid : "+pid.get(i));
                                        out.print("<option value="+rsRow.get(0)+">"+rsRow.get(1)+"</option>");
                                    }
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            %>
                        </select>

                        <script>
                    $(document).ready(function(){
                        hideAllTables();
                        onProgramChng();


                    });
                    function onProgramChng()
                    {
                        var ddl = document.getElementById("pName");
                        var selectedValue = ddl.options[ddl.selectedIndex].value;
                        hideAllTables();
                        document.getElementById(selectedValue).style.display = 'table';;
                    }
                    function hideAllTables (){
                        var tables = document.getElementsByTagName("table");
                        for(var i =0;i<tables.length;i++){
                            tables[i].style.display = 'none';;
                        }
                    }
                </script>

                    <script>
                        $(document).ready(function(){
                            hideAllTables();
                            onProgramChng();


                        });
                        function getProgram()
                        {
                            var ddl = document.getElementById("pName");
                            var selectedValue = ddl.options[ddl.selectedIndex].value;
                            document.getElementById("progID").innerHTML = selectedValue;
                        }
                    </script>

                        <br>

                        <p>Click "Add" to enter program performance indicator</p>

                        <div class="panel panel-default">
                            <!-- Default panel contents -->

                            <!-- Table -->

                                <%

                                    AS_Select aselect = new AS_Select();

                                    for (int k=0; k < pid.size();k++){
                                        try {
                                            ArrayList<ArrayList<String>> rs = aselect.selectPerformanceIndicators(pid.get(k));
                                            ArrayList<String> rsRow ;

                                            out.print("<table id=\""+pid.get(k)+"\" class=\"table\">\n" +
                                                    "                                <tr>\n" +
                                                    "                                    <th>Label</th>\n" +
                                                    "                                    <th>Performance Indicator</th>\n" +
                                                    "                                    <th>Edit</th>\n" +
                                                    "                                    <th>Delete</th>\n" +
                                                    "                                </tr>" );
                                            for (int i=0; i<rs.size();i++){
                                                rsRow = new ArrayList<String>();
                                                rsRow = rs.get(i);
                                                out.print("<tr>");
                                                for (int j=0; j<rsRow.size();j++) {
                                                    System.out.println("id: "+rsRow.get(0)+" name: "+rsRow.get(1));
                                                    out.print("<td>"+rsRow.get(j)+"</td>");

                                                }
                                                out.print(
                                                        "<td>" +
                                                        "                            <input name=\"page\" value=\"updatePI\" form=\"innerformU\" hidden />\n" +
                                                        "                            <input name=\"PILabel\" value=\""+rsRow.get(0)+"\" form=\"innerformU\" hidden />\n" +
                                                        "                            <input name=\"PIValue\" value=\""+rsRow.get(1)+"\" form=\"innerformU\" hidden />\n" +
                                                        "                            <input name=\"progID\" id=\"progID\" onchange=\"getProgram()\" form=\"innerformU\" hidden />\n" +
                                                        "                            <button  type=\"submit\" form=\"innerformU\" title=\"Edit\" class=\"btn btn-warning btn-simple\"><i class=\"fa fa-pencil fa-2x \"></i></button>\n" +
                                                        "                               </td>" +
                                                        "                            <input name=\"page\" id=\"page\" value=\"delete\" form=\"innerformD\" hidden />\n" +
                                                        "                            <input name=\"PILabel\" value=\""+rsRow.get(0)+"\" form=\"innerformD\" hidden />\n" +
                                                        "                               <td>" +
                                                        "                            <button  type=\"submit\" form=\"innerformD\" title=\"Delete\" class=\"btn btn-danger btn-simple\"><i class=\"fa fa-trash-o fa-2x \"></i></button>\n" +
                                                        "                               </td>"+
                                                        "</tr>"
                                                        );
                                            }
                                            out.print("</table>");

                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                %>

                        </div>
                        <button class="btn btn-success" type="submit" form="outerform">Add</button>
                        <button class="btn btn-success">Cancel</button>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>