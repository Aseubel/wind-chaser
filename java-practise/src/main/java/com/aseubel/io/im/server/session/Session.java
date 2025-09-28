package com.aseubel.io.im.server.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.channels.SocketChannel;

/**
 * 会话对象
 * @author Aseubel
 * @date 2025/9/28 上午12:26
 */
@Getter
@Setter
@AllArgsConstructor
public class Session {
    private final String userId;
    private final SocketChannel channel;
    private long lastHeartbeatTime;

    public Session(String userId, SocketChannel channel) {
        this.userId = userId;
        this.channel = channel;
        this.lastHeartbeatTime = System.currentTimeMillis();
    }

    public void updateLastHeartbeatTime() {
        this.lastHeartbeatTime = System.currentTimeMillis();
    }
}
