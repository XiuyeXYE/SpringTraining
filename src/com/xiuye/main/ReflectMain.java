package com.xiuye.main;

import java.lang.reflect.Field;

import com.xiuye.bean.Student;

public class ReflectMain {

	public static void main(String[] args) {
		try {
			Class<Student> clazz = Student.class;
			Student s = new Student();

			Field f = clazz.getDeclaredField("age");
			f.setAccessible(true);
			System.out.println(f.getType().getSimpleName());
			f.set(s, 2);
			System.out.println(s);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
