package com.aseubel.io.im.server.main;

import com.aseubel.io.im.common.handler.MessageHandler;
import com.aseubel.io.im.common.protocol.Message;
import com.aseubel.io.im.server.handler.ChatRoomHandler;
import com.aseubel.io.im.server.handler.SingleChatHandler;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aseubel
 * @date 2025/9/28 上午11:23
 */
public class HandlerDispatcher {
    private final Map<String, MessageHandler> handlers = new HashMap<>();

    public HandlerDispatcher() {
        // 在启动时注册所有处理器
        handlers.put("SINGLE_CHAT", new SingleChatHandler());
        handlers.put("CHAT_ROOM", new ChatRoomHandler());
    }

    public void dispatch(Message message, SocketChannel channel) {
        MessageHandler handler = handlers.get(message.getType());
        if (handler != null) {
            handler.handle(message, channel);
        } else {
            System.err.println("No handler found for message type: " + message.getType());
        }
    }
}
