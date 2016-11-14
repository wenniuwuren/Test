package com.wenniuwuren.nio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by hzzhuyibin on 2016/8/7.
 */
public class AcceptCompletionHandler implements
        CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        // 完成后 accept 才能不断地接收客户端信息
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // dst:接收缓冲区，用于从一部 Channel 中读取数据包， attachment:异步channel携带的附件，通知回调作为入参舒勇
        // handler 接收通知回调的业务handler
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}

