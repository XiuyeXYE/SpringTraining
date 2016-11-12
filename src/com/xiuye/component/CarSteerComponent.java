package com.xiuye.component;

import org.springframework.stereotype.Component;

import com.xiuye.annotation.Steer;

@Component
@Steer
public class CarSteerComponent implements CarComponent {

	@Override
	public void description() {
		System.out.println("I'm car's steer!");
	}

}
