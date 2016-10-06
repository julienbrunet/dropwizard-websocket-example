package fr.jbrunet.dw.websocket.example.websocket;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class BroadcastServlet extends WebSocketServlet {

   @Override
   public void configure(WebSocketServletFactory factory) {
      factory.register(BroadcastSocket.class);
   }
}
