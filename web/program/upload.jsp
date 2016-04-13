
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * used to display objectives/outcomes/courses upload page.
     */

    String id ="";
    String name ="";
    String dataType = "";
    if(request.getParameter("data") != null && request.getParameter("id") != null){
        id = request.getParameter("id");
        name = request.getParameter("name");
        dataType = request.getParameter("data");

    }
%>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>
    <div class="container">
        <!-- Here is row -->
        <div class="row">
            <h2 class="text-center">User Management</h2>
            <div class="col-md-8 col-md-offset-2">
                <p>Please confirm that the imported data are as intended, you can click re-upload to discard the current imported file and import it again.</p>

                    <!-- Table -->
                    <table class="table table-hover table-striped table-bordered text-center">
                        <tr>
                            <%
                                if(dataType.equals("obj")){
                                    out.print("<th>Objective</th>");
                                }else if (dataType.equals("outcomes")){
                                    out.print("<th>Outcomes</th>");
                                }else if(dataType.equals("course")){

                                }
                            %>
                        </tr>

<%

    Object dataObj = request.getSession().getAttribute("sheetData");


    ArrayList<ArrayList<String>> dataArr = (ArrayList<ArrayList<String>>) dataObj;
    ArrayList<String> dataRow;

    for(int i=0;i<dataArr.size();i++){
        dataRow = dataArr.get(i);
        out.print("<tr>");
        for(int j=0;j<dataRow.size();j++){
            out.print("<td>");
            out.print(dataRow.get(j));
            out.print("</td>");
        }
        out.print("</tr>");
    }


%>
                    </table>

                <form method="post" action="/upload/program" >
                    <a class="btn btn-primary" href="/program/index.jsp?name=<%=name%>&id=<%=id%>&page=import&data=<%=dataType%>">re-upload</a>
                    <input name="data-type" value="<%=dataType%>" hidden>
                    <input name="name" value="<%=name%>" hidden>
                    <input name="id" value="<%=id%>" hidden>
                    <button class="btn btn-primary"  type="submit">Upload</button>
                    <a href="/program/index.jsp?name=<%=name%>&id=<%=id%>&page=ObjList" class="btn btn-primary">Cancel</a>
                </form>




                <!-- End of col -->
            </div>

            <!-- End of row -->
        </div>

    <!-- End of container -->
    </div>

