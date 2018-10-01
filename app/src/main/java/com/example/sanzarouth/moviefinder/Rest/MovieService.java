package com.example.sanzarouth.moviefinder.Rest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.sanzarouth.moviefinder.Activities.MovieFinderActivity;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieService {
    private MovieAPI movieAPI;

    private static class SingletonHelper {
        private static final MovieService INSTANCE = new MovieService();
    }

    public static MovieService getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public MovieService() {
        movieAPI = RetrofitInstance.getRetrofitInstance().create(MovieAPI.class);
    }

    public LiveData<MovieList> getMovies(String query) {
        final MutableLiveData<MovieList> data = new MutableLiveData<>();
        movieAPI.getMovies(query, "full", MovieFinderActivity.KEY)
                .enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}