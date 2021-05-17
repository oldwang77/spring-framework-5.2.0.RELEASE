package com.imooc;

import com.imooc.service.WelcomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Entrance {
	public static void main(String[] args) {
		System.out.println("hello world");
		String xmlPath = "//Users/oldwong/Desktop/Spring/spring-framework-5.2.0.RELEASE/springdemo/src/main/resources/spring/spring-config.xml";
		// 拿到对应的spring容器了
		ApplicationContext applicationContext =  new FileSystemXmlApplicationContext(xmlPath);
		// getBean(bean的名字)
		WelcomeService welcomeService = (WelcomeService) applicationContext.getBean("welcomeService");
		welcomeService.sayHello("强大的spring框架");
	}
}
