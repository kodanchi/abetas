
<%@ page import="Backup.Backup" %>
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

    <link href="../css/bootstrap.css" rel="stylesheet" />
    <link href="css/ct-paper.css" rel="stylesheet" />
    <link href="css/cus.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/fonts.css" rel="stylesheet">

</head>

<body>

<div class="main">
    <div class="section">
        <div class="container">
            <!--         what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Backup History</h2>
                <legend></legend>
                <div class="col-md-8 col-md-offset-2">
                    <p>Click "Create New Backup" to store a new backup</p>

                    <div class="panel panel-default">
                        <!-- Default panel contents -->

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


                                ServletContext servletContext = request.getSession().getServletContext();
                                String files= servletContext.getRealPath("/")+"backup";
                                File folder = new File(files);
                                File[] listOfFiles = folder.listFiles();


                                for (int i = 0; i < listOfFiles.length; i++)
                                {

                                    if (listOfFiles[i].isFile())
                                    {
                                        BasicFileAttributes view
                                                = Files.getFileAttributeView(listOfFiles[i].toPath(), BasicFileAttributeView.class)
                                                .readAttributes();
                                        files = listOfFiles[i].getName();
                                        if (files.endsWith(".sql") || files.endsWith(".SQL"))
                                        {


                                            out.print("<tr><td>"+files+"</td>\n" +
                                                    "                                <td>"+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                                                    .format(view.creationTime().toMillis())+"</td>\n" +
                                                    "                                <form method=\"post\" action=\"/RestoreDB\">\n" +
                                                    "                                    <td><input hidden name=\"restoreAction\" value="+files+"><button type=\"submit\" class=\"btn btn-warning btn-simple\" data-toggle=\"modal\" data-target=\"#restoreModal\" name=\"restore_Bck\"><i class=\"fa fa-undo fa-2x\"></i></button></td></form>\n"+
                                                    "                                <form method=\"post\" action=\"/BackupDel\">\n" +
                                                    "                                    <td ><input name=\"deleteAction\" hidden value="+files+"> <button type=\"submit\" class=\"btn btn-danger btn-simple\" data-toggle=\"modal\" data-target=\"#deleteModal\"><i class=\"fa fa-trash-o fa-2x\"></i></button></td></form>\n");



                                        }
                                    }
                                }
                            %>
                        </table>
                    </div>
                    <form method="post" action="/Backup">
                    <button class="btn btn-success btn-fill" name="backupCreate">Create New Backup</button>
                    <button class="btn btn-primary pull-right">close</button>
                    </form>

                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>

            <!-- Restore Modal -->
            <div class="modal fade" id="restoreModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Backup Restore</h4>
                        </div>
                        <div class="modal-body">
                            If you choose to restore this backup, you will lose any data entered after that time and will not be able to access them.
                        </div>
                        <div class="modal-footer">
                            <div class="left-side">
                                <a href="#" type="button" class="btn btn-default btn-simple">OK</a>
                            </div>
                            <div class="divider"></div>
                            <div class="right-side">
                                <a href="#" type="button" class="btn btn-default btn-simple" data-dismiss="modal">Cancel</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--  Modal -->
            <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabelbackup">Delete Backup</h4>
                        </div>
                        <div class="modal-body">
                            If you choose to delete this backup, you will not be able to access it again.
                        </div>
                        <div class="modal-footer">
                            <div class="left-side">
                                <a href="#" type="button" class="btn btn-default btn-simple">OK</a>
                            </div>
                            <div class="divider"></div>
                            <div class="right-side">
                                <a href="#" type="button" class="btn btn-default btn-simple" data-dismiss="modal">Cancel</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End of modals -->

        </div>
    </div>
</div>


</body>

<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<!-- <script src="code.jquery.com/jquery-1.10.2.js"></script> -->
<script src="../js/bootstrap.js" type="text/javascript"></script>

<!--  Plugins -->
<script src="js/ct-paper-checkbox.js"></script>
<script src="js/ct-paper-radio.js"></script>
<script src="js/bootstrap-select.js"></script>
<script src="js/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

<script src="js/ct-paper.js"></script>
<script>
    $("#slider-range").slider({
        range: true,
        min: 0,
        max: 500,
        values: [75, 300],
    });
    $("#slider-default").slider({
        value: 70,
        orientation: "horizontal",
        range: "min",
        animate: true
    });
    $('.btn-tooltip').tooltip('show');
    $('.radio').on('toggle', function () {});
</script>

</html>