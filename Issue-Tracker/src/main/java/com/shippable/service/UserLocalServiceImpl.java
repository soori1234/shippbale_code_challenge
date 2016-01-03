package com.shippable.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shippable.constants.IssueTrackerConstants;
import com.shippable.dao.UserDao;
import com.shippable.dto.User;

/**
 * @author Soori Babu Meesala
 *
 */
@Service
public class UserLocalServiceImpl implements UserLocalService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User saveUser(User user) {
      
        user.setCreationDate(new Date());
        user.setModifiedDate(new Date());
        user.setRole(IssueTrackerConstants.siteUserRole);
		return userDao.saveUser(user);
	}

	@Override
	public User getUser(User user) {
        
		return userDao.getUser(user);
	}

	@Override
	public User updateUser(User user) {
		
		return userDao.updateUser(user);
	}

	@Override
	public Boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
