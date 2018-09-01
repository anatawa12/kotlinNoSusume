@file:Suppress("DeferredResultUnused")

package com.anatawa12.infraWorkshop.kotlinNoSusume.compatibility

import kotlinx.coroutines.experimental.async
import java.nio.channels.CompletionHandler
import kotlin.coroutines.experimental.Continuation
// 手動import
import kotlin.coroutines.experimental.intrinsics.suspendCoroutineOrReturn

/**
 * Created by anatawa12 on 2018/06/24.
 */
fun <T>caller(block: ICoroutineCaller<T>, handler: CompletionHandler<T, Nothing>) {
	async {
		try {
			handler.completed(suspendCoroutineOrReturn<T>{block(it)}, null)
		} catch (throwable: Throwable) {
			handler.failed(throwable, null)
		}
	}
}

interface ICoroutineCaller<T> {
	@Throws(Throwable::class)
	operator fun invoke(p1: Continuation<T>): Any?
}
