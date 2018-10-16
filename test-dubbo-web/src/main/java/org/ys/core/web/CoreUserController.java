package org.ys.core.web;

import java.sql.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ys.core.model.CoreUser;
import org.ys.core.service.CoreUserService;

@Controller
@RequestMapping("/CoreUserController")
public class CoreUserController {
	@Autowired
	private CoreUserService coreUserService;
	
	@RequestMapping("/userList")
	public String userList(){
		System.out.println("----------1231--------");
		return "core_user_list";
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(){
		String msg = null;
		try {
			CoreUser coreUser = new CoreUser();
			Random random = new Random(10000);
			coreUser.setUserName("中文"+random.nextInt());
			coreUser.setAge(22);
			coreUser.setBirthday(new Date(System.currentTimeMillis()));
			coreUser.setEmail("test@163.com");
			coreUser.setPassword("123455");
			coreUser.setCommentContext("This is a test");
			coreUser.setAddress("测试地址");
			coreUserService.addUser(coreUser);
			msg = "添加成功！";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败，程序发生异常！";
		}
		return msg;
	}
}
