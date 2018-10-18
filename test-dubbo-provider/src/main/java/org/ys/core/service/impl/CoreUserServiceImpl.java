package org.ys.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.ys.core.dao.CoreUserDAO;
import org.ys.core.model.CoreUser;
import org.ys.core.service.CoreUserService;

@Service("coreUserService")
public class CoreUserServiceImpl implements CoreUserService {
	private CoreUserDAO coreUserDAO;

	@Resource
	public void setCoreUserDAO(CoreUserDAO coreUserDAO) {
		this.coreUserDAO = coreUserDAO;
	}

	@Override
	public void addUser(CoreUser coreUser) throws Exception {
		if(null != coreUser){
			coreUserDAO.addUser(coreUser);
		}
	}

	@Override
	public void deleteUserById(String userId) throws Exception {
		if(StringUtils.isNotEmpty(userId)){
			coreUserDAO.deleteUserById(userId);
		}
	}

	@Override
	public void updateUser(CoreUser coreUser) throws Exception {
		if(null != coreUser){
			coreUserDAO.updateUser(coreUser);
		}
	}

	@Override
	public List<CoreUser> pageUserList(String hql, int pageNum, int pageSize)throws Exception {
		List<CoreUser> userList = null;
		if(StringUtils.isNotEmpty(hql)){
			userList = coreUserDAO.pageUserList(hql, pageNum, pageSize);
		}
		return userList;
	}

	@Override
	public int pageUserListCount(String hql) throws Exception {
		int count = 0;
		if(StringUtils.isNotEmpty(hql)){
			count = coreUserDAO.pageUserListCount(hql);
		}
		return count;
	}

	@Override
	public CoreUser findUserById(String userId) throws Exception {
		CoreUser coreUser = null;
		if(StringUtils.isNotEmpty(userId)){
			coreUser = coreUserDAO.findUserById(userId);
		}
		return coreUser;
	}

}
