package com.aseubel.io.im.server.main;

import com.aseubel.io.im.server.session.Session;
import com.aseubel.io.im.server.session.SessionManager;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器心跳监控
 * @author Aseubel
 * @date 2025/9/28 上午12:26
 */
public class HeartBeatMonitor implements Runnable {
    // 心跳超时时间，例如 15 秒
    private static final long HEARTBEAT_TIMEOUT = 15_000;

    @Override
    public void run() {
        while (true) {
            try {
                // 每 5 秒检查一次
                Thread.sleep(5000);
                long now = System.currentTimeMillis();

                // 为了避免在遍历时修改，复制一份进行检查
                Map<String, Session> allSessions = new ConcurrentHashMap<>(SessionManager.getInstance().getAllSessions());

                for (Session session : allSessions.values()) {
                    if (now - session.getLastHeartbeatTime() > HEARTBEAT_TIMEOUT) {
                        System.out.println("Client " + session.getUserId() + " timed out. Closing connection.");
                        SocketChannel channel = session.getChannel();
                        SessionManager.getInstance().logout(channel);
                        try {
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
