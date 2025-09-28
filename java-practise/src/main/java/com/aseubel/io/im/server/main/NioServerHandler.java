package com.aseubel.io.im.server.main;

import com.aseubel.io.im.server.handler.ChatRoomHandler;
import com.aseubel.io.im.server.handler.SingleChatHandler;
import com.aseubel.io.im.common.protocol.Message;
import com.aseubel.io.im.server.session.SessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 服务器NIO事件处理器
 * @author Aseubel
 * @date 2025/9/28 上午12:26
 */
public class NioServerHandler implements Runnable {
    private Selector selector;
    private SelectionKey selectionKey;
    private final HandlerDispatcher dispatcher;

    public NioServerHandler(Selector selector, HandlerDispatcher dispatcher) {
        this.selector = selector;
        this.dispatcher = dispatcher;
    }

    public NioServerHandler(Selector selector, SelectionKey key, HandlerDispatcher dispatcher) {
        this.selector = selector;
        this.selectionKey = key;
        this.dispatcher = dispatcher;
    }

    public void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Client connected: " + clientChannel.getRemoteAddress());
    }

    @Override
    public void run() {
        try {
            handleRead(selectionKey);
        } catch (IOException e) {
            // 客户端异常断开
            handleClientDisconnect(selectionKey);
        }
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = clientChannel.read(buffer);

        if (bytesRead > 0) {
            buffer.flip();
            String receivedMessage = new String(buffer.array(), 0, bytesRead).trim();
            // 简单的粘包处理：假设一次读取一个完整的JSON
            Gson gson = new Gson();
            Message message = gson.fromJson(receivedMessage, Message.class);
            dispatch(message, clientChannel);
        } else if (bytesRead == -1) {
            // 客户端正常断开
            handleClientDisconnect(key);
        }
    }

    private void dispatch(Message message, SocketChannel channel) {
        switch (message.getType()) {
            case "LOGIN":
                SessionManager.getInstance().login(message.getFrom(), channel);
                break;
            case "HEARTBEAT":
                SessionManager.getInstance().updateHeartbeatTime(channel);
//                System.out.println("Received heartbeat from: " + SessionManager.getInstance().getUserIdByChannel(channel));
                break;
            default:
                dispatcher.dispatch(message, channel);
                break;
        }
    }

    private void handleClientDisconnect(SelectionKey key) {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        SessionManager.getInstance().logout(clientChannel);
        key.cancel();
        try {
            clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
