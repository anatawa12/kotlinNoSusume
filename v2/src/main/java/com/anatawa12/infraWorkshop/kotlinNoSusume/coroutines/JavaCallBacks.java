package com.anatawa12.infraWorkshop.kotlinNoSusume.coroutines;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by anatawa12 on 2018/06/24.
 */
@SuppressWarnings("CatchMayIgnoreException")
public class JavaCallBacks {
	public static int SIZE = 100;
	public static int PORT = 80;

	public static void client(byte[] sendMessage, Consumer<byte[]> data, Consumer<Throwable> error) throws IOException {
		AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
		client.connect(new InetSocketAddress("localhost", PORT), null, new CompletionHandler<Void, Void>() {
			@Override
			public void completed(Void result, Void attachment) {
				System.out.println("CLIENT: Connected");
				ByteBuffer wBuf = ByteBuffer.wrap(sendMessage);
				wBuf.flip();

				client.write(wBuf, wBuf, new CompletionHandler<Integer, ByteBuffer>() {
					@Override
					public void completed(Integer result, ByteBuffer attachment) {
						System.out.println("CLIENT: write "+result);
						if(result == 0){
							error.accept(new IOException("Write Error"));
							return;
						}

						if(wBuf.hasRemaining()){
							client.write(wBuf, wBuf, this);
							return;
						}

						ByteArrayOutputStream s = new ByteArrayOutputStream();
						ByteBuffer rBuf = ByteBuffer.allocate(SIZE);
						client.read(rBuf, rBuf, new CompletionHandler<Integer, ByteBuffer>() {
							@Override
							public void completed(Integer result, ByteBuffer attachment) {
								System.out.println("CLIENT: read "+result);
								if(result < 0){
									data.accept(s.toByteArray());
									System.out.println("CLIENT: done "+Arrays.toString(rBuf.array()));
									return;
								}

								try {
									s.write(rBuf.array());
								} catch (IOException e){/*nop*/}

								if(rBuf.hasRemaining()){
									client.read(rBuf, rBuf, this);
									return;
								}

								data.accept(s.toByteArray());
								System.out.println("CLIENT: done "+Arrays.toString(rBuf.array()));
								try {
									client.close();
								} catch(IOException e){
								}
							}

							@Override
							public void failed(Throwable exc, ByteBuffer attachment) {
								error.accept(exc);
							}
						});
					}
					@Override
					public void failed(Throwable exc, ByteBuffer attachment) {
						error.accept(exc);
					}
				});
			}
			@Override
			public void failed(Throwable exc, Void attachment) {
				error.accept(exc);
			}
		});
	}

}
