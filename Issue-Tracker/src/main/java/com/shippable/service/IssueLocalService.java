package com.shippable.service;

import java.util.Date;
import java.util.List;

import com.shippable.dto.Issue;


/**
 * @author Soori Babu Meesala
 *
 */
public interface IssueLocalService {
	public Issue saveIssue(Issue issue);
	public Issue getIssue(Issue issue);
	public Issue updateIssue(Issue issue);
	public Boolean deleteIssue(Issue issue);
	public List<Issue> getAllIssues();
	public List<Issue> getSortedOpenIssues(String hql, Date fromDate, Date toDate);
	
}
