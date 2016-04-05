/**
 * Created by Mojahed on 2/12/2016.
 */



function onSubmitUpdateUser(){

    //var lvl = document.getElementById("userType").value;
    var fname = document.getElementById("fname");
    var mname = document.getElementById("mname");
    var lname = document.getElementById("lname");
    var email = document.getElementById("uemail");
    var password = document.getElementById("uOldPassword");
    var newpassword = document.getElementById("upassword");
    var renewpassword = document.getElementById("repassword");



    $(document).trigger("clear-alert-id.newpassword");
    $(document).trigger("clear-alert-id.renewpassword");
    $(document).trigger("clear-alert-id.password");
    $(document).trigger("clear-alert-id.uemail");
    $(document).trigger("clear-alert-id.fname");
    $(document).trigger("clear-alert-id.mname");
    $(document).trigger("clear-alert-id.lname");


    //alert(selectedValue+"\n"+fname.value+"\n"+mname.value+"\n"+lname.value+"\n"+uname.value+"\n"+email.value)

    //checking the first name START
    if(fname.value == "") {
        $(document).trigger("clear-alert-id.fname");
        $(document).trigger("set-alert-id-fname", [
            {
                message: "Please enter first name",
                priority: "error"
            }
        ]);
        fname.focus();
        return false;
    }else if (!/^[a-zA-Z]*$/g.test(fname.value)) {
        $(document).trigger("clear-alert-id.fname");
        $(document).trigger("set-alert-id-fname", [
            {
                message: "name must have only alphabetic letters",
                priority: "error"
            }
        ]);
        fname.focus();
        return false;
    }else if (fname.value.length < 3 || fname.value.length > 20) {
        $(document).trigger("clear-alert-id.fname");
        $(document).trigger("set-alert-id-fname", [
            {
                message: "first name must be in the range 3-20 characters",
                priority: "error"
            }
        ]);
        fname.focus();
        return false;

    }
    //checking the first name END
    //checking the middle name START
    else if(mname.value == "") {
        $(document).trigger("clear-alert-id.mname");
        $(document).trigger("set-alert-id-mname", [
            {
                message: "Please enter middle name",
                priority: "error"
            }
        ]);
        mname.focus();
        return false;
    }else if (!/^[a-zA-Z]*$/g.test(mname.value)) {
        $(document).trigger("clear-alert-id.mname");
        $(document).trigger("set-alert-id-mname", [
            {
                message: "name must have only alphabetic letters",
                priority: "error"
            }
        ]);
        mname.focus();
        return false;
    }else if (mname.value.length < 3 || mname.value.length > 20) {
        $(document).trigger("clear-alert-id.mname");
        $(document).trigger("set-alert-id-mname", [
            {
                message: "middle name must be in the range 3-20 characters",
                priority: "error"
            }
        ]);
        mname.focus();
        return false;
    }
    //checking the middle name END
    //checking the last name START
    else if(lname.value == "") {
        $(document).trigger("clear-alert-id.lname");
        $(document).trigger("set-alert-id-lname", [
            {
                message: "Please enter last name",
                priority: "error"
            }
        ]);
        lname.focus();
        return false;
    }else if (!/^[a-zA-Z]*$/g.test(lname.value)) {
        $(document).trigger("clear-alert-id.lname");
        $(document).trigger("set-alert-id-lname", [
            {
                message: "name must have only alphabetic letters",
                priority: "error"
            }
        ]);
        lname.focus();
        return false;
    }else if (lname.value.length < 3 || lname.value.length > 20) {
        $(document).trigger("clear-alert-id.lname");
        $(document).trigger("set-alert-id-lname", [
            {
                message: "last name must be in the range 3-7 characters",
                priority: "error"
            }
        ]);
        lname.focus();
        return false;
    }
    //checking the last name END

    else if(email.value == "") {//checking the email
        $(document).trigger("clear-alert-id.email");
        $(document).trigger("set-alert-id-email", [
            {
                message: "You must enter a valid email",
                priority: "error"
            }
        ]);
        email.focus();
        return false;
    }else if ((newpassword.value.toLowerCase().indexOf("abetas") >= 0)){
        $(document).trigger("clear-alert-id.newpassword");
        $(document).trigger("set-alert-id-newpassword", [
            {
                message: "Password must not contains the word abetas",
                priority: "error"
            }
        ]);
        newpassword.value = "";
        newpassword.focus();
    }else if ((newpassword.value.length < 6 || newpassword.value.length > 15 )&& (newpassword.value != "" || renewpassword.value != "")){
        $(document).trigger("clear-alert-id.newpassword");
        $(document).trigger("set-alert-id-newpassword", [
            {
                message: "Password must be in the range 6-15 characters",
                priority: "error"
            }
        ]);
        newpassword.value = "";
        newpassword.focus();
    } else if ((newpassword.value != "" || renewpassword.value != "") && newpassword.value != renewpassword.value ){
        $(document).trigger("clear-alert-id.renewpassword");
        $(document).trigger("set-alert-id-renewpassword", [
            {
                message: "new password fields aren't matched!",
                priority: "error"
            }
        ]);

        renewpassword.value = "";
        renewpassword.focus();
    }else {
        //checking the email format
        var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!re.test(email.value)) {
            $(document).trigger("clear-alert-id.email");
            $(document).trigger("set-alert-id-email", [
                {
                    message: "You must enter valid email format",
                    priority: "error"
                }
            ]);
            email.focus();
            return false;
        }else if(password.value == "") {
            $(document).trigger("clear-alert-id.password");
            $(document).trigger("set-alert-id-password", [
                {
                    message: "You must enter your password to apply the changes",
                    priority: "error"
                }
            ]);
            password.focus();
        } else {//submit


            document.getElementById("usrform").submit();
        }


    }

}

function onSubmitUpdateSystem(){

    var uname = document.getElementById("uname");
    var cname = document.getElementById("cname");
    //var ulogo = document.getElementById("ulogo");

    $(document).trigger("clear-alert-id.uname");
    $(document).trigger("clear-alert-id.cname");
    $(document).trigger("clear-alert-id.ulogo");


    if(uname.value == ""){
        $(document).trigger("clear-alert-id.uname");
        $(document).trigger("set-alert-id-uname", [
            {
                message: "University name must be entered",
                priority: "error"
            }
        ]);
        uname.focus();
    }else if (!/^[A-Za-z\s]+$/g.test(uname.value)) {
        $(document).trigger("clear-alert-id.uname");
        $(document).trigger("set-alert-id-uname", [
            {
                message: "name must have only alphabetic letters",
                priority: "error"
            }
        ]);
        uname.focus();
        return false;
    }else if(cname.value == ""){
        $(document).trigger("clear-alert-id.cname");
        $(document).trigger("set-alert-id-cname", [
            {
                message: "College name must be entered",
                priority: "error"
            }
        ]);
        cname.focus();
    }else if (!/^[A-Za-z\s]+$/g.test(cname.value)) {
        $(document).trigger("clear-alert-id.cname");
        $(document).trigger("set-alert-id-cname", [
            {
                message: "name must have only alphabetic letters",
                priority: "error"
            }
        ]);
        cname.focus();
        return false;
    }else if($('#ulogo').val()) {
        var input = document.getElementById("ulogo");
        //if(input.files && input.files.length == 1)
        if(input.files[0].size > 2000000)
        {

                //document.getElementById("alert").style.visibility = "visible";
                //$('#alertt').html('university logo must not exceeds 2 MB');
                $(document).trigger("clear-alert-id.ulogo");
                $(document).trigger("set-alert-id-ulogo", [
                    {
                        message: "University logo must not exceeds 2 MB",
                        priority: "error"
                    }
                ]);
                return false;

        }else {

            document.getElementById("sysform").submit();
        }
    }else {
        /*if ($('#removeLogo').is(":checked"))
        {
            // it is checked
            $('#ulogo').val("removed");
        }else {
            $('#ulogo').val(null)
        }*/
        document.getElementById("sysform").submit();
    }


}

function showSysSettings(){
    $('#usrSection').hide();
    $('#sysSection').show();
    $('#sysBtn').addClass("active");
    $('#usrBtn').removeClass("active");

}
function showUsrSettings(){
    $('#usrSection').show();
    $('#sysSection').hide();
    $('#usrBtn').addClass("active");
    $('#sysBtn').removeClass("active");

}
