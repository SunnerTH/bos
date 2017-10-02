package com.sun.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.websocket.Session;

import org.hibernate.SessionFactory;


import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sun.bos.dao.base.IBaseDao;

/**
 * 持久层通用实现
 * @author SunnerTH
 *
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{
	//实体类型
	private Class<T> entityClass;

	//使用注解方式进行依赖注入
	@Resource
	public void setMySessionFactory(SessionFactory SessionFactory){
		super.setSessionFactory(SessionFactory);
	}
	
	/**
	 * 在构造方法中获得操作的实体类型
	 */
	public BaseDaoImpl(){
		//获得父类（BaseDaoImpl<T>）类
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类的泛型数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	public void save(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(entity);
	}

	public void delete(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(entity);
	}

	public void update(T entity) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(entity);
	}

	public T findById(Serializable id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(entityClass, id);
	}

	public List<T> findAll() {
		// TODO Auto-generated method stub
		String hql = "FROM " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 通用更新方法
	 
	public void executeUpdate(String queryName, Object ...objectsobjects){
		Session session = this.getSession();// 从本地线程中获得session对象
		// 使用命名查询语句获得一个查询对象
		Query query = session.getNamedQuery(queryName);
		// 为HQL语句中的？赋值
		int i = 0;
		for(Object arg : objects){
			query.setParameter(i++,arg);
		}
		query.executeUpdate();// 执行更行
	}*/
}
