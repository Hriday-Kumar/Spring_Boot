package com.hriday.service;

import com.hriday.model.User;

public interface UserService {
	
	public User saveUser(User user);
	public User findUserBymail(String email);

}
