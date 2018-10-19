package com.example.sanzarouth.moviefinder.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.sanzarouth.moviefinder.Activities.MovieFinderActivity;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Rest.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            movieLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        movieErrorLiveData.setValue(t);
                    }
                });

    }
}
