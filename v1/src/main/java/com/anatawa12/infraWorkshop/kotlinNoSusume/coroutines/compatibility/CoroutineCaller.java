package com.anatawa12.infraWorkshop.kotlinNoSusume.coroutines.compatibility;

import com.anatawa12.infraWorkshop.kotlinNoSusume.coroutines.KotlinCallbacksKt;

import java.nio.channels.CompletionHandler;

/**
 * Created by anatawa12 on 2018/06/24.
 */
public class CoroutineCaller {
	public static void call() {
		CoroutineWrapperForJavaKt.<byte[]>caller((fun) -> KotlinCallbacksKt.client6("".getBytes(), fun), new CompletionHandler<byte[], Object>() {
			@Override
			public void completed(byte[] result, Object attachment) {

			}

			@Override
			public void failed(Throwable exc, Object attachment) {

			}
		});
	}
}
