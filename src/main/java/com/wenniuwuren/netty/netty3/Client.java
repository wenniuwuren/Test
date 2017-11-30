package com.wenniuwuren.netty.netty3;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 *  首先创建了NioClientSocketChannelFactory：创建boss线程池，创建worker线程池以及worker线程数。（boss线程数默认为1个）
 *  创建ClientBootstrap client端启动辅助类
 *  为ClientBootstrap设置ChannelPipelineFactory工厂，并为ChannelPipelineFactory将来创建出的ChannelPipeline设置编码器／解码器／事件处理器
 *  使用ClientBootstrap连接Server端监听的地址和端口
 */
public class Client {
    public static void main(String[] args) {
        ChannelFactory factory = new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool(),
                8
        );
        ClientBootstrap bootstrap = new ClientBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("handler", new ClientChannelHandler());
                return pipeline;
            }
        });

        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8091));
        System.out.println("client start success!");
    }
}