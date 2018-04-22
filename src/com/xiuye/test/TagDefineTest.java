package com.xiuye.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiuye.spring.tag.User;

public class TagDefineTest {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("META-INF/beans.xml");
		User u = (User) ac.getBean("userBean");
		System.out.println("username: " + u.getName() + "\nemail: " + u.getEmail());
		u = ac.getBean(User.class);
		System.out.println("username: " + u.getName() + "\nemail: " + u.getEmail());
	}

}
