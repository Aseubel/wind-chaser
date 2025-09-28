package com.aseubel.calculator.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel ch) {
                     ChannelPipeline p = ch.pipeline();
                     p.addLast(new StringDecoder());
                     p.addLast(new StringEncoder());
                     p.addLast(new ClientHandler());
                 }
             });

            Channel channel = b.connect("localhost", 8080).sync().channel();
            System.out.println("已连接到计算器服务器。输入 'exit' 退出。");
            
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Calculator> ");
                String line = scanner.nextLine();
                if ("exit".equalsIgnoreCase(line)) {
                    channel.close().sync();
                    break;
                }
                channel.writeAndFlush(line + "\n");
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}