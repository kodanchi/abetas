
<%@ page import="java.io.File" %>
<%@ page import="java.nio.file.*" %>
<%@ page import="java.nio.file.attribute.BasicFileAttributes" %>
<%@ page import="java.nio.file.attribute.BasicFileAttributeView" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Backup</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/flat-ui.css" rel="stylesheet" />
    <link href="css/cus.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/fonts.css" rel="stylesheet">

    <script src="js/jquery-2.2.0.min.js" type="text/javascript"></script>

</head>

<body>
<div id="page">
<div id="header">
    <jsp:include page="/Header.jsp"/>
</div>


    <%

        /**
         * backup page used to list backup files lists
         */
        if(request.getSession().getAttribute("Msg") != null){


            out.print("<script type=\"text/javascript\">\n" +
                    "    $(window).load(function(){\n" +
                    "       bootbox.alert(\""+request.getSession().getAttribute("Msg")+"\")\n" +
                    "    });\n" +
                    "</script>");
            request.getSession().removeAttribute("Msg");


        }

    %>


<div class="main">
    <div class="section">
        <div class="container">

            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Backup History</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Click "Create New Backup" to store a new backup</p>

                    <div class="row">
                        <form method="post" action="/setBackupTime">
                            <div class="form-group">
                                <%
                                    ServletContext servletContext = request.getSession().getServletContext();

                                    String backupTime = (String) servletContext.getAttribute("backupTime");
                                %>
                                <select class=" select select-default mbl" name="timeList">
                                    <option value="daily" <%if(backupTime.equals("daily"))out.print(" selected ");%>>Daily</option>
                                    <option value="weekly" <%if(backupTime.equals("weekly"))out.print(" selected ");%>>Weekly</option>
                                    <option value="biweekly" <%if(backupTime.equals("biweekly"))out.print(" selected ");%>>Biweekly</option>
                                    <option value="monthly" <%if(backupTime.equals("monthly"))out.print(" selected ");%>>Monthly</option>
                                </select>
                                <input class="btn btn-success" type="submit" value="Set">
                            </div>

                        </form>
                    </div>

                        <!-- Table -->
                        <table class="table table-striped table-bordered text-center">

                            <tr>
                                <th class="text-center">Name</th>
                                <th class="text-center">Date & time</th>
                                <th class="text-center tenPer">Restore</th>
                                <th class="text-center tenPer">Delete</th>
                            </tr>

                            <%

                            /**
                                 * the location of retrived the backup files
                             */


                                int pageNum = 1;
                                int recordsPerPage = 10;
                                int noOfPages = 0;
                                if(request.getParameter("p")!= null){
                                    pageNum = Integer.valueOf(request.getParameter("p"));
                                }

                                String file = servletContext.getRealPath("/")+"backup";
                                File folder = new File(file);
                                File[] listOfFiles = folder.listFiles();

                                int recordLimit = (pageNum-1)*recordsPerPage+recordsPerPage < listOfFiles.length ?
                                        (pageNum-1)*recordsPerPage+recordsPerPage : listOfFiles.length;

                                for (int i = (pageNum-1)*recordsPerPage; i < recordLimit; i++)
                                {

                                    if (listOfFiles[i].isFile())
                                    {
                                        BasicFileAttributes view
                                                = Files.getFileAttributeView(listOfFiles[i].toPath(), BasicFileAttributeView.class)
                                                .readAttributes();
                                        file = listOfFiles[i].getName();
                                        if (file.endsWith(".sql") || file.endsWith(".SQL"))
                                        {


                                            out.print("<tr><td>"+ file +"</td>\n" +
                                                    "                                <td>"+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                                                    .format(view.creationTime().toMillis())+"</td>\n" +
                                                    "                                <form method=\"post\" class=\"RestoreForm\" action=\"/RestoreDB\">\n" +
                                                    "                                    <td><input hidden name=\"restoreAction\" value="+ file +"><button  type=\"submit\" title=\"Edit\" class=\"btn btn-link btn-Y \"><i class=\"fui-new icon30\"></i></button></td></form>\n"+
                                                    "                                <form method=\"post\" class=\"delForm\" action=\"/BackupDel\">\n" +
                                                    "                                    <td ><input name=\"deleteAction\" hidden value="+ file +"><button  type=\"submit\" title=\"Delete\" class=\"btn btn-link btn-T \"><i class=\"fui-trash icon30\"></i></button></td>" +
                                                    "</tr>" +
                                                    "</form>\n");



                                        }
                                    }
                                }



                                noOfPages = (int) Math.ceil(listOfFiles.length * 1.0 / recordsPerPage);
                            %>
                        </table>
                    <ul class="pagination">

                        <li class="previous pull-left" >
                            <%=pageNum!=1?"<a href=\"backup.jsp?p="+(pageNum-1)+"\">Previous</a>":""%>
                        </li>
                        <%
                            for (int i=1;i<=noOfPages;i++){
                                out.println("                   <li><a \n" );


                                if(pageNum == i) {
                                    out.print(" class=\"active\" ");
                                }else {
                                    out.println("  href=\"backup.jsp?p=" + i+"\"" );
                                }
                                out.print(">"+i +"</a></li>");
                            }
                        %>

                        <li class="next pull-right">
                            <%=pageNum!=noOfPages?"<a href=\"backup.jsp?p="+(pageNum+1)+"\">Next</a>":""%>
                        </li>
                    </ul>

                    <form method="post" action="/Backup">
                    <button class="btn btn-primary pull-left" name="backupCreate">Create New Backup</button>
                    </form>
                    <a class="btn btn-primary pull-right" href="index.jsp">Back</a>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

        </div>
    </div>
</div>

    <script>
        $(function(){
            $('form.RestoreForm').on('submit',function (e){
                e.preventDefault();
                var form = $(this);
                bootbox.confirm('Are you sure want to Restore the database ?', function(result) {
                    if(result == true){
                        //var form = $(this).parents('form:first');
                        $('form.RestoreForm').off('submit');
                        form.submit();
                        //alert('fdf');
                    }
                });

            });
        });
    </script>

<div id="footer">
    <jsp:include page="/Footer.jsp"/>
</div>
</div>
<div id="loading" ></div>
</body>

<!-- <script src="code.jquery.com/jquery-1.10.2.js"></script> -->
<script src="js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->
<script src="js/bootstrap-select.js"></script>
<script src="js/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

<script src="js/flat-ui.min.js"></script>
<script src="js/flat-ui-select.js"></script>
<script src="js/bootbox.min.js"></script>
<script src="js/del-form-listener.js"></script>

</html>