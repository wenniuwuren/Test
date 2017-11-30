package com.wenniuwuren.netty.netty3;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;

/**
 * 监听与服务端连接成功事件，连接成功后，写消息给服务端
 * 监听向服务端写消息完成的事件
 * 监听接收到来自服务端的消息
 * 捕捉异常事件
 */
public class ClientChannelHandler extends SimpleChannelHandler {
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("client connect success!");
        String str = "hi server!";
        e.getChannel().write(str);//异步
    }

    @Override
    public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
        System.out.println("client write finished");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        String msg = (String) e.getMessage();
        System.out.println("client receive message, msg: " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}