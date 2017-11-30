package com.wenniuwuren.netty.netty3;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 *  首先创建了NioServerSocketChannelFactory：创建boss线程池，创建worker线程池以及worker线程数。（boss线程数默认为1个）
    创建ServerBootstrap server端启动辅助类
   为ServerBootstrap设置ChannelPipelineFactory工厂，并为ChannelPipelineFactory将来创建出的ChannelPipeline设置编码器／解码器／事件处理器
   使用ServerBootstrap绑定监听地址和端口
 */
public class Server {
    public void start(){
        ChannelFactory factory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),//boss线程池
                Executors.newCachedThreadPool(),//worker线程池
                8//worker线程数
        );
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        /**
         * 对于每一个连接channel, server都会调用PipelineFactory为该连接创建一个ChannelPipline
         */
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("handler", new ServerChannelHandler());
                return pipeline;
            }
        });

        Channel channel = bootstrap.bind(new InetSocketAddress("127.0.0.1", 8091));
        System.out.println("server start success!");
    }

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}