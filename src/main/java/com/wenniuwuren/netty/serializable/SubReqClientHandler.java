package main.java.com.wenniuwuren.netty.serializable;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import main.java.com.wenniuwuren.netty.pojo.SubscribeReq;


/**
 * @author wenniuwuren
 */
public class SubReqClientHandler extends SimpleChannelInboundHandler {

    /**
     * Creates a client-side handler.
     */
    public SubReqClientHandler() {
    }

    public void channelActive(ChannelHandlerContext ctx) {
        // 发送 10 条订购请求
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReq subReq(int i) {
        SubscribeReq req = new SubscribeReq();
        req.setAddress("china");
        req.setPhoneNumber("13899999999");
        req.setProductName("book");
        req.setSubReqID(i);
        req.setUserName("wenniuwuren");
        return req;
    }

    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // 收到消息已经是自动解码过的
        System.out.println("server response : [" + msg + "]");
    }


    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}