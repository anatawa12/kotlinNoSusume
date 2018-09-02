package com.anatawa12.infraWorkshop.kotlinNoSusume.nullSafety;

import java.util.Objects;

/**
 * Created by anatawa12 on 2018/06/24.
 */
public class JavaSafe2ObjectMethodsCaller extends JavaUnsafeObjectMethodsCaller {
	@Override
	public boolean nullableEquals(Object o1, Object o2) {
		return Objects.equals(o1, o2);
	}

	@Override
	public int nullableHashCode(Object o1) {
		return Objects.hashCode(o1);
	}

	@Override
	public String nullableToString(Object o1) {
		return Objects.toString(o1);
	}
}
