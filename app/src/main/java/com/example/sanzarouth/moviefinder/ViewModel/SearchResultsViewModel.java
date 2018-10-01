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

    private String query;
    private LiveData<MovieList> movieResponseObservable;

    private static final MutableLiveData MUTABLE_LIVE_DATA = new MutableLiveData();{
        MUTABLE_LIVE_DATA.setValue(null);
    }

    public final ObservableField<MovieList> project = new ObservableField<>();

    public SearchResultsViewModel(Application application) {
        super(application);
        movieResponseObservable = MovieService.getInstance().getMovies(query);
    }

    public LiveData<MovieList> getMovieResponseObservable() {
        return movieResponseObservable;
    }
}
