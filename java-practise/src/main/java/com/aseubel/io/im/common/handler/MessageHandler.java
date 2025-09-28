package com.aseubel.io.im.common.handler;

import com.aseubel.io.im.common.protocol.Message;

import java.nio.channels.SocketChannel;

/**
 * @author Aseubel
 * @date 2025/9/28 上午11:17
 */
public interface MessageHandler {
    void handle(Message message, SocketChannel channel);
}
