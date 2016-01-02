package com.shippable.controller;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shippable.dto.Issue;
import com.shippable.dto.User;
import com.shippable.service.IssueLocalService;

/**
 * @author SONY
 *
 */
/**
 * @author SONY
 *
 */
/**
 * @author SONY
 *
 */
@Controller
public class IssueController {

	@Autowired
	private IssueLocalService issueLocalService;
	@RequestMapping("/new-issue")
	public ModelAndView saveIssue(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(null == user)
		{
			return new ModelAndView("login");
		}else{
			return new ModelAndView("new-issue","issue",new Issue());
		}
		
	}
	
	@RequestMapping("/issue-save-action")
	public ModelAndView saveIssueAction(@ModelAttribute Issue issue, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(null == user)
		{
			return new ModelAndView("login");
		}else{
			issue.setCreatedBy(user.getUserId());
			issue.setModifiedBy(user.getUserId());
			issue.setUserName(user.getFirstName()+" "+user.getLastName());
			issueLocalService.saveIssue(issue);
			List<Issue> issuesList = issueLocalService.getAllIssues(); 
			return new ModelAndView("home", "issuesList", issuesList);
		}
		
	}
	
	
	/**
	 * @param request
	 * @return this method returns all issues created in db
	 * 
	 */
	@RequestMapping("/all-issues")
	public ModelAndView getAllOpenIssues(HttpServletRequest request)
	{
		List<Issue> issuesList = issueLocalService.getAllIssues();
		return new ModelAndView("home","issuesList",issuesList);
	}
	
	
	/**
	 * @param request
	 * @return This method returns the issues created in last 24 hrs
	 */
	@RequestMapping("/last-24hrs-issues")
	public ModelAndView getLast24hrsOpenIssues(HttpServletRequest request)
	{
		long difference = 1000*60*60*24;
		Date fromDate = new Date();
		Date toDate = new Date(fromDate.getTime()-difference);
	    System.out.println("from date "+fromDate);
	    System.out.println("to date "+toDate);
		String hql = "from Issue where creationDate <= :fromDate and creationDate >= :toDate ";
		List<Issue> issuesList = issueLocalService.getSortedOpenIssues(hql,fromDate,toDate);
		return new ModelAndView("home","issuesList",issuesList);
	}
	
	
	/**
	 * @param request
	 * @return This method returns issues created between last 24hrs to 7 days
	 */
	@RequestMapping("/last-24hrs-to-7days-issues")
	public ModelAndView getLast24hrsTo7DaysOpenIssues(HttpServletRequest request)
	{
		long difference = 1000*60*60*24;
		Date curDate = new Date();
		Date fromDate = new Date(curDate.getTime()-difference);
		
		Date toDate = new Date(curDate.getTime()-(difference*7));
		
		System.out.println("24 to 7days older");
		System.out.println("from date "+fromDate);
	    System.out.println("to date "+toDate);
	    
		String hql = "from Issue where creationDate <= :fromDate and creationDate >= :toDate ";
		List<Issue> issuesList = issueLocalService.getSortedOpenIssues(hql,fromDate,toDate);
		return new ModelAndView("home","issuesList",issuesList);

	}
	
	
	/**
	 * @param request
	 * @return This method returns issues which are 7 days older
	 */
	@RequestMapping("/7-days-older-issues")
	public ModelAndView get7DaysOlderOpenIssues(HttpServletRequest request)
	{
		long difference = 1000*60*60*24*7;
		Date curDate = new Date();
		
		Date fromDate = new Date(curDate.getTime()-difference);
		Date toDate = new Date(0l);
		
		System.out.println("7days older");
		System.out.println("from date "+fromDate);
	    System.out.println("to date "+toDate);
	    
		String hql = "from Issue where creationDate <= :fromDate and creationDate >= :toDate ";
		List<Issue> issuesList = issueLocalService.getSortedOpenIssues(hql,fromDate,toDate);
		return new ModelAndView("home","issuesList",issuesList);

	}
	
	@RequestMapping(value="/issue-details", method = RequestMethod.GET)
	public ModelAndView getIssueDetails(@RequestParam("issueId") String issueId, HttpServletRequest request)
	{
		Issue issue = new Issue();
		issue.setIssueId(Long.parseLong(issueId));
		Issue validIssue = issueLocalService.getIssue(issue);
		if(null != validIssue)
		{
			return new ModelAndView("issue-details","issue",validIssue);
		}
		else{
			List<Issue> issuesList = issueLocalService.getAllIssues(); 
		    return new ModelAndView("home", "issuesList", issuesList);
		}
	}

}
