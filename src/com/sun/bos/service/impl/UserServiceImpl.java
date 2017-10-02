package com.sun.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.bos.dao.IUserDao;
import com.sun.bos.domain.User;
import com.sun.bos.service.IUserService;
import com.sun.bos.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	//注入dao
	@Autowired
	private IUserDao userDao;
	
	public User login(User user) {
		// TODO Auto-generated method stub
		String username = user.getUsername();
		String password = user.getPassword();//获取到密码
		password = MD5Utils.md5(password);//md5加密
		return userDao.findByUsernameAndPassword(username,password);
	}

	@Override
	public void editPassword(String password, String id) {
		// TODO Auto-generated method stub
		userDao.executeUpdate("editPassword",password,id);
	}

}
