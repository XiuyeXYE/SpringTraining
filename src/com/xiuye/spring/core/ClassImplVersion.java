package com.xiuye.spring.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.Manifest;
import java.util.jar.Attributes.Name;

import org.springframework.core.SpringVersion;

public class ClassImplVersion {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Manifest m = new Manifest(new FileInputStream("src/META-INF/MANIFEST.MF"));
		System.out.println(m.getEntries());
		System.out.println(m.getMainAttributes().entrySet());
		System.out.println(m.getMainAttributes().getValue(Name.IMPLEMENTATION_TITLE));
		System.out.println(m.getMainAttributes().getValue(Name.IMPLEMENTATION_VERSION));

		System.out.println(ClassImplVersion.class.getPackage());
		System.out.println(ClassImplVersion.class.getPackage().getImplementationVendor());
		System.out.println(ClassImplVersion.class.getPackage().getImplementationTitle());
		System.out.println(ClassImplVersion.class.getPackage().getImplementationVersion());
		System.out.println(SpringVersion.getVersion());

	}

}
