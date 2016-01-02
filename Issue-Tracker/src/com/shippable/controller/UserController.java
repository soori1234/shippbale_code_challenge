package com.shippable.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shippable.dto.Issue;
import com.shippable.dto.User;
import com.shippable.service.IssueLocalService;
import com.shippable.service.UserLocalService;

/**
 * @author Soori Babu Meesala
 * This class acts as controller and proceess the user request
 *
 */
@Controller
public class UserController {
    @Autowired
	private UserLocalService userLocalService;
    @Autowired
    private IssueLocalService issueLocalService;
	
	@RequestMapping("/")
    public ModelAndView defaultPage()
	{
		List<Issue> issuesList = issueLocalService.getAllIssues(); 
		return new ModelAndView("home", "issuesList", issuesList);
	}
    
	@RequestMapping("/register")
	public ModelAndView registerUser(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)
        {
        	return new ModelAndView("home");
        }
		return new ModelAndView("register","user", new User());
	}
	@RequestMapping(value = "/register-action", method = RequestMethod.POST)
	public ModelAndView registerUserAction(@ModelAttribute("user")User user)
	{
		user = userLocalService.saveUser(user);
		return new ModelAndView("success","user",user);
		
	}
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
        if(session.getAttribute("user") != null)
        {
        	return new ModelAndView("home");
        }
		return new ModelAndView("login","user",new User());
	}
	
	@RequestMapping(value = "/login-action", method = RequestMethod.POST)
	public ModelAndView loginAction(@ModelAttribute("user")User user, HttpServletRequest request)
	{
		user = userLocalService.getUser(user);
		if(null != user)
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			user.setLastLoginIP(request.getLocalAddr());
			user.setLastLoginTime(new Date());
			userLocalService.updateUser(user);
		}
		List<Issue> issuesList = issueLocalService.getAllIssues(); 
		return new ModelAndView("home", "issuesList", issuesList);
		
	}

	@RequestMapping("/logout-action")
	public ModelAndView logoutAction(@ModelAttribute("user")User user, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();
		List<Issue> issuesList = issueLocalService.getAllIssues(); 
		return new ModelAndView("home", "issuesList", issuesList); 
	}
}
