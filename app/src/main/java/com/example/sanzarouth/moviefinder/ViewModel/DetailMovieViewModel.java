package com.example.sanzarouth.moviefinder.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Rest.MovieService;

public class DetailMovieViewModel extends AndroidViewModel {

    private LiveData<Movie> movieResponseObservable;

    public DetailMovieViewModel(Application application) {
        super(application);
    }

    public LiveData<Movie> getMovieResponseObservable(String query) {
        movieResponseObservable = MovieService.getInstance().getMovie(query);
        return movieResponseObservable;
    }
}
