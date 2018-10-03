package com.example.sanzarouth.moviefinder.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Rest.MovieService;

public class SearchResultsViewModel extends AndroidViewModel {

    private LiveData<MovieList> movieResponseObservable;

    public SearchResultsViewModel(Application application) {
        super(application);
    }

    public LiveData<MovieList> getMovieResponseObservable(String query) {
        movieResponseObservable = MovieService.getInstance().getMovies(query);
        return movieResponseObservable;
    }
}
