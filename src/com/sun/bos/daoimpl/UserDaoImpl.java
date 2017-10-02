package com.sun.bos.daoimpl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sun.bos.dao.IUserDao;
import com.sun.bos.dao.base.impl.BaseDaoImpl;
import com.sun.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	//根据用户名和密码查询用户
	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
