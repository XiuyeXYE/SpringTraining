package com.xiuye.config.aspect;

import org.aspectj.lang.annotation.After;
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

}
