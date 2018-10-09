package com.example.sanzarouth.moviefinder.ViewModel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.sanzarouth.moviefinder.Rest.MovieAPIInterface;

public class CustomViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;
    private MovieAPIInterface retrofitNetworkInterface;


    public CustomViewModelFactory(Application application, MovieAPIInterface retrofitNetworkInterface) {
        this.application = application;
        this.retrofitNetworkInterface = retrofitNetworkInterface;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass == SearchResultsViewModel.class) {
            return (T) new SearchResultsViewModel(application, retrofitNetworkInterface);
        } else if (modelClass == DetailMovieViewModel.class) {
            return (T) new DetailMovieViewModel(application, retrofitNetworkInterface);
        }

        return (T) new SearchResultsViewModel(application, retrofitNetworkInterface);
    }
}