package org.ys.core.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
		if(null != coreUser){
			this.getSession().save(coreUser);
		}
	}

	@Override
	public void deleteUserById(String userId) throws Exception {
		if(StringUtils.isNotEmpty(userId)){
			Session session = this.getSession();
			Query query = session.createQuery("delete CoreUser where userId=?");
			query.setString(0, userId);
			query.executeUpdate();	
		}
	}

	@Override
	public void updateUser(CoreUser coreUser) throws Exception {
		if(null != coreUser){
			this.getSession().update(coreUser);
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreUser> pageUserList(String hql, int pageNum, int pageSize)throws Exception {
		List<CoreUser> users = null;
		if(StringUtils.isNotEmpty(hql)){
			Session session = this.getSession();
			Query query = session.createQuery(hql);
			query.setFirstResult(pageNum * pageSize);
			query.setMaxResults(pageSize);
			users = query.list();
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int pageUserListCount(String hql) throws Exception {
		int count = 0;
		if(null != hql){
			StringBuffer sb = new StringBuffer();
			sb.append("select count(1) from (").append(hql).append(")");
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(sb.toString());
			List<Object> userCount = query.list();
			session.close();
			count = (Integer) userCount.get(0);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CoreUser findUserById(String userId) throws Exception {
		if(StringUtils.isEmpty(userId)){
			return null;
		}
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
