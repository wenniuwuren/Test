package com.wenniuwuren.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author lilinfeng
 * @version 1.0
 * @date 2014年2月16日
 */
public class ReadCompletionHandler implements
        CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        if (this.channel == null)
            this.channel = channel;
    }


    public void completed(Integer result, ByteBuffer attachment) {
        // 从缓冲区写入转换为读
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        // 从 buffer 传到 body
        attachment.get(body);
        try {
            String req = new String(body, "UTF-8");
            System.out.println("The time server receive order : " + req);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new java.util.Date(
                    System.currentTimeMillis()).toString() : "BAD ORDER";
            doWrite(currentTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // 发送给客户端
    private void doWrite(String currentTime) {
        if (currentTime != null && currentTime.trim().length() > 0) {
            byte[] bytes = (currentTime).getBytes();
            // 发送缓冲区
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer,
                    new CompletionHandler<Integer, ByteBuffer>() {

                        public void completed(Integer result, ByteBuffer buffer) {
                            // 如果没有发送完成，继续发送
                            if (buffer.hasRemaining())
                                channel.write(buffer, buffer, this);
                        }

                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try {
                                channel.close();
                            } catch (IOException e) {
                                // ingnore on close
                            }
                        }
                    });
        }
    }

    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            // 只要发生了读写异常，就关闭链路，释放资源
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}