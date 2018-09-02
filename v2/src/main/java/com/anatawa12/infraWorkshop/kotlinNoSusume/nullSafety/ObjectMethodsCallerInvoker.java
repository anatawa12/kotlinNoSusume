package com.anatawa12.infraWorkshop.kotlinNoSusume.nullSafety;

/**
 * Created by anatawa12 on 2018/06/24.
 */
@SuppressWarnings("ConstantConditions")
public class ObjectMethodsCallerInvoker {
	public static void main(String[] args) {
		//ObjectMethodsCaller caller = new JavaUnsafeObjectMethodsCaller();
		//ObjectMethodsCaller caller = new JavaSafe1ObjectMethodsCaller();
		//ObjectMethodsCaller caller = new JavaSafe2ObjectMethodsCaller();
		//ObjectMethodsCaller caller = new Kotlin1ObjectMethodsCaller();
		ObjectMethodsCaller caller = new Kotlin2ObjectMethodsCaller();

		System.out.println();
		System.out.println("nullableEquals(\"foo\", \"foo\")");
		try {
			System.out.println(caller.nullableEquals("foo", "foo"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
		System.out.println("nullableEquals(\"foo\", \"bar\")");
		try {
			System.out.println(caller.nullableEquals("foo", "bar"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
		System.out.println("nullableEquals(null, null)");
		try {
			System.out.println(caller.nullableEquals(null, null));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		System.out.println();

		System.out.println();
		System.out.println("nonNullableEquals(\"foo\", \"foo\")");
		try {
			System.out.println(caller.nonNullableEquals("foo", "foo"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
		System.out.println("nonNullableEquals(\"foo\", \"bar\")");
		try {
			System.out.println(caller.nonNullableEquals("foo", "bar"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
		System.out.println("nonNullableEquals(null, null)");
		try {
			System.out.println(caller.nonNullableEquals(null, null));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		System.out.println();
		System.out.println();

		System.out.println();
		System.out.println("nullableHashCode(\"foo\")");
		try {
			System.out.println(caller.nullableHashCode("foo"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
		System.out.println("nullableHashCode(null)");
		try {
			System.out.println(caller.nullableHashCode(null));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		System.out.println();

		System.out.println();
		System.out.println("nonNullableHashCode(\"foo\")");
		try {
			System.out.println(caller.nonNullableHashCode("foo"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
		System.out.println("nonNullableHashCode(null)");
		try {
			System.out.println(caller.nonNullableHashCode(null));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		System.out.println();
		System.out.println();

		System.out.println();
		System.out.println("nullableToString(\"foo\")");
		try {
			System.out.println(caller.nullableToString("foo"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
		System.out.println("nullableToString(null)");
		try {
			System.out.println(caller.nullableToString(null));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		System.out.println();

		System.out.println();
		System.out.println("nonNullableToString(\"foo\")");
		try {
			System.out.println(caller.nonNullableToString("foo"));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
		System.out.println("nonNullableToString(null)");
		try {
			System.out.println(caller.nonNullableToString(null));
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
