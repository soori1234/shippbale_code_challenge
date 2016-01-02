package com.shippable.service;

import java.util.List;


import com.shippable.dto.User;

public interface UserLocalService {
	
	public User saveUser(User user);
	public User getUser(User user);
	public User updateUser(User user);
	public Boolean deleteUser(User user);
	public List<User> getAllUsers();

}
