package com.example.sanzarouth.moviefinder;

import android.app.Application;

import com.example.sanzarouth.moviefinder.Rest.DaggerModuleAPIComponent;
import com.example.sanzarouth.moviefinder.Rest.ModuleAPIComponent;
import com.example.sanzarouth.moviefinder.Rest.MovieModule;

public class CustomApplication extends Application {

    private ModuleAPIComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerModuleAPIComponent.builder()
                .movieModule(new MovieModule(this, "http://www.omdbapi.com/"))
                .build();
    }

    public ModuleAPIComponent getNetworkComponent() {
        return networkComponent;
    }
}
