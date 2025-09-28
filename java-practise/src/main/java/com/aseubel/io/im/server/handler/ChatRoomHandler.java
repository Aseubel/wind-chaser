package com.aseubel.io.im.server.handler;

import com.aseubel.io.im.common.handler.MessageHandler;
import com.aseubel.io.im.common.protocol.Message;
import com.aseubel.io.im.server.session.Session;
import com.aseubel.io.im.server.session.SessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * 聊天室消息处理器
 * @author Aseubel
 * @date 2025/9/28 上午12:27
 */
public class ChatRoomHandler implements MessageHandler {

    @Override
    public void handle(Message message, SocketChannel channel) {
        Map<String, Session> allSessions = SessionManager.getInstance().getAllSessions();
        Gson gson = new Gson();
        String jsonMessage = gson.toJson(message);

        // 遍历所有在线用户，广播消息（除了发送者自己）
        for (Session session : allSessions.values()) {
            if (!session.getUserId().equals(message.getFrom())) {
                try {
                    SessionManager.getInstance().sendMessage(session.getChannel(), jsonMessage);
                } catch (IOException e) {
                    System.err.println("Failed to send chat room message to " + session.getUserId());
                    e.printStackTrace();
                }
            }
        }
    }
}
