package com.xiuye.component;


import org.springframework.stereotype.Component;

import com.xiuye.bean.Student;

@Component
public class ComponentForStudent2 {

	private Student s;

	public void setStudent(Student s){
		this.s = s;
	}

	public void studentInfo(){
		System.out.println(s);
	}

}
