package com.example.sanzarouth.moviefinder.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.sanzarouth.moviefinder.Activities.MovieFinderActivity;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Rest.MovieService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SearchResultsViewModel extends AndroidViewModel {

    private MutableLiveData<MovieList> movieListLiveData;

    private MutableLiveData<Throwable> movieListErrorLiveData;

    MovieService movieService;

    public SearchResultsViewModel(Application application, MovieService movieService) {
        super(application);
        this.movieService = movieService;
        movieListLiveData = new MutableLiveData<>();
        movieListErrorLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<MovieList> getMovieListLiveData() {
        return movieListLiveData;
    }

    public MutableLiveData<Throwable> getMovieListErrorLiveData() {
        return movieListErrorLiveData;
    }

    public void getMovies(String query) {
        movieService.getMovieList(query, "full", MovieFinderActivity.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieList movieList) {
                        movieListLiveData.setValue(movieList);
                    }

                    @Override
                    public void onError(Throwable t) {
                        movieListErrorLiveData.setValue(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
