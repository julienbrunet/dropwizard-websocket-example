package fr.jbrunet.dw.websocket.example;

import be.tomcools.dropwizard.websocket.WebsocketBundle;
import fr.jbrunet.dw.websocket.example.api.BroadcasterResource;
import fr.jbrunet.dw.websocket.example.websocket.BroadcastSocket;
import io.dropwizard.Application;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by Julien BRUNET on 05/10/2016.
 */
public class WebsocketExampleApp extends Application<WebsocketExampleConfig> {
    private WebsocketBundle websocket = new WebsocketBundle();

    public static void main(String[] args) throws Exception {
        new WebsocketExampleApp().run("server", "src/main/resources/config.yml");
    }

    @Override
    public void initialize(Bootstrap<WebsocketExampleConfig> bootstrap) {
        bootstrap.addBundle(new ConfiguredAssetsBundle("/web-client/", "/"));
        bootstrap.addBundle(websocket);
    }

    @Override
    public void run(WebsocketExampleConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(new BroadcasterResource());

        //Annotated endpoint
        websocket.addEndpoint(BroadcastSocket.class);

    }
}
