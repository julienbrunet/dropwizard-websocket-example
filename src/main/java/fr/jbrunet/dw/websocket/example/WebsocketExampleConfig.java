package fr.jbrunet.dw.websocket.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Julien BRUNET on 06/10/2016.
 */
public class WebsocketExampleConfig extends Configuration implements AssetsBundleConfiguration {

    @Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets = AssetsConfiguration.builder().build();


    @Override
    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }
}
