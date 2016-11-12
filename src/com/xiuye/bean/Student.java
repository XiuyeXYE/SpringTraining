package com.xiuye.bean;

public class Student {

	private String name;
	private String sex;
	private int age;
	private int level;



	public Student(String name, String sex, int age, int level) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.level = level;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", sex=" + sex + ", age=" + age + ", level=" + level + "]";
	}



}
