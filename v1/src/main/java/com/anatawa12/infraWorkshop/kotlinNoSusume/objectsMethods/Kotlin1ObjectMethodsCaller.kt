package com.anatawa12.infraWorkshop.kotlinNoSusume.objectsMethods

/**
 * Created by anatawa12 on 2018/06/24.
 */
class Kotlin1ObjectMethodsCaller : ObjectMethodsCaller {
	override fun nullableEquals(o1: Any?, o2: Any?): Boolean {
		return o1 == o2
	}

	override fun nonNullableEquals(o1: Any, o2: Any): Boolean {
		return o1 == o2
	}

	override fun nullableHashCode(o1: Any?): Int {
		return o1?.hashCode() ?: 0
	}

	override fun nonNullableHashCode(o1: Any): Int {
		return o1.hashCode()
	}

	override fun nullableToString(o1: Any?): String {
		return o1?.toString() ?: "null"
	}

	override fun nonNullableToString(o1: Any): String {
		return o1.toString()
	}

}