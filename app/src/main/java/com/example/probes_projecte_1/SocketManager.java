package com.example.probes_projecte_1;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketManager {
    private static final String SERVER_URL = "http://192.168.205.249:3001";
    private Socket socket;

    public SocketManager() {
        try {
            socket = IO.socket(SERVER_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void connectSocket() {
        if (socket != null) {
            socket.connect();
        }
    }

    public void disconnectSocket() {
        if (socket != null) {
            socket.disconnect();
        }
    }

    public void sendMessage(String message) {
        if (socket != null) {
            socket.emit("chat message", message);
        }
    }

    public void setOnMessageReceivedListener(final OnMessageReceivedListener listener) {
        if (socket != null) {
            socket.on("chat message", args -> {
                if (args.length > 0) {
                    String message = (String) args[0];
                    listener.onMessageReceived(message);
                }
            });
        }
    }

    public interface OnMessageReceivedListener {
        void onMessageReceived(String message);
    }
}

