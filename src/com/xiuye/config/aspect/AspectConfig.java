package com.xiuye.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
/*import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;*/

@Aspect
//@Configuration
public class AspectConfig {

	@Before("execution(** com.xiuye.component.*.description())")
	public void beforeDescription(){
		System.out.println("Before method:description!");
	}
	@Pointcut("execution(** com.xiuye.component.*.description())")
	public void description(){
	}

	@After("description()")
	public void afterDescription(){
		System.out.println("After method:description!");
	}
	@AfterReturning("description()")
	public void afterReturningDescription(){
		System.out.println("AfterReturning method:description!");
	}
	@AfterThrowing("description()")
	public void afterThrowingDescription(){
		System.out.println("AfterThrowing method:description!");
	}
	@Around("description()")//始终在其通知前面执行
	public void aroundDescription(ProceedingJoinPoint jp){
		System.out.println("Around method:description!");
		System.out.println("Hello World!");
		try {
			jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("Is game over?!");
	}

}
