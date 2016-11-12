package com.xiuye.component;

import org.springframework.stereotype.Component;

import com.xiuye.annotation.Wheel;

@Component
@Wheel
public class CarWheelComponent implements CarComponent {

	@Override
	public void description() {
		System.out.println("I'm car's wheels!");
	}

}
