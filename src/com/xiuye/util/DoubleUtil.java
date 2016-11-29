package com.xiuye.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleUtil {
	public static String outputFullLengthDouble(String d) {
		DecimalFormat format = new DecimalFormat(".###");
		BigDecimal b = new BigDecimal(d);
		return format.format(b);
	}

	public static String outputFullLengthDouble(double d) {
		DecimalFormat format = new DecimalFormat(".###");
		return format.format(d);
	}
}
