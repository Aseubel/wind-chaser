package com.aseubel.io.im.client;

import com.aseubel.io.im.common.protocol.Message;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 客户端主程序
 * @author Aseubel
 * @date 2025/9/28 上午12:24
 */
public class ImClient {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;
    private SocketChannel socketChannel;
    private String userId;

    public ImClient(String userId) {
        this.userId = userId;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
            socketChannel.configureBlocking(false);
            System.out.println("Connected to IM server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        // 登录
        login();

        // 启动心跳
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new HeartBeatTask(this), 0, 5, TimeUnit.SECONDS);

        // 启动一个线程来读取服务器消息
        new Thread(new ClientMessageHandler(socketChannel)).start();

        // 主线程用于发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            processUserInput(line);
        }
    }

    private void login() {
        Message loginMsg = new Message();
        loginMsg.setType("LOGIN");
        loginMsg.setFrom(this.userId);
        sendMessage(loginMsg);
    }

    private void processUserInput(String line) {
        // 简单协议: "toUserId:message" for single chat, "all:message" for chat room
        String[] parts = line.split(":", 2);
        if (parts.length == 2) {
            Message msg = new Message();
            msg.setFrom(this.userId);
            msg.setContent(parts[1]);
            if ("all".equalsIgnoreCase(parts[0])) {
                msg.setType("CHAT_ROOM");
                msg.setTo("ALL");
            } else {
                msg.setType("SINGLE_CHAT");
                msg.setTo(parts[0]);
            }
            sendMessage(msg);
        }
    }

    public void sendMessage(Message message) {
        try {
            Gson gson = new Gson();
            String jsonMessage = gson.toJson(message);
            socketChannel.write(ByteBuffer.wrap(jsonMessage.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ImClient <userId>");
            return;
        }
        new ImClient(args[0]).start();
    }
}
