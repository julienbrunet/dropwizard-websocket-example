package fr.jbrunet.dw.websocket.example.api;

import fr.jbrunet.dw.websocket.example.websocket.BroadcastSocket;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by Julien BRUNET on 05/10/2016.
 */
@Path("broadcast")
public class BroadcasterResource {
    @POST
    @Consumes("application/json")
    public void broadcast(Object data) throws Exception {
        BroadcastSocket.broadcast((String) data);
    }

    @POST
    @Consumes("text/plain")
    public void broadcastString(String data) throws Exception {
        BroadcastSocket.broadcast("Client message: " + data);
    }
}
