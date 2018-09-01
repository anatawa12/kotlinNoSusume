package com.anatawa12.infraWorkshop.kotlinNoSusume.singletons;

/**
 * Created by anatawa12 on 2018/06/24.
 */
public class JavaSingleTonFromIDEA {
	private static JavaSingleTonFromIDEA ourInstance = new JavaSingleTonFromIDEA();

	public static JavaSingleTonFromIDEA getInstance() {
		return ourInstance;
	}

	private JavaSingleTonFromIDEA() {
	}

	public static void main(String[] args) {
		System.out.println(KotlinObject.INSTANCE.getInts());
	}
}
