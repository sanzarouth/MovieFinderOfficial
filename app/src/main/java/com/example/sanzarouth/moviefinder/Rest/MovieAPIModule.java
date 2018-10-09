package com.example.sanzarouth.moviefinder.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieAPIModule {

    private String urlPath;

    public MovieAPIModule(String urlPath) {
        this.urlPath = urlPath;
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(urlPath)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
