package com.sun.bos.service;

import com.sun.bos.domain.User;

public interface IUserService {
	
	public User login(User model);
	
	public void editPassword(String password, String id);
}
