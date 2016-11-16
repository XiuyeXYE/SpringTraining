package com.xiuye.excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCHandler {

	public static Connection getConnection(String driveClassName,String url,String user,String password) throws ClassNotFoundException, SQLException {

		Class.forName(driveClassName);
		return DriverManager.getConnection(url,user,password);

	}
	public static Connection getConnection(String driveClassName,String url) throws ClassNotFoundException, SQLException {

		Class.forName(driveClassName);
		return DriverManager.getConnection(url);

	}

	public static PreparedStatement preparedStatement(String sql,String driveClassName,String url,String user,String password) throws ClassNotFoundException, SQLException{

		Connection connection = getConnection(driveClassName,url,user,password);
		return connection.prepareStatement(sql);
	}
	public static PreparedStatement preparedStatement(String sql,String driveClassName,String url) throws ClassNotFoundException, SQLException{

		Connection connection = getConnection(driveClassName,url);
		return connection.prepareStatement(sql);
	}



}
