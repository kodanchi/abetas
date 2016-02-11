/**
 * Created by Mojahed on 1/25/2016.
 */



function onSubmitUpdateUser(){

    //var lvl = document.getElementById("userType").value;
    var fname = document.getElementById("fname");
    var mname = document.getElementById("mname");
    var lname = document.getElementById("lname");
    var email = document.getElementById("uemail");


    //alert(selectedValue+"\n"+fname.value+"\n"+mname.value+"\n"+lname.value+"\n"+uname.value+"\n"+email.value)

    if(fname.value == "") {
        document.getElementById("alert").style.visibility = "visible";
        document.getElementById("alertt").innerHTML = "You must enter user's first name";
        fname.focus();
        return false;
    }else if(mname.value == "") {
        document.getElementById("alert").style.visibility = "visible";
        document.getElementById("alertt").innerHTML = "You must enter user's middle name";
        mname.focus();
        return false;
    }else if(lname.value == "") {
        document.getElementById("alert").style.visibility = "visible";
        document.getElementById("alertt").innerHTML = "You must enter user's last name";
        lname.focus();
        return false;
    }   else if (email.value != "") {
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
    }else {
        document.getElementById("alert").style.visibility = "hidden";
        document.getElementById("usrform").submit();
    }

}