package com.aseubel.io.im.server.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务器主程序
 * @author Aseubel
 * @date 2025/9/28 上午12:25
 */
public class ImServer {
    private static final int PORT = 8080;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private ExecutorService pool = Executors.newFixedThreadPool(10);
    private final HandlerDispatcher dispatcher = new HandlerDispatcher();

    public ImServer() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("IM Server started on port: " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(new HeartBeatMonitor()).start(); // 启动心跳监控

        while (true) {
            try {
                if (selector.select() > 0) {
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectedKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if (key.isAcceptable()) {
                            new NioServerHandler(selector, dispatcher).handleAccept(key);
                        }
                        if (key.isReadable()) {
                            pool.submit(new NioServerHandler(selector, key, dispatcher));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ImServer().start();
    }
}
