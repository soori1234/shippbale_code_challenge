<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="init.jsp" %>


<form action="issue-save-action" method = "POST">
Title:<input type = "text" id = "issueTitle" name = "issueTitle"><br>
Description:<input type = "textarea" id = "issueDesc" name = "issueDesc"><br>
Image Link :<input type = "textarea" id = "issueLink" name = "issueLink"><br>
<input type = "submit" value = "Post Issue">
</form>