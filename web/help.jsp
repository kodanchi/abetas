
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>ABETS - Backup Management</title>

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


    %>


<div class="main">
    <div class="section">
        <div class="container">

            <!-- Here is row -->
            <div class="row">
                <h2 class="text-center">Help</h2>
                <h6 class="text-center">User Manual</h6>
                <legend></legend>
                <div class="col-md-12">

                    <div class="row">

                        <embed src="<%
                        if(request.getServerName().equals("localhost")){
                            out.print(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());}
                        else {
                            out.print(request.getScheme()+"://"+request.getServerName());
                        }
                        %>/UserManual.pdf" style="width:100%; height:700px;" type='application/pdf'>

                    </div>




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
                        $('form.RestoreForm').off('submit');
                        form.submit();
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