package com.xiuye.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.xiuye.annotation.Engine;
import com.xiuye.annotation.Steer;
import com.xiuye.annotation.Wheel;
import com.xiuye.component.CarComponent;

public class WhiteCar {

	private CarComponent wheels;
	private CarComponent steer;
	private CarComponent engine;

	@Autowired
	//@Qualifier("carWheelComponent")
	@Wheel
	public void setWheels(CarComponent wheels) {
		this.wheels = wheels;
	}
	@Autowired
	//@Qualifier("carSteerComponent")
	@Steer
	public void setSteer(CarComponent steer) {
		this.steer = steer;
	}
	@Autowired
	@Engine
	public void setEngine(CarComponent engine) {
		this.engine = engine;
	}

	public void configInfo(){
		this.steer.description();
		this.wheels.description();
		this.engine.description();
	}




}
