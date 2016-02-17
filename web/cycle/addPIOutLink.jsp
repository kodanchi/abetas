<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/8/2016
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<%
    String id = "";
    String Termid = "";
    if(request.getSession().getAttribute("id") != null && request.getSession().getAttribute("Termid") != null){
        id  = (String) request.getSession().getAttribute("id");
        Termid  = (String) request.getSession().getAttribute("Termid");
        out.println("id is : "+id);
        out.print("Termid is : "+Termid);
    }

%>

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Performance Indicators</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Click "Add" to enter Performance Indicator</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">
                            <tr>
                                <th class="text-center">PID</th>
                                <th class="text-center">Outcome ID</th>
                                <th class="text-center">Edit</th>
                                <th class="text-center">Delete</th>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>A</td>
                                <td><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></td>
                                <td><a class="btn btn-danger btn-simple" href="#"><i class="fa fa-trash-o fa-2x"></i></a></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>B</td>
                                <td><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></td>
                                <td><a class="btn btn-danger btn-simple" href="#"><i class="fa fa-trash-o fa-2x"></i></a></td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>C</td>
                                <td><a class="btn btn-warning btn-simple" href="#"><i class="fa fa-pencil fa-2x"></i></a></td>
                                <td><a class="btn btn-danger btn-simple" href="#"><i class="fa fa-trash-o fa-2x"></i></a></td>
                            </tr>
                        </table>
                    </div>
                    <button class="btn btn-success btn-fill">Add</button>
                    <button class="btn btn-primary">Home Page</button>
                    <button class="btn btn-primary pull-right">Finish</button>


                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>


        </div>
    </div>
</div>