
package com.wenniuwuren.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 简单应用程序只用到少量方法，所以继承 ChannelInboundHandlerAdapter（提供ChannelInboundHandler 默认实现） 就行
 * Created by hzzhuyibin on 2016/8/9.
 */
@ChannelHandler.Sharable  // 标识一个 ChannelHandler 可以被多个 Channel 安全地共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    // 每个传入消息都要调用
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // ByteBuf in = (ByteBuf) msg;
        System.out.println("receive from client :" + msg); // 输出在控制台
        ctx.write(msg); // 接收到的消息写给发送者，而不冲刷出站消息
    }

    // 当前批量读取中的最后一条消息
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//将未决消息(pending message 目前暂存在ChannelOutboundBuffer中的消息，
                                                // 在下次调用flush() 或者 writeAndFlush() 时将会尝试写到 Socket)
                                                // 冲刷到远程节点，并且关闭该 Channel
                .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 每个 Channel 都拥有一个与之相关联的 ChannelPipeline，其持有一个 ChannelHandler 的
     实例链。在默认的情况下， ChannelHandler 会把对它的方法的调用转发给链中的下一个 ChannelHandler。因此，如果 exceptionCaught()方法没有被该链中的某处实现，那么所接收的异常将会被
     传递到 ChannelPipeline 的尾端并被记录。为此，你的应用程序应该提供至少有一个实现了
     exceptionCaught()方法的 ChannelHandler。
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace(); // 打印异常
        ctx.close();
    }
}

