package com.anatawa12.infraWorkshop.kotlinNoSusume.nullSafety

import java.math.BigInteger

/**
 * Created by anatawa12 on 2018/09/02.
 */

fun main(args: Array<String>) {
	val string0: String = ""
	val string1: String? = null
	val bigInteger0: BigInteger = BigInteger.ONE
	val bigInteger1: BigInteger? = null
	val int1: Int = 0
}

fun returnInt(): Int {
	return 0
}
