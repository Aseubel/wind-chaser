package com.aseubel.io.im.server.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 会話管理器
 * @author Aseubel
 * @date 2025/9/28 上午12:27
 */
public class SessionManager {
    private static final SessionManager instance = new SessionManager();
    // UserId -> Session
    private final Map<String, Session> userSessions = new ConcurrentHashMap<>();
    // SocketChannel -> UserId
    private final Map<SocketChannel, String> channelUserMap = new ConcurrentHashMap<>();

    private SessionManager() {}

    public static SessionManager getInstance() {
        return instance;
    }

    public void login(String userId, SocketChannel channel) {
        System.out.println(userId + " logged in.");
        Session session = new Session(userId, channel);
        userSessions.put(userId, session);
        channelUserMap.put(channel, userId);
    }

    public void logout(SocketChannel channel) {
        String userId = channelUserMap.remove(channel);
        if (userId != null) {
            userSessions.remove(userId);
            System.out.println(userId + " logged out.");
        }
    }

    public Session getSession(String userId) {
        return userSessions.get(userId);
    }

    public String getUserIdByChannel(SocketChannel channel) {
        return channelUserMap.get(channel);
    }

    public Map<String, Session> getAllSessions() {
        return userSessions;
    }

    public void updateHeartbeatTime(SocketChannel channel) {
        String userId = channelUserMap.get(channel);
        if(userId != null) {
            userSessions.get(userId).updateLastHeartbeatTime();
        }
    }

    public void sendMessage(SocketChannel channel, String message) throws IOException {
        channel.write(ByteBuffer.wrap(message.getBytes()));
    }
}
