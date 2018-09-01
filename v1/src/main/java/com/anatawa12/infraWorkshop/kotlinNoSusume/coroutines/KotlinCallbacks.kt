@file:Suppress("NAME_SHADOWING")

package com.anatawa12.infraWorkshop.kotlinNoSusume.coroutines

import com.anatawa12.infraWorkshop.kotlinNoSusume.coroutines.JavaCallBacks.PORT
import com.anatawa12.infraWorkshop.kotlinNoSusume.coroutines.JavaCallBacks.SIZE
import kotlinx.coroutines.experimental.nio.aConnect
import kotlinx.coroutines.experimental.nio.aRead
import kotlinx.coroutines.experimental.nio.aWrite
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousSocketChannel
import java.nio.channels.CompletionHandler
import java.util.*
import java.util.function.Consumer

/**
 * Created by anatawa12 on 2018/06/24.
 */
@Throws(IOException::class)
fun client1(sendMessage: ByteArray, data: Consumer<ByteArray>, error: Consumer<Throwable>) {
	val client = AsynchronousSocketChannel.open()
	client.connect<Void>(InetSocketAddress("localhost", PORT), null, object : CompletionHandler<Void, Void> {
		override fun completed(result: Void, attachment: Void) {
			println("CLIENT: Connected")
			val wBuf = ByteBuffer.wrap(sendMessage)
			wBuf.flip()

			client.write(wBuf, wBuf, object : CompletionHandler<Int, ByteBuffer> {
				override fun completed(result: Int?, attachment: ByteBuffer) {
					println("CLIENT: write " + result!!)
					if (result == 0) {
						error.accept(IOException("Write Error"))
						return
					}

					if (wBuf.hasRemaining()) {
						client.write(wBuf, wBuf, this)
						return
					}

					val s = ByteArrayOutputStream()
					val rBuf = ByteBuffer.allocate(SIZE)
					client.read(rBuf, rBuf, object : CompletionHandler<Int, ByteBuffer> {
						override fun completed(result: Int?, attachment: ByteBuffer) {
							println("CLIENT: read " + result!!)
							if (result < 0) {
								data.accept(s.toByteArray())
								println("CLIENT: done " + Arrays.toString(rBuf.array()))
								return
							}

							try {
								s.write(rBuf.array())
							} catch (e: IOException) {/*nop*/
							}

							if (rBuf.hasRemaining()) {
								client.read(rBuf, rBuf, this)
								return
							}

							data.accept(s.toByteArray())
							println("CLIENT: done " + Arrays.toString(rBuf.array()))
							try {
								client.close()
							} catch (e: IOException) {
							}

						}

						override fun failed(exc: Throwable, attachment: ByteBuffer) {
							error.accept(exc)
						}
					})
				}

				override fun failed(exc: Throwable, attachment: ByteBuffer) {
					error.accept(exc)
				}
			})
		}

		override fun failed(exc: Throwable, attachment: Void) {
			error.accept(exc)
		}
	})
}

@Throws(IOException::class)
fun client2(sendMessage: ByteArray, data: Consumer<ByteArray>, error: Consumer<Throwable>) {
	val client = AsynchronousSocketChannel.open()
	client.connect<Void>(InetSocketAddress("localhost", PORT), null, object : CompletionHandler<Void, Void> {
		override fun completed(result: Void, attachment: Void) {
			println("CLIENT: Connected")
			val wBuf = ByteBuffer.wrap(sendMessage)
			wBuf.flip()

			client.write(wBuf, wBuf, object : CompletionHandler<Int, ByteBuffer> {
				override fun completed(result: Int?, attachment: ByteBuffer) {
					println("CLIENT: write " + result!!)
					if (result == 0) {
						error.accept(IOException("Write Error"))
						return
					}

					if (!wBuf.hasRemaining()) {
						val s = ByteArrayOutputStream()
						val rBuf = ByteBuffer.allocate(SIZE)
						client.read(rBuf, rBuf, object : CompletionHandler<Int, ByteBuffer> {
							override fun completed(result: Int?, attachment: ByteBuffer) {
								println("CLIENT: read " + result!!)
								if (result < 0) {
									data.accept(s.toByteArray())
									println("CLIENT: done " + Arrays.toString(rBuf.array()))
									return
								}

								try {
									s.write(rBuf.array())
								} catch (e: IOException) {/*nop*/
								}

								if (rBuf.hasRemaining()) {
									data.accept(s.toByteArray())
									println("CLIENT: done " + Arrays.toString(rBuf.array()))
									try {
										client.close()
									} catch (e: IOException) {
									}
								} else client.read(rBuf, rBuf, this)

							}

							override fun failed(exc: Throwable, attachment: ByteBuffer) {
								error.accept(exc)
							}
						})
					} else {
						client.write(wBuf, wBuf, this)
					}
				}

				override fun failed(exc: Throwable, attachment: ByteBuffer) {
					error.accept(exc)
				}
			})
		}

		override fun failed(exc: Throwable, attachment: Void) {
			error.accept(exc)
		}
	})
}

@Throws(IOException::class)
suspend fun client3(sendMessage: ByteArray, data: Consumer<ByteArray>, error: Consumer<Throwable>) {
	val client = AsynchronousSocketChannel.open()
	client.aConnect(InetSocketAddress("localhost", PORT))
	println("CLIENT: Connected")
	val wBuf = ByteBuffer.wrap(sendMessage)
	wBuf.flip()

	client.write(wBuf, wBuf, object : CompletionHandler<Int, ByteBuffer> {
		override fun completed(result: Int?, attachment: ByteBuffer) {
			println("CLIENT: write " + result!!)
			if (result == 0) {
				error.accept(IOException("Write Error"))
				return
			}

			if (!wBuf.hasRemaining()) {
				val s = ByteArrayOutputStream()
				val rBuf = ByteBuffer.allocate(SIZE)
				client.read(rBuf, rBuf, object : CompletionHandler<Int, ByteBuffer> {
					override fun completed(result: Int?, attachment: ByteBuffer) {
						println("CLIENT: read " + result!!)
						if (result < 0) {
							data.accept(s.toByteArray())
							println("CLIENT: done " + Arrays.toString(rBuf.array()))
							return
						}

						try {
							s.write(rBuf.array())
						} catch (e: IOException) {/*nop*/
						}

						if (rBuf.hasRemaining()) {
							data.accept(s.toByteArray())
							println("CLIENT: done " + Arrays.toString(rBuf.array()))
							try {
								client.close()
							} catch (e: IOException) {
							}
						} else client.read(rBuf, rBuf, this)

					}

					override fun failed(exc: Throwable, attachment: ByteBuffer) {
						error.accept(exc)
					}
				})
			} else {
				client.write(wBuf, wBuf, this)
			}
		}

		override fun failed(exc: Throwable, attachment: ByteBuffer) {
			error.accept(exc)
		}
	})
}

@Throws(IOException::class)
suspend fun client4(sendMessage: ByteArray, data: Consumer<ByteArray>, error: Consumer<Throwable>) {
	val client = AsynchronousSocketChannel.open()
	client.aConnect(InetSocketAddress("localhost", PORT))
	println("CLIENT: Connected")
	val wBuf = ByteBuffer.wrap(sendMessage)
	wBuf.flip()

	val result = client.aWrite(wBuf)

	do {
		println("CLIENT: write " + result)
		if (result == 0) {
			error.accept(IOException("Write Error"))
			return
		}


		val s = ByteArrayOutputStream()
		val rBuf = ByteBuffer.allocate(SIZE)
		client.read(rBuf, rBuf, object : CompletionHandler<Int, ByteBuffer> {
			override fun completed(result: Int?, attachment: ByteBuffer) {
				println("CLIENT: read " + result!!)
				if (result < 0) {
					data.accept(s.toByteArray())
					println("CLIENT: done " + Arrays.toString(rBuf.array()))
					return
				}

				try {
					s.write(rBuf.array())
				} catch (e: IOException) {/*nop*/
				}

				if (rBuf.hasRemaining()) {
					data.accept(s.toByteArray())
					println("CLIENT: done " + Arrays.toString(rBuf.array()))
					try {
						client.close()
					} catch (e: IOException) {
					}
				} else client.read(rBuf, rBuf, this)

			}

			override fun failed(exc: Throwable, attachment: ByteBuffer) {
				error.accept(exc)
			}
		})
	} while (wBuf.hasRemaining())
}

@Throws(IOException::class)
suspend fun client5(sendMessage: ByteArray, data: Consumer<ByteArray>, error: Consumer<Throwable>) {
	val client = AsynchronousSocketChannel.open()
	client.aConnect(InetSocketAddress("localhost", PORT))
	println("CLIENT: Connected")
	val wBuf = ByteBuffer.wrap(sendMessage)
	wBuf.flip()

	val result = client.aWrite(wBuf)

	do {
		println("CLIENT: write $result")
		if (result == 0) {
			error.accept(IOException("Write Error"))
			return
		}


		val s = ByteArrayOutputStream()
		val rBuf = ByteBuffer.allocate(SIZE)
		val result = client.aRead(rBuf)
		do {
			println("CLIENT: read $result")
			if (result < 0) {
				data.accept(s.toByteArray())
				println("CLIENT: done " + Arrays.toString(rBuf.array()))
				return
			}

			try {
				s.write(rBuf.array())
			} catch (e: IOException) {/*nop*/
			}

			data.accept(s.toByteArray())
			println("CLIENT: done " + Arrays.toString(rBuf.array()))
			try {
				client.close()
			} catch (e: IOException) {
			}
		} while (rBuf.hasRemaining())
	} while (wBuf.hasRemaining())
}

suspend fun client6(sendMessage: ByteArray): ByteArray? {
	AsynchronousSocketChannel.open().use { client ->
		client.aConnect(InetSocketAddress("localhost", PORT))
		println("CLIENT: Connected")
		val wBuf = ByteBuffer.wrap(sendMessage)
		wBuf.flip()

		do {
			val result = client.aWrite(wBuf)
			println("CLIENT: write $result")
			if (result == 0) {
				throw IOException("Write Error")
			}
		} while (wBuf.hasRemaining())

		val s = ByteArrayOutputStream()
		val rBuf = ByteBuffer.allocate(SIZE)
		do {
			val result = client.aRead(rBuf)
			println("CLIENT: read $result")
			if (result < 0) break

			s.write(rBuf.array())
		} while (rBuf.hasRemaining())

		println("CLIENT: done " + Arrays.toString(rBuf.array()))
		return s.toByteArray()
	}
}