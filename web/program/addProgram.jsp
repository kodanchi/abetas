<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Abuaqel
  Date: 2/1/2016
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-2.2.0.min.js" type="text/javascript"></script>

<!doctype html>
<div class="main">
    <div class="section">
        <div class="container" id="space">
            <!-- what is row -->
            <div class="row tim-row">
                <h2 class="text-center">Add Program</h2>
                <legend></legend>
                <div class="col-md-10 col-md-offset-1">
                    <p>You need to enter the name and the misson of the program:</p>

                    <form name="myform" action="#" method="post">

                        <div class="form-group">

                            <label>Program Name</label>

                            <input type="text" class="form-control" placeholder="Program Name" required>

                        </div>

                        <div class="form-group">

                            <label>Mission Statement</label>

                            <textarea class="form-control" rows="4" cols="50" placeholder="Mission Statement" required></textarea>

                        </div>

                        <br>
                        <button type="submit" class="btn btn-success btn-fill">Add</button>
                        <button type="button" class="btn btn-primary">Cancel</button>

                    </form>
                    <!-- End of col -->
                </div>

                <!-- End of row -->
            </div>
            <!-- Modal Bodies come here -->
        </div>
    </div>
</div>
</html>
