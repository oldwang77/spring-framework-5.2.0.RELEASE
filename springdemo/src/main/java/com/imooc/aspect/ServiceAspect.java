package com.imooc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// 告诉spring，会织入
@Aspect
@Component
public class ServiceAspect {

	// com.imooc.service下的所有类的所有方法
	@Pointcut("execution(* com.imooc.service..*.*(..))")
	public void embed(){}

	@Before("embed()")
	public void before(JoinPoint joinPoint){
		System.out.println("开始调用" + joinPoint);
	}

	@After("embed()")
	public void after(JoinPoint joinPoint){
		System.out.println("调用完成" + joinPoint);
	}

	@Around("embed()")
	public Object aroundMe(JoinPoint joinPoint){
		long startTime = System.currentTimeMillis();
		Object returnValue = null;
		System.out.println("开始记录时间"+joinPoint);

		try {
			// 执行代理方法
			returnValue = ((ProceedingJoinPoint)joinPoint).proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println("总共耗时"+joinPoint+"["+ (endTime-startTime) +"]ms");
		}
		return returnValue;
	}

	@AfterReturning(pointcut = "embed()",returning = "returnValue")
	public void afterReturning(JoinPoint joinPoint,Object returnValue){
		System.out.println("无论是空还是有值都会返回"+joinPoint+",返回值"+returnValue);
	}

	@AfterThrowing(pointcut = "embed()",throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint,Exception exception){
		System.out.println(joinPoint+","+exception.getMessage());
	}
}
