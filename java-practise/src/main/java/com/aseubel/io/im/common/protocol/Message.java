package com.aseubel.io.im.common.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * 消息协议定义
 * @author Aseubel
 * @date 2025/9/28 上午12:28
 */
@Getter
@Setter
public class Message {
    private String type; // LOGIN, SINGLE_CHAT, CHAT_ROOM, HEARTBEAT, SYSTEM
    private String from; // 发送方 User ID
    private String to;   // 接收方 User ID or "ALL" for chat room
    private String content; // 消息内容

    // Getters and Setters
}
