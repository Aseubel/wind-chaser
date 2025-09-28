package com.aseubel.io.im.client;

import com.aseubel.io.im.common.protocol.Message;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 客户端消息处理器
 * @author Aseubel
 * @date 2025/9/28 上午12:24
 */
public class ClientMessageHandler implements Runnable {
    private final SocketChannel socketChannel;

    public ClientMessageHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int bytesRead = socketChannel.read(buffer);

                if (bytesRead > 0) {
                    buffer.flip();
                    String receivedMessage = new String(buffer.array(), 0, bytesRead).trim();
                    Gson gson = new Gson();
                    Message message = gson.fromJson(receivedMessage, Message.class);

                    // 在控制台打印收到的消息
                    displayMessage(message);

                } else if (bytesRead == -1) {
                    System.out.println("Server has closed the connection.");
                    socketChannel.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Connection to server was lost.");
            // 可以在这里实现重连逻辑
        }
    }

    private void displayMessage(Message msg) {
        switch (msg.getType()) {
            case "SINGLE_CHAT":
                System.out.println("\n[" + msg.getFrom() + " -> You]: " + msg.getContent());
                break;
            case "CHAT_ROOM":
                System.out.println("\n[ChatRoom - " + msg.getFrom() + "]: " + msg.getContent());
                break;
            case "SYSTEM":
                System.out.println("\n[System]: " + msg.getContent());
                break;
            default:
                System.out.println("\nReceived unknown message type: " + msg.getType());
        }
    }
}
