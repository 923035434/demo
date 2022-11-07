package com.example.netty.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServiceHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channels.forEach(c->{
            if(channel!=c){
               c.writeAndFlush(channel.remoteAddress() + "发送的消息：" + msg + "\n");
            }
        });
       System.out.println(ctx.channel().remoteAddress()+","+msg);
        //ctx.channel().writeAndFlush("from service: "+ UUID.randomUUID());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        Channel channel = ctx.channel();
        channels.writeAndFlush("【客户端】 - " + channel.remoteAddress() + "加入连接\r\n");
        channels.add(channel);
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        Channel channel = ctx.channel();
        channels.writeAndFlush("【客户端】 - " + channel.remoteAddress() + "退出连接\n\n");
//        会自动remove，可以不用手动remove
//        channels.remove(channel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
