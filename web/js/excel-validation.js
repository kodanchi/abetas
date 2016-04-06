/**
 * Created by Mojahed on 4/6/2016.
 */
$(function(){
    $('#import').submit(function(){

        var excelInput = document.getElementById("excelInput");


        $(document).trigger("clear-alert-id.obj");

        if(!$('#excelInput').val()) {
            $(document).trigger("clear-alert-id.exceli");
            $(document).trigger("set-alert-id-exceli", [
                {
                    message: "Select excel file",
                    priority: "error"
                }
            ]);
            excelInput.focus();
            return false;
        }else if($('#excelInput').val()) {

            if(excelInput.files && excelInput.files.length == 1)
            {
                if (excelInput.files[0].size > 50000000)
                {
                    //document.getElementById("alert").style.visibility = "visible";
                    //$('#alertt').html('university logo must not exceeds 2 MB');
                    $(document).trigger("clear-alert-id.exceli");
                    $(document).trigger("set-alert-id-exceli", [
                        {
                            message: "File must not exceeds 50 MB",
                            priority: "error"
                        }
                    ]);
                    excelInput.focus();
                    return false;
                }
            }
        }
    });
});