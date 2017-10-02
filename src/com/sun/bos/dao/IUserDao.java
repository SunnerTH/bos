package com.sun.bos.dao;
import com.sun.bos.dao.base.IBaseDao;
import com.sun.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {

	public User findByUsernameAndPassword(String username, String password);

}
