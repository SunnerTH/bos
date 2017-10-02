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
 * �־ò�ͨ��ʵ��
 * @author SunnerTH
 *
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{
	//ʵ������
	private Class<T> entityClass;

	//ʹ��ע�ⷽʽ��������ע��
	@Resource
	public void setMySessionFactory(SessionFactory SessionFactory){
		super.setSessionFactory(SessionFactory);
	}
	
	/**
	 * �ڹ��췽���л�ò�����ʵ������
	 */
	public BaseDaoImpl(){
		//��ø��ࣨBaseDaoImpl<T>����
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//��ø���ķ�������
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
	 * ͨ�ø��·���
	 
	public void executeUpdate(String queryName, Object ...objectsobjects){
		Session session = this.getSession();// �ӱ����߳��л��session����
		// ʹ��������ѯ�����һ����ѯ����
		Query query = session.getNamedQuery(queryName);
		// ΪHQL����еģ���ֵ
		int i = 0;
		for(Object arg : objects){
			query.setParameter(i++,arg);
		}
		query.executeUpdate();// ִ�и���
	}*/
}
