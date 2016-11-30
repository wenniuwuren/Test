
package com.wenniuwuren.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by hzzhuyibin on 2016/8/9.
 */
public class EchoClientHandler extends SimpleChannelInboundHandler {

    private int counter;

    static final String ECHO_REQ = "Hi, wenniuwuren. Welcome to Netty.$_";

    /**
     * Creates a client-side handler.
     */
    public EchoClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.buffer(ECHO_REQ
        // .getBytes().length);
        // buf.writeBytes(ECHO_REQ.getBytes());
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("This is " + ++counter + " times receive server : ["
                + msg + "]");
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

