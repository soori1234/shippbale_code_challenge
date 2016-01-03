package com.shippable.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
 * @author Soori Babu Meesala
 *
 */
@Controller
public class IssueController {

	@Autowired
	private IssueLocalService issueLocalService;

	@RequestMapping("/new-issue")
	public ModelAndView saveIssue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (null == user) {
			return new ModelAndView("login");
		} else {
			return new ModelAndView("new-issue", "issue", new Issue());
		}

	}

	@RequestMapping("/issue-save-action")
	public ModelAndView saveIssueAction(@ModelAttribute Issue issue, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (null == user) {
			return new ModelAndView("login");
		} else {
			issue.setCreatedBy(user.getUserId());
			issue.setModifiedBy(user.getUserId());
			issue.setUserName(user.getFirstName() + " " + user.getLastName());
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
	public ModelAndView getAllOpenIssues(HttpServletRequest request) {
		List<Issue> issuesList = issueLocalService.getAllIssues();
		return new ModelAndView("home", "issuesList", issuesList);
	}

	/**
	 * @param request
	 * @return This method returns the issues created in last 24 hrs
	 */
	@RequestMapping("/last-24hrs-issues")
	public ModelAndView getLast24hrsOpenIssues(HttpServletRequest request) {
		Date fromDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -24);
		Date toDate = cal.getTime();
		//System.out.println("from date "+fromDate+"  todate"+toDate);
		String hql = "from Issue where creationDate <= :fromDate and creationDate >= :toDate ";
		List<Issue> issuesList = issueLocalService.getSortedOpenIssues(hql, fromDate, toDate);
		return new ModelAndView("home", "issuesList", issuesList);
	}

	/**
	 * @param request
	 * @return This method returns issues created between last 24hrs to 7 days
	 */
	@RequestMapping("/last-24hrs-to-7days-issues")
	public ModelAndView getLast24hrsTo7DaysOpenIssues(HttpServletRequest request) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -24);
		Date fromDate = cal.getTime();

		// This code used to get the 7 days earlier to current date and the
		// starting time HH:MM:SS -->> 00:00:00
		cal.add(Calendar.DATE, -6);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date toDate = cal.getTime();
		//System.out.println("from date "+fromDate+"  todate"+toDate);
		
		String hql = "from Issue where creationDate <= :fromDate and creationDate >= :toDate ";
		List<Issue> issuesList = issueLocalService.getSortedOpenIssues(hql, fromDate, toDate);
		return new ModelAndView("home", "issuesList", issuesList);

	}

	/**
	 * @param request
	 * @return This method returns issues which are 7 days older
	 */
	@RequestMapping("/7-days-older-issues")
	public ModelAndView get7DaysOlderOpenIssues(HttpServletRequest request) {

		Calendar cal = Calendar.getInstance();

		// This code used to get the 7 days earlier to current date and the
		// starting time HH:MM:SS -->> 00:00:00
		cal.add(Calendar.DATE, -7);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		Date fromDate = cal.getTime();
		Date toDate = new Date(0l); // this will create date as 01-Jan-1970
		
		//System.out.println("from date "+fromDate+"  todate"+toDate);
		String hql = "from Issue where creationDate <= :fromDate and creationDate >= :toDate ";
		List<Issue> issuesList = issueLocalService.getSortedOpenIssues(hql, fromDate, toDate);
		return new ModelAndView("home", "issuesList", issuesList);

	}

	@RequestMapping(value = "/issue-details", method = RequestMethod.GET)
	public ModelAndView getIssueDetails(@RequestParam("issueId") String issueId, HttpServletRequest request) {
		Issue issue = new Issue();
		issue.setIssueId(Long.parseLong(issueId));
		Issue validIssue = issueLocalService.getIssue(issue);
		if (null != validIssue) {
			return new ModelAndView("issue-details", "issue", validIssue);
		} else {
			List<Issue> issuesList = issueLocalService.getAllIssues();
			return new ModelAndView("home", "issuesList", issuesList);
		}
	}

}
