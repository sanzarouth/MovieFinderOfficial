package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Activities.DetailMovieActivity;
import com.example.sanzarouth.moviefinder.Activities.SearchResultsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MovieModule.class})
public interface ModuleAPIComponent {

    public void inject(SearchResultsActivity activity);

    public void injectDetail(DetailMovieActivity activity);

}
