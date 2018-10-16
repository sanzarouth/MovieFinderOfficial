package com.example.sanzarouth.moviefinder.ViewModel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.sanzarouth.moviefinder.Rest.MovieService;

public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private MovieService movieService;


    public CustomViewModelFactory(Application application, MovieService movieService) {
        this.application = application;
        this.movieService = movieService;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass == SearchResultsViewModel.class) {
            return (T) new SearchResultsViewModel(application, movieService);
        } else if (modelClass == DetailMovieViewModel.class) {
            return (T) new DetailMovieViewModel(application, movieService);
        }

        return (T) new SearchResultsViewModel(application, movieService);
    }
}