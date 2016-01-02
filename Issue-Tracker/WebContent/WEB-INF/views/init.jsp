<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.shippable.service.IssueLocalService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shippable.dto.Issue"%>
<%@page import="java.util.List"%>
<%@page import="com.shippable.dto.User"%>
<html>
<head>
	
</head>
<% 
User user = (User)session.getAttribute("user");
List<Issue> issuesList = (List<Issue>)request.getAttribute("issuesList");
if(null == issuesList)
{
  issuesList =  new ArrayList<Issue>();
}
Long openIssuesCount = new Long(issuesList.size());
%>
</html>