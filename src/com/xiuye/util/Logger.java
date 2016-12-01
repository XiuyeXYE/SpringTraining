package com.xiuye.util;

public class Logger {

	public static Logger getInstance(){
		return LoggerHolder.instance;
	}

	private static class LoggerHolder{
		private static Logger instance = new Logger();
	}


	public void debug(String msg){
		System.out.println(msg);
	}



	public void info(String msg){
		System.out.println(msg);
	}
}
