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
		if(null == coreUser){
			throw new Exception("插入用户为空！");
		}
		coreUserDAO.addUser(coreUser);
	}

	@Override
	public void deleteUserById(String userId) throws Exception {
		if(StringUtils.isEmpty(userId)){
			throw new Exception("删除用户Id为空！");
		}
		coreUserDAO.deleteUserById(userId);
	}

	@Override
	public void updateUser(CoreUser coreUser) throws Exception {
		if(null == coreUser){
			throw new Exception("更新用户为空！");
		}
		coreUserDAO.updateUser(coreUser);
	}

	@Override
	public List<CoreUser> pageUserList(String hql, int startNum, int pageSize)throws Exception {
		if(StringUtils.isEmpty(hql)){
			throw new Exception("分页hql为空！");
		}
		return coreUserDAO.pageUserList(hql, startNum, pageSize);
	}

	@Override
	public long pageUserListCount(String hql) throws Exception {
		if(StringUtils.isEmpty(hql)){
			throw new Exception("分页总数hql为空！");
		}
		return coreUserDAO.pageUserListCount(hql);
	}

	@Override
	public CoreUser findUserById(String userId) throws Exception {
		if(StringUtils.isEmpty(userId)){
			throw new Exception("查找用户Id为空！");
		}
		return coreUserDAO.findUserById(userId);
	}

}
