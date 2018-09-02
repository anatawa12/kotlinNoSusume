package com.anatawa12.infraWorkshop.kotlinNoSusume.definitions

/**
 * Created by anatawa12 on 2018/06/24.
 */
@Suppress("ConvertSecondaryConstructorToPrimary")
enum class KotlinEnum {
	Value1, Value2(2)
	;
	val value: Int
	constructor(value: Int = 0){
		this.value = value
	}
}

fun main(args: Array<String>) {
	println(KotlinEnum.Value2.value)
}