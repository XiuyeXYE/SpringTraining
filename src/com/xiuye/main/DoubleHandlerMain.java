package com.xiuye.main;

import java.text.DecimalFormat;

public class DoubleHandlerMain {

	public static void main(String[] args) {

		Double d = Double.valueOf("1.0E10");
		DecimalFormat format = new DecimalFormat(".###");
		System.out.println(format.format(d));
		d = Double.valueOf("5656.6789767");
		format = new DecimalFormat(".###");
		System.out.println(format.format(d));

	}

	public static String outputFullLengthDouble(String d) {
		DecimalFormat format = new DecimalFormat(".###");
		double d1 = Double.valueOf(d);
		return format.format(d1);
	}

	public static String outputFullLengthDouble(double d) {
		DecimalFormat format = new DecimalFormat(".###");

		return format.format(d);
	}

}
