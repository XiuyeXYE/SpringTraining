package com.xiuye.main;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class EnvOutputAll {

	public static void main(String[] args) {

		Properties p = System.getProperties();
		Set<Object> keySet = p.keySet();
		System.out.println("=================properties===================");
		for(Object key : keySet){
			System.out.println(key + " := " + p.get(key));
		}
		System.out.println("=================properties===================");

		Map<String,String> map = System.getenv();

		Set<String> mapKeySet = map.keySet();
		System.out.println("=================env===================");
		for(String key : mapKeySet){
			System.out.println(key + " := " + map.get(key));
		}
		System.out.println("=================env===================");

		LookAndFeelInfo [] lafInfos = UIManager.getInstalledLookAndFeels();
		for(LookAndFeelInfo lafInfo : lafInfos){
			System.out.println(lafInfo);
		}

		System.out.println("ADg".equalsIgnoreCase("AdG"));

	}

}
