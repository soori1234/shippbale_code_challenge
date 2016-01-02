package com.shippable.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shippable.constants.IssueTrackerConstants;
import com.shippable.dao.IssueDao;
import com.shippable.dto.Issue;

@Service
public class IssueLocalServiceImpl implements IssueLocalService{

	@Autowired
	private IssueDao issueDao;
	
	@Override
	public Issue saveIssue(Issue issue) {
		issue.setStatus(IssueTrackerConstants.open);
		issue.setCreationDate(new Date());
		issue.setModifiedDate(new Date());
		return issueDao.saveIssue(issue);
	}

	@Override
	public Issue getIssue(Issue issue) {


		return issueDao.getIssue(issue);
	}

	@Override
	public Issue updateIssue(Issue issue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteIssue(Issue issue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issue> getAllIssues() {
	   List<Issue> openIssues =  issueDao.getAllIssues();
		return openIssues;
	}

	@Override
	public List<Issue> getSortedOpenIssues(String hql, Date fromDate, Date toDate){
		
		return issueDao.getSortedOpenIssues(hql, fromDate, toDate);
	}

}
