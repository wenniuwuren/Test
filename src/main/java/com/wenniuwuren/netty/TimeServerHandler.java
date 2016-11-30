package com.wenniuwuren.netty;

import io.netty.channel.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 每收到一条消息就计数一次，然后发送应答消息给客户端。
 * Created by hzzhuyibin on 2016/8/8.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8").substring(0, req.length
//                - System.getProperty("line.separator").length());
        // 接收到的 msg 就是回车换行后的请求消息，不需要考虑处理读半包问题，也不许要对消息进行编码
        String body = (String) msg;
        System.out.println("The time server receive order : " + body
                + " ; the counter is : " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
                System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }


}

