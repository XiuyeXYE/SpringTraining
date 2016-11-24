package com.xiuye.verify;

public class VerifyNullBooleanToPrimitiveType {

	public static void main(String[] args) {

		boolean b = (Boolean)null==null?false:true;
		System.out.println(b);

	}

}
