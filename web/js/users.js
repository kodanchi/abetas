/**
 * Created by Mojahed on 1/25/2016.
 */


function onUserTypeChng()
{
    var ddl = document.getElementById("userType");
    var selectedValue = ddl.options[ddl.selectedIndex].value;
    if (selectedValue == "Superuser" || selectedValue == "Faculty_Member")
    {
        document.getElementById("emailDiv").style.visibility = "visible";
    }else {
        document.getElementById("emailDiv").style.visibility = "hidden";
        document.getElementById("emailDiv").value = "";
    }
}

function onSubmitAddUser(){

    //var lvl = document.getElementById("userType").value;
    var fname = document.getElementById("fname");
    var mname = document.getElementById("mname");
    var lname = document.getElementById("lname");
    var uname = document.getElementById("uname");




    var lvl = document.getElementById("userType");
    var selectedValue = lvl.options[lvl.selectedIndex].value;
    if (selectedValue == "Superuser" || selectedValue == "Faculty_Member")
    {
        var email = document.getElementById("userEmail");
    }

    //alert(selectedValue+"\n"+fname.value+"\n"+mname.value+"\n"+lname.value+"\n"+uname.value+"\n"+email.value)


    if(selectedValue == "User Type" || selectedValue == "") {

        document.getElementById("alert").style.visibility = "visible";
        document.getElementById("alertt").innerHTML = "You must select user type";

        lvl.focus();
        return false;
    }else if(fname.value == "") {
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
    }else if(uname.value == "") {
        document.getElementById("alert").style.visibility = "visible";
        document.getElementById("alertt").innerHTML = "You must enter user's username";
        uname.focus();
        return false;
    }else if(selectedValue == "Superuser" || selectedValue == "Faculty_Member") {
        if (email.value != "") {
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if (!re.test(email.value)) {
                document.getElementById("alert").style.visibility = "visible";
                document.getElementById("alertt").innerHTML = "You must enter valid email format";
                email.focus();
                return false;
            } else {
            document.getElementById("alert").style.visibility = "hidden";
            document.getElementById("UserAddForm").submit();
        }
        }else {
            document.getElementById("alert").style.visibility = "visible";
            document.getElementById("alertt").innerHTML = "You must enter user's email";
            email.focus();
            return false;
        }
    }else {
        document.getElementById("alert").style.visibility = "hidden";
        document.getElementById("UserAddForm").submit();
    }


}