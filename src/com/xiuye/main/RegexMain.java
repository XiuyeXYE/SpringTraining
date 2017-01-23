package com.xiuye.main;

import java.util.Arrays;
import java.util.Date;

public class RegexMain {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		String s = "﻿\" JKR\",\"SFZH\",\"HTH\",\"DKYE\",\"BQHJE\",\"BQQC\",\"XCBJRQ\",\"YHMC\",\"YHDM\",\"FFE\",\"DKQX\",\"ZTMC\",\"HKFSMC\",\"YQQC\",\"YQBX\"";
		System.out.println(s);
		System.out.println(s.replaceAll("[^\\w|,]", ""));



		long end = System.currentTimeMillis();

		System.out.println(end - start);
		System.out.println(new Date(end - start));
		long l = end - start;
		String time = "";
		l /= 1000;// s
		time += l;
		System.out.println(l);
		l /= 60;// m
		time += ":" + l;
		System.out.println(l);
		l /= 60;// h
		time += ":" + l;
		System.out.println(l);
		System.out.println(time);

		String []s1 = {"FFF","SFSDF"};
		System.out.println(Arrays.toString(s1).replaceAll("^\\[|\\]$", ""));

	}

}
