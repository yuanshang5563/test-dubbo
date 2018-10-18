package org.ys.core.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.ys.common.page.PageBean;
import org.ys.common.utils.RequsetUtils;
import org.ys.core.model.CoreUser;
import org.ys.core.service.CoreUserService;

@Controller
@RequestMapping("/coreUserController")
public class CoreUserController {
	@Autowired
	private CoreUserService coreUserService;
	
	@RequestMapping("/coreUserList")
	public ModelAndView coreUserList(HttpServletRequest request) throws Exception {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String userName = request.getParameter("userName");
		
		StringBuffer sb = new StringBuffer();
		StringBuffer hql = new StringBuffer();
		hql.append("from CoreUser where 1=1 ");
		if(StringUtils.isNotEmpty(userName)){
			hql.append("and userName like '%").append(userName).append("%'");
			sb.append("&userName=").append(userName.trim());
		}
		if(StringUtils.isEmpty(pageNum)){
			pageNum = "1";
		}
		if(StringUtils.isEmpty(pageSize)){
			pageSize = "10";
		}
		List<CoreUser> userList = coreUserService.pageUserList(hql.toString(), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		int count = coreUserService.pageUserListCount(hql.toString());
		PageBean<CoreUser> pageInfo = new PageBean<CoreUser>(userList, count);
		ModelAndView model = new ModelAndView("/manager/core_user/core_user_list");
		model.addObject("pageInfo", pageInfo);
		model.addObject("sb", sb);
		model.addObject("userName", userName);
		return model;
	}	
	
	@RequestMapping("/addCoreUser")
	@ResponseBody
	public Map<String,Object> addCoreUser(HttpServletRequest request){
		String msg = null;
		int result = 0;
		try {
			Map<String,Object> paramMap = RequsetUtils.getParamsMap(request);
			CoreUser coreUser = new CoreUser();
			BeanUtilsBean.getInstance().getConvertUtils().register(new SqlDateConverter(null), Date.class);
			BeanUtils.populate(coreUser, paramMap);		
			coreUser.setCreatedTime(new Date());
			coreUser.setModifiedTime(new Date());
			coreUserService.addUser(coreUser);
			msg = "增加成功！";
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "增加失败！";
		} 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("states", result);
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(){
		String msg = null;
		try {
			CoreUser coreUser = new CoreUser();
			Random random = new Random(10000);
			coreUser.setUserName("中文"+random.nextInt());
			coreUser.setBirthday(new Date(System.currentTimeMillis()));
			coreUser.setEmail("test@163.com");
			coreUser.setPassword("123455");
			coreUser.setCommentContext("This is a test");
			coreUser.setAddress("测试地址");
			coreUser.setCreatedTime(new Date());
			coreUser.setModifiedTime(new Date());
			coreUserService.addUser(coreUser);
			msg = "添加成功！";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败，程序发生异常！";
		}
		return msg;
	}	
}
