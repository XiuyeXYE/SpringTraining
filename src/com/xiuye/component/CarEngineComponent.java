package com.xiuye.component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.xiuye.annotation.Engine;

@Component
@Primary
@Engine
public class CarEngineComponent implements CarComponent {

	@Override
	public void description() {

		System.out.println("I'm car's engine!");

	}

}
