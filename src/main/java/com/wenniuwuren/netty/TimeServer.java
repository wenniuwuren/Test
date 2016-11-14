package com.wenniuwuren.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import main.java.com.wenniuwuren.netty.TimeServerHandler;

/**
 * Created by hzzhuyibin on 2016/8/8.
 */
public class TimeServer {

    public void bind(int port) throws Exception{
        // 配置服务端的 NIO 线程组  一个用于服务端接收客户端的连接，另一个用于进行 SocketChannel 的网络读写
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 启动辅助类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChildChannelHandler());

            // 绑定端口，同步等待成功
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            // 等待服务端监听端口关闭   类似 JDK 的 Future
            channelFuture.channel().closeFuture().sync();
        } finally {

            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    // 主要用于处理网络 IO 事件，例如记录日志、对消息进行编解码等
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel arg0) throws Exception {
            // 新增两个解码器
            arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
            arg0.pipeline().addLast(new StringDecoder());
            arg0.pipeline().addLast(new TimeServerHandler());
        }

    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new TimeServer().bind(port);
    }

}

