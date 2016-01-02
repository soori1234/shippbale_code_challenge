<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="login-action" name = "loginForm" onsubmit="return validateForm()" method = "POST" >
 Email Address:<input type = "text" name = "emailId" /><br>
 Password: <input type = "password" name ="password"/><br>
 <input type = "submit" value="Login">
</form>


</body>


<script>
var x = document.forms["loginForm"]["emailId"].value;
if (x == null || x == "") {
    alert("Email Id mandatory");
    return false;
}

x = document.forms["loginForm"]["password"].value;
if (x == null || x == "") {
    alert("Passwordmandatory");
    return false;
}

</script>
</html>