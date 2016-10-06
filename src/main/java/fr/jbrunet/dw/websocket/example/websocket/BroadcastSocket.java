package fr.jbrunet.dw.websocket.example.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Julien BRUNET on 05/10/2016.
 */
@ServerEndpoint(value = "/broadcast")
public class BroadcastSocket {

    private static final Logger log = LoggerFactory.getLogger(BroadcastSocket.class);

    private static Set<Session> sessions = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        log.info("Socket Connected: {}", Integer.toHexString(session.hashCode()));
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        log.info("Socket Closed");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("Got text {} from {}", message, Integer.toHexString(session.hashCode()));
    }

    public static void broadcast(String msg) {
        sessions.forEach(session -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                log.error("Problem broadcasting message", e);
            }
        });
    }
}