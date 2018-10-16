package org.ys.core.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.ys.core.dao.CoreUserDAO;
import org.ys.core.model.CoreUser;

@Component("coreUserDAO")
public class CoreUserDAOImpl implements CoreUserDAO {
	private SessionFactory sessionFactory;

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addUser(CoreUser coreUser) throws Exception {
		this.getSession().save(coreUser);
	}

	@Override
	public void deleteUserById(String userId) throws Exception {
		Session session = this.getSession();
		Query query = session.createQuery("delete CoreUser where userId=?");
		query.setString(0, userId);
		query.executeUpdate();
	}

	@Override
	public void updateUser(CoreUser coreUser) throws Exception {
		this.getSession().update(coreUser);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreUser> pageUserList(String hql, int startNum, int pageSize)throws Exception {
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(startNum);
		query.setMaxResults(pageSize);
		List<CoreUser> users = query.list();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long pageUserListCount(String hql) throws Exception {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		List<Object> userCount = query.list();
		session.close();
		return (Long) userCount.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CoreUser findUserById(String userId) throws Exception {
		Session session = this.getSession();
		Query query = session.createQuery("from CoreUser where userId=?");
		query.setString(0, userId);
		List<CoreUser> users = query.list();
		if(CollectionUtils.isNotEmpty(users)){
			return users.get(0);
		}else{
			return null;
		}
	}

}
