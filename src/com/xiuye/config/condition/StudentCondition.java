package com.xiuye.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class StudentCondition implements Condition{

	@Override
	public boolean matches(ConditionContext paramConditionContext, AnnotatedTypeMetadata paramAnnotatedTypeMetadata) {
		Environment env = paramConditionContext.getEnvironment();
		for(String a : env.getActiveProfiles()){
			System.out.println("Active profile := "+a);
		}
		boolean b = Boolean.valueOf(env.getProperty("test"));
		System.out.println("test := " + b);
		return b;
	}

}
