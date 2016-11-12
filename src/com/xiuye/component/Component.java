package com.xiuye.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@org.springframework.stereotype.Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 必须原生,否则no longer has any
												// effect
public class Component {
	private String _1;
	private String _2;

	public Component(String _1, String _2) {
		super();
		this._1 = _1;
		this._2 = _2;
	}

	@Override
	public String toString() {
		return "Component [_1=" + _1 + ", _2=" + _2 + "]";
	}

}
