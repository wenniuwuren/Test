
    package main.java.com.wenniuwuren.netty;

    import io.netty.buffer.ByteBuf;
    import io.netty.buffer.Unpooled;
    import io.netty.channel.ChannelFutureListener;
    import io.netty.channel.ChannelHandlerContext;
    import io.netty.channel.ChannelInboundHandlerAdapter;

    /**
     * Created by hzzhuyibin on 2016/8/9.
     */
    public class EchoServerHandler extends ChannelInboundHandlerAdapter {

        private int counter;

        public void channelRead(ChannelHandlerContext ctx, Object msg)
                throws Exception {
            System.out.println("receive from client :" + msg);
        }

        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4
                    .addListener(ChannelFutureListener.CLOSE);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            ctx.close();
        }
    }

