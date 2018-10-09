package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Activities.SearchResultsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworksModule.class})
public interface NetworkComponent {

    public void inject(SearchResultsActivity activity);

}
