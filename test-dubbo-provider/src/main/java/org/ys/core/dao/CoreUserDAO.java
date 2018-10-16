package org.ys.core.dao;

import java.util.List;

import org.ys.core.model.CoreUser;

public interface CoreUserDAO {
	public void addUser(CoreUser coreUser) throws Exception;
	public void deleteUserById(String userId) throws Exception;
	public void updateUser(CoreUser coreUser) throws Exception;
	public List<CoreUser> pageUserList(String hql,int startNum,int pageSize) throws Exception;
	public long pageUserListCount(String hql) throws Exception;
	public CoreUser findUserById(String userId) throws Exception;
}
