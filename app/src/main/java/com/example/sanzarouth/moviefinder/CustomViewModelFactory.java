package com.example.sanzarouth.moviefinder;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class CustomViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private String mParam;


    public CustomViewModelFactory(Application application) {
        mApplication = application;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new CustomViewModel(mApplication);
    }
}