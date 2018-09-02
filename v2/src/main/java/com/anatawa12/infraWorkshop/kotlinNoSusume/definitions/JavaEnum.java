package com.anatawa12.infraWorkshop.kotlinNoSusume.definitions;

/**
 * Created by anatawa12 on 2018/06/24.
 */
public enum JavaEnum {
	Value1, Value2(0)
	;
	final int value;
	JavaEnum() {
		this(0);
	}

	JavaEnum(int value) {
		this.value = value;
	}
}
