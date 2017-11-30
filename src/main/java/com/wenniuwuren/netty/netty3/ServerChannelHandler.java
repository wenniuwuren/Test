package com.wenniuwuren.netty.netty3;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * 监听与客户端连接成功事件
 * 监听接收到来自客户端的消息，之后写回给客户端消息
 * 捕捉异常事件
 * 等等.....
 */
public class ServerChannelHandler extends SimpleChannelHandler {
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("connected, channel: " + e.getChannel().toString());
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        String msg = (String) e.getMessage();
        System.out.println("receive client message, msg: " + msg);

        Channel channel = e.getChannel();
        String str = "hi, client";

        channel.write(str);//写消息发给client端
        System.out.println("server send data: " + str + " finished");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}