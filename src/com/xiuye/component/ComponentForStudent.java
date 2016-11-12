package com.xiuye.component;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiuye.bean.Student;

@Component
public class ComponentForStudent {

	@Resource
	private Student s;

	public void studentInfo(){
		System.out.println(s);
	}

}
