<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
<h2>User Registration</h2>
<form name = "regForm" action="register-action" onsubmit="return validateForm()" method = "POST" >
	Email Address:   <input type = "text" name = "emailId"><br>
	Password:        <input type = "password" name = "password"><br>
	Confirm Password:<input type = "password" name = "confirmPassword"><br>
	First Name      :<input type = "text" name = "firstName"><br>
	Last Name:      <input type = "text" name = "lastName"><br>
	Mobile No:      <input type = "text" name = "mobileNo"><br>
	<input type = "submit" value = "Register" >
</form>
</body>

<script>
function validateForm() {
    var x = document.forms["regForm"]["emailId"].value;
    if (x == null || x == "") {
        alert("Email Id mandatory");
        return false;
    }else{
    	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    	 if (reg.test(x) == false) 
         {
             alert('Invalid Email Address');
             return (false);
         }
    }
   
    x = document.forms["regForm"]["password"].value;
    if (x == null || x == "") {
        alert("Passwordmandatory");
        return false;
    }
    var y = document.forms["regForm"]["confirmPassword"].value;
    if(x !=y){
    	alert("Confirm Password doesnt match");
    	return false;
    }
    
    x = document.forms["regForm"]["firstName"].value;
    if (x == null || x == "") {
        alert("First Name mandatory");
        return false;
    }
    
    x = document.forms["regForm"]["firstName"].value;
    if (x == null || x == "") {
        alert("First Name mandatory");
        return false;
    }
    
    x = document.forms["regForm"]["mobileNo"].value;
    if (x != null || x != "") {
        var t = isNaN(x);
        if(t == true)
        	{
        	alert("invalid mobile no");
        	return false;
        	}
    }
    
    
}
</script>


</html>