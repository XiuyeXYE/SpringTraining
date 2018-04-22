package com.xiuye.spring.factory;

import org.springframework.beans.factory.FactoryBean;

import com.xiuye.spring.bean.Car;

public class CarFactoryBean implements FactoryBean<Car> {

	private String carInfo;

	@Override
	public Car getObject() throws Exception {
		Car c = new Car();
		String [] infos = this.carInfo.split(",");
		c.setBrand(infos[0]);
		c.setMaxSpeed(Integer.valueOf(infos[1]));
		c.setPrice(Double.valueOf(infos[2]));
		return c;
	}

	@Override
	public Class<?> getObjectType() {

		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}



}
