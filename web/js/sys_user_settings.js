

function onSubmitUpdateUser(){

    //var lvl = document.getElementById("userType").value;
    var fname = document.getElementById("fname");
    var email = document.getElementById("uemail");
    var password = document.getElementById("uOldPassword");
    var newpassword = document.getElementById("upassword");
    var renewpassword = document.getElementById("repassword");


    //alert(selectedValue+"\n"+fname.value+"\n"+mname.value+"\n"+lname.value+"\n"+uname.value+"\n"+email.value)

    if(fname.value == "") {
        document.getElementById("alert").style.visibility = "visible";
        document.getElementById("alertt").innerHTML = "You must enter first name";
        fname.focus();
        return false;
    }else if(email.value == "") {
        document.getElementById("alert").style.visibility = "visible";
        document.getElementById("alertt").innerHTML = "You must enter an email";
        email.focus();
        return false;
    }  else if(password.value == "") {
        document.getElementById("alert").style.visibility = "visible";
        document.getElementById("alertt").innerHTML = "You must enter your password to apply the changes";
        password.focus();
    }else if ((newpassword.value != "" || renewpassword.value != "") && newpassword.value != renewpassword.value ){
        document.getElementById("alert").style.visibility = "visible";
        document.getElementById("alertt").innerHTML = "You must enter your new password twice to confirm changes";
        renewpassword.focus();
    }else {

        var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!re.test(email.value)) {
            document.getElementById("alert").style.visibility = "visible";
            document.getElementById("alertt").innerHTML = "You must enter valid email format";
            email.focus();
            return false;
        } else {
            document.getElementById("alert").style.visibility = "hidden";
            document.getElementById("usrform").submit();
        }


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
