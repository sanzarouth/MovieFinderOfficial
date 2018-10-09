package com.example.sanzarouth.moviefinder;

import android.app.Application;

import com.example.sanzarouth.moviefinder.Rest.DaggerNetworkComponent;
import com.example.sanzarouth.moviefinder.Rest.NetworkComponent;
import com.example.sanzarouth.moviefinder.Rest.NetworksModule;

public class CustomApplication extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerNetworkComponent.builder()
                .networksModule(new NetworksModule("http://www.omdbapi.com/"))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
