package com.imooc;

import com.imooc.controller.HelloController;
import com.imooc.controller.HiController;
import com.imooc.controller.WelcomeController;
import com.imooc.service.WelcomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.imooc")
public class Entrance {

	/**
	 * 使用配置文件启动
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("hello world");
		String xmlPath = "//Users/oldwong/Desktop/Spring/spring-framework-5.2.0.RELEASE/springdemo/src/main/resources/spring/spring-config.xml";
		// 拿到对应的spring容器了
		ApplicationContext applicationContext =  new FileSystemXmlApplicationContext(xmlPath);
		// getBean(bean的名字)
		WelcomeService welcomeService = (WelcomeService) applicationContext.getBean("welcomeService");
		welcomeService.sayHello("强大的spring框架");
	}

	/**
	 * 注解方式启动容器
	 */
	public static void main2(String[] args) {
		// 指定配置类
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Entrance.class);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		// entrance
		// welcomeServiceImpl
		// for(String beanName:beanDefinitionNames){
		// 	System.out.println(beanName);
		// }
		//
		// WelcomeController welcomeController = (WelcomeController) applicationContext.getBean("welcomeController");
		// welcomeController.handleRequest();

		System.out.println("==============AOP==============");
		HelloController helloController = (HelloController)applicationContext.getBean("helloController");
		helloController.handleRequest();

		HiController hi = (HiController) applicationContext.getBean("hiController");
		hi.handleRequest();
	}
}
