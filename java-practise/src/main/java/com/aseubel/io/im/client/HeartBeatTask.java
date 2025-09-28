package com.aseubel.io.im.client;

import com.aseubel.io.im.common.protocol.Message;

/**
 * 客户端心跳任务
 * @author Aseubel
 * @date 2025/9/28 上午12:24
 */
public class HeartBeatTask implements Runnable {
    private final ImClient client;

    public HeartBeatTask(ImClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Message heartbeatMsg = new Message();
            heartbeatMsg.setType("HEARTBEAT");
            client.sendMessage(heartbeatMsg);
        } catch (Exception e) {
            System.err.println("Failed to send heartbeat.");
            e.printStackTrace();
        }
    }
}
