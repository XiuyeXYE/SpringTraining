package com.xiuye.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xiuye.bean.Car;
import com.xiuye.bean.Student;
import com.xiuye.component.Component;
import com.xiuye.component.ComponentForStudent;
import com.xiuye.config.BeanConfiguration1;
import com.xiuye.util.DoubleUtil;

public class Main {

	public static void main(String []args) {

		String s = DoubleUtil.outputFullLengthDouble("1.67889E10");
		System.out.println(s);
		System.setProperty("test","false");//@Conditional注解对AnnotationConfigApplicationContext不起作用
		System.setProperty("spring.profiles.active","dev");//设置(开启)激活的开发环境   可以   不设置报错
		//System.setProperty("spring.profiles.default","dev");//设置默认的开发环境  可以

		ApplicationContext ac = new AnnotationConfigApplicationContext(BeanConfiguration1.class);
		Student s1 = ac.getBean(Student.class);
		System.out.println(s1);

		Student s2 = ac.getBean(Student.class, "Linda","Woman",22,70);
		System.out.println(s2);
		ComponentForStudent cf = ac.getBean(ComponentForStudent.class);
		cf.studentInfo();
		Component c = ac.getBean(Component.class, "100","200");
		System.out.println(c);
		Car car = ac.getBean(Car.class);
		car.configInfo();
	}

}
