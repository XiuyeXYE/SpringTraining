package com.xiuye.util;

import java.text.DecimalFormat;

public class DoubleUtil {
	public static String outputFullLengthDouble(String d) {
		DecimalFormat format = new DecimalFormat(".###");
		return format.format(d);
	}

	public static String outputFullLengthDouble(double d) {
		DecimalFormat format = new DecimalFormat(".###");

		return format.format(d);
	}
}
