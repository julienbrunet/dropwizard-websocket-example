package fr.jbrunet.dw.websocket.example.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by Julien BRUNET on 05/10/2016.
 */
@Path("message")
public class MessageResource {

    @POST
    @Consumes("text/plain")
    public void sendMessage(String msg) throws Exception {
    }
}
