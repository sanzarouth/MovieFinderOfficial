package com.example.sanzarouth.moviefinder.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieViewModel extends AndroidViewModel {

    private MutableLiveData<Movie> movieLiveData;

    private MutableLiveData<Throwable> movieErrorLiveData;

    public DetailMovieViewModel(Application application) {
        super(application);
        movieLiveData = new MutableLiveData<>();
        movieErrorLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }

    public MutableLiveData<Throwable> getMovieErrorLiveData() {
        return movieErrorLiveData;
    }

    public void getMovies(String query) {
//        MovieService.getInstance().getMovie(query)
//                .enqueue(new Callback<Movie>() {
//                    @Override
//                    public void onResponse(Call<Movie> call, Response<Movie> response) {
//                        if (response.isSuccessful()) {
//                            movieLiveData.setValue(response.body());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Movie> call, Throwable t) {
//                        movieErrorLiveData.setValue(t);
//                    }
//                });

    }




}
