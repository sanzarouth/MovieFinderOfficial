package com.example.sanzarouth.moviefinder;

import android.app.Application;

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
