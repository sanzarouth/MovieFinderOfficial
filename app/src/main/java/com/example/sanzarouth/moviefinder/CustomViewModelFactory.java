package com.example.sanzarouth.moviefinder;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.sanzarouth.moviefinder.ViewModel.SearchResultsViewModel;

public class CustomViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;
    private RetrofitNetworkInterface retrofitNetworkInterface;


    public CustomViewModelFactory(Application application, RetrofitNetworkInterface retrofitNetworkInterface) {
        this.application = application;
        this.retrofitNetworkInterface = retrofitNetworkInterface;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new SearchResultsViewModel(application, retrofitNetworkInterface);
    }
}