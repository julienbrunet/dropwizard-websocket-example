package fr.jbrunet.dw.websocket.example.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
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
        log.info("Socket opened, session id {}", Integer.toHexString(session.hashCode()));
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        sessions.remove(session);
        log.info("Socket closed for session id {}, reason : {}", Integer.toHexString(session.hashCode()), reason.getReasonPhrase());
    }

    @OnMessage
    public void onMessage(String message, Session senderSession) {
        log.info("Message received from session {} : {}", Integer.toHexString(senderSession.hashCode()), message);
        sessions.forEach(s -> {
            try {
                s.getBasicRemote().sendText("[" + Integer.toHexString(senderSession.hashCode()) + "] " + message);
            } catch (IOException e) {
                log.error("Problem broadcasting message", e);
            }
        });
    }

}