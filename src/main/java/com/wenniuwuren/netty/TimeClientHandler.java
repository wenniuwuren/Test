package com.wenniuwuren.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Logger;

/**
 * Created by hzzhuyibin on 2016/8/8.
 */
public class TimeClientHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = Logger
            .getLogger(TimeClientHandler.class.getName());

    private int counter;

    private byte[] req;

    /**
     * Creates a client-side handler.
     */
    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator"))
                .getBytes();
    }

    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message = null;
        System.out.println("client " + ctx.channel().remoteAddress() + "connected");
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8");
        // 已经是解码成字符串之后的应答消息了
        String body = (String) msg;
        System.out.println("Now is : " + body + " ; the counter is : "
                + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 释放资源
        logger.warning("Unexpected exception from downstream : "
                + cause.getMessage());
        ctx.close();
    }
}

