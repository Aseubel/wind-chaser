package com.aseubel.io.im.server.handler;

import com.aseubel.io.im.common.handler.MessageHandler;
import com.aseubel.io.im.common.protocol.Message;
import com.aseubel.io.im.server.session.Session;
import com.aseubel.io.im.server.session.SessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * 单聊消息处理器
 * @author Aseubel
 * @date 2025/9/28 上午12:27
 */
public class SingleChatHandler implements MessageHandler {

    @Override
    public void handle(Message message, SocketChannel channel) {
        Session toSession = SessionManager.getInstance().getSession(message.getTo());

        // 如果对方在线，直接转发
        if (toSession != null) {
            try {
                Gson gson = new Gson();
                String jsonMessage = gson.toJson(message);
                SessionManager.getInstance().sendMessage(toSession.getChannel(), jsonMessage);
            } catch (IOException e) {
                System.err.println("Failed to send single chat message to " + message.getTo());
                e.printStackTrace();
            }
        } else {
            // 对方不在线，可以实现离线消息逻辑，这里仅作提示
            System.out.println("User " + message.getTo() + " is offline. Message from " + message.getFrom() + " not sent.");
            // 可以回复发送者一个系统消息
            sendSystemMessageToSender(message.getFrom(), "User " + message.getTo() + " is offline.");
        }
    }

    private void sendSystemMessageToSender(String fromUserId, String content) {
        Session fromSession = SessionManager.getInstance().getSession(fromUserId);
        if (fromSession != null) {
            Message systemMessage = new Message();
            systemMessage.setType("SYSTEM");
            systemMessage.setFrom("System");
            systemMessage.setTo(fromUserId);
            systemMessage.setContent(content);
            try {
                Gson gson = new Gson();
                String jsonMessage = gson.toJson(systemMessage);
                SessionManager.getInstance().sendMessage(fromSession.getChannel(), jsonMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
