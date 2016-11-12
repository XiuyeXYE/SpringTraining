package com.xiuye.test;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiuye.bean.Car;
import com.xiuye.bean.WhiteCar;
import com.xiuye.component.ComponentForStudent;
import com.xiuye.component.ComponentForStudent2;
import com.xiuye.config.BeanConfiguration1;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BeanConfiguration1.class)
@ActiveProfiles("dev")

public class TestMain {


	@Resource
	private ComponentForStudent cfs;
	@Resource
	private ComponentForStudent2 cfs2;

	@Resource
	private Car car;

	@Resource
	private WhiteCar wcar;

	@BeforeClass
	public static void envConfig(){
		System.setProperty("test","true");
	}

	@Test
	public void testCfs(){
		cfs.studentInfo();
	}
	@Test
	public void testCfs2(){
		cfs2.studentInfo();
	}


	@Test
	public void testCar(){
		this.car.configInfo();
	}
	@Test
	public void testWhiteCar(){
		this.wcar.configInfo();
	}

}
