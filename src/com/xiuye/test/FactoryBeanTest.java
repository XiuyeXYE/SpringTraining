package com.xiuye.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import oracle.net.aso.a;

public class FactoryBeanTest {

	public static void main(String[] args) {

		ApplicationContext ac = new ClassPathXmlApplicationContext("META-INF/beans.xml");
		System.out.println(ac.getBean("car"));
		System.out.println(ac.getBean("&car"));

	}

}
