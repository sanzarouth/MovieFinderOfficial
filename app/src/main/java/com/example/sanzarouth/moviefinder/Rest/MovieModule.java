package com.example.sanzarouth.moviefinder.Rest;

import android.app.Application;

import com.example.sanzarouth.moviefinder.ViewModel.CustomViewModelFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieModule {

    private Application application;
    private String urlPath;

    public MovieModule(Application application, String urlPath) {
        this.application = application;
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

    @Singleton
    @Provides
    public MovieAPIInterface provideMovieAPIInterface(Retrofit retrofit) {
        return retrofit.create(MovieAPIInterface.class);
    }

    @Singleton
    @Provides
    public MovieService provideMovieService(MovieAPIInterface movieAPIInterface) {
        return new MovieServiceImpl(movieAPIInterface);
    }

    @Singleton
    @Provides
    public CustomViewModelFactory provideCustomViewModelFactory(MovieService movieService) {
        return new CustomViewModelFactory(application, movieService);
    }


}
