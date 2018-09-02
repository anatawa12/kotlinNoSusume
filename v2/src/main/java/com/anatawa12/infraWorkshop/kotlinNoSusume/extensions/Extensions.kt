@file:Suppress("unused")

package com.anatawa12.infraWorkshop.kotlinNoSusume.extensions

import java.nio.charset.Charset
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by anatawa12 on 2018/06/24.
 */
private val sjis by lazy { Charset.forName("SJIS") }
val Charsets.SJIS
		get() = sjis

inline fun Lock.with(block: () -> Unit) {
	lock()
	try {
		block()
	} finally {
		unlock()
	}
}

class ExLock{
	companion object {
		@JvmStatic
		fun with(lock: Lock, block: () -> Unit) {
			lock.lock()
			try {
				block()
			} finally {
				lock.unlock()
			}
		}
	}
}

fun main(args: Array<String>) {
	val lock = ReentrantLock()

	ExLock.with(lock, {

	})

	lock.with({

	})
}