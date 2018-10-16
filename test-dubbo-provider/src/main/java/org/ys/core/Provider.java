package org.ys.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	private static final Log log = LogFactory.getLog(Provider.class);

	public static void main(String[] args) {

		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			context.start();
			System.in.read(); // 按任意键退出
		} catch (Exception e) {
			log.error("== DubboProvider context start error:", e);
		}
	}
}
