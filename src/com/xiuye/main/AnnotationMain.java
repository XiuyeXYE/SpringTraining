package com.xiuye.main;

import com.xiuye.annotation.MyAnnotation;

@MyAnnotation(say="xiuye")
public class AnnotationMain {

	public static void main(String[] args) {
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		MyAnnotation ma = AnnotationMain.class.getAnnotation(MyAnnotation.class);
		System.out.println(ma.say());
	}

}
