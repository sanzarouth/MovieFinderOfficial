package com.example.sanzarouth.moviefinder;

import android.support.v7.app.AppCompatActivity;

import com.example.sanzarouth.moviefinder.Activities.MovieFinderActivity;
import com.example.sanzarouth.moviefinder.Activities.SearchResultsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworksModule.class})
public interface NetworkComponent {

    public void inject(SearchResultsActivity activity);

}
