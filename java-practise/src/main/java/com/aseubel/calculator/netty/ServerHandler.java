package com.aseubel.calculator.netty;

import com.aseubel.calculator.Calculator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private final Calculator calculator = new Calculator();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        String expression = msg.trim();
        System.out.println("接收到表达式: " + expression);

        String result;
        try {
            result = calculator.evaluate(expression);
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }
        
        System.out.println("计算结果: " + result);
        ctx.writeAndFlush(result + "\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.err.println("处理时发生错误：" + cause.getMessage());
        ctx.close();
    }
}