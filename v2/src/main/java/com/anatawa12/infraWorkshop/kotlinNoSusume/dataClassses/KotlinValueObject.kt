package com.anatawa12.infraWorkshop.kotlinNoSusume.dataClassses

import com.anatawa12.infraWorkshop.kotlinNoSusume.definitions.KotlinClass

/**
 * Created by anatawa12 on 2018/06/24.
 */
data class KotlinValueObject(val name: String, val cost: Int) : KotlinClass("")

fun main(args: Array<String>) {
	println(KotlinValueObject("anatawa12", 100))
}

class AAAA {
	fun String.aaaa() = ""
}
