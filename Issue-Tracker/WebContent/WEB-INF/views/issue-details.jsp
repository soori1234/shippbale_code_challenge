<%@page import="com.shippable.dto.Issue"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
   Issue issue = (Issue)request.getAttribute("issue");
%>
<a href="/Issue-Tracker/">Home</a>

<table border="1">
 <tr><th><h2><b><%=issue.getIssueTitle() %></b>  #<%=issue.getIssueId() %></h2>  <h4>Status : <%=issue.getStatus() %></h4><br></th></tr>
<tr><td><h4><b><%=issue.getUserName() %></b> opened this issue on <%=issue.getCreationDate() %></h4><br></td></tr>
<tr><td><h4><%= issue.getIssueDesc() %></h4><br></td></tr>
</table>
<img alt="no-image" src="<%=issue.getIssueLink()%>" height="350px" widt="400px">
</body>
</html>