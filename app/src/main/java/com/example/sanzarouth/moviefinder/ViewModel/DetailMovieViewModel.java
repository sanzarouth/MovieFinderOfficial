package com.example.sanzarouth.moviefinder.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.sanzarouth.moviefinder.Activities.MovieFinderActivity;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Rest.MovieService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailMovieViewModel extends AndroidViewModel {

    private MutableLiveData<Movie> movieLiveData;

    private MutableLiveData<Throwable> movieErrorLiveData;

    MovieService movieService;

    public DetailMovieViewModel(Application application, MovieService movieService) {
        super(application);
        movieLiveData = new MutableLiveData<>();
        movieErrorLiveData = new MutableLiveData<>();
        this.movieService = movieService;
    }

    public MutableLiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }

    public MutableLiveData<Throwable> getMovieErrorLiveData() {
        return movieErrorLiveData;
    }

    public void getMovieDetail(String query) {
        movieService.getMovieDetail(query, "full", MovieFinderActivity.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Movie movie) {
                        movieLiveData.setValue(movie);
                    }

                    @Override
                    public void onError(Throwable t) {
                        movieErrorLiveData.setValue(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
