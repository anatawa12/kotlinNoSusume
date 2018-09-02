package com.anatawa12.infraWorkshop.kotlinNoSusume.nullSafety;

/**
 * Created by anatawa12 on 2018/06/24.
 */
@SuppressWarnings("NullableProblems")
public class JavaUnsafeObjectMethodsCaller implements ObjectMethodsCaller {
	@Override
	public boolean nullableEquals(Object o1, Object o2) {
		return o1.equals(o2);
	}

	@Override
	public boolean nonNullableEquals(Object o1, Object o2) {
		return o1.equals(o2);
	}

	@Override
	public int nullableHashCode(Object o1) {
		return o1.hashCode();
	}

	@Override
	public int nonNullableHashCode(Object o1) {
		return o1.hashCode();
	}

	@Override
	public String nullableToString(Object o1) {
		return o1.toString();
	}

	@Override
	public String nonNullableToString(Object o1) {
		return o1.toString();
	}
}
