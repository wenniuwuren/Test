package com.wenniuwuren.netty.serializable;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import com.wenniuwuren.netty.pojo.SubscribeReq;
import com.wenniuwuren.netty.pojo.SubscribeResp;


/**
 * 订单服务处理
 * @author wenniuwuren
 */
@Sharable
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // 经过 ObjectDecoder 的解码，接收到的请求消息已经被自动解码为 SubscribeReq 对象
        SubscribeReq req = (SubscribeReq) msg;
        if ("wenniuwuren".equalsIgnoreCase(req.getUserName())) {
            System.out.println("from client : ["
                    + req.toString() + "]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }

    private SubscribeResp resp(int subReqID) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(subReqID);
        resp.setRespCode(200);
        resp.setDesc("the book will deliver in 1 day");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();// 发生异常，关闭链路
    }
}