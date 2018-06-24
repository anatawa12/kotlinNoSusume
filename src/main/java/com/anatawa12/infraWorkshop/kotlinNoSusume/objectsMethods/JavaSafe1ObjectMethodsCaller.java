package com.anatawa12.infraWorkshop.kotlinNoSusume.objectsMethods;

/**
 * Created by anatawa12 on 2018/06/24.
 */
public class JavaSafe1ObjectMethodsCaller extends JavaUnsafeObjectMethodsCaller {
	@Override
	public boolean nullableEquals(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	@Override
	public int nullableHashCode(Object o1) {
		return o1 != null ? o1.hashCode() : 0;
	}

	@Override
	public String nullableToString(Object o1) {
		return o1 != null ? o1.toString() : "null";
	}
}
