
<%@ include file="init.jsp" %>

<%
   if(null != user)
   {%>
   <h2>Hi  <b><%=user.getFirstName() %> !</b></h2>
   <a href = "new-issue">New Issue</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href = "logout-action">Logout</a>
   <%
   }else
   {%>
   
   <a href="login" >Login</a>
   <a href = "register">Register</a>
   
   <% 
	   
   }
%>

<br><h1>Welcome to Issue Tracker</h1><br>


<a href = "/Issue-Tracker/">Open Issues </a>&nbsp;&nbsp;&nbsp;<a href = "last-24hrs-issues">Last 24 Hrs</a>&nbsp;&nbsp;&nbsp;
<a href = "last-24hrs-to-7days-issues">24 Hrs to 7 days</a>&nbsp;&nbsp;&nbsp;
<a href = "7-days-older-issues">7 days+ Open Issues</a><br><br>&nbsp;&nbsp;&nbsp;

<table border="1" class="tablesorter">
  <tr>
    <th><%=openIssuesCount %> Open</th>
  </tr>
  <% 
  for(int i =issuesList.size()-1; i>=0; i--)  
 // for(Issue issue: issuesList)
    {
	 Issue issue = issuesList.get(i);
    	%>
    	<tr>
    		<td>
    			<h2><b><a href= "issue-details?issueId=<%=issue.getIssueId()%>"><%=issue.getIssueTitle() %></a></b></h2><br>
    			<h4>#<%=issue.getIssueId() %> opened on <%=issue.getCreationDate() %> by <%=issue.getUserName() %> </h4>
    			
    		</td>
  		</tr>
    	<%
    }
  %>
  
</table>

