package com.xiuye.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.xiuye.component.CarComponent;

public class Car {

	private CarComponent wheels;
	private CarComponent steer;
	private CarComponent engine;

	@Autowired
	@Qualifier("carWheelComponent")
	public void setWheels(CarComponent wheels) {
		this.wheels = wheels;
	}
	@Autowired
	@Qualifier("carSteerComponent")
	public void setSteer(CarComponent steer) {
		this.steer = steer;
	}
	@Autowired
	public void setEngine(CarComponent engine) {
		this.engine = engine;
	}

	public void configInfo(){
		this.steer.description();
		this.wheels.description();
		this.engine.description();
	}




}
