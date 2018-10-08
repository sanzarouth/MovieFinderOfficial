package com.example.sanzarouth.moviefinder.Rest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.sanzarouth.moviefinder.Activities.MovieFinderActivity;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;

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


    public Call<MovieList> getMovies(String query) {
        return movieAPI.getMovies(query, "full", MovieFinderActivity.KEY);
    }

    public Call<Movie> getMovie(String query) {
        return movieAPI.getMovie(query, "full", MovieFinderActivity.KEY);
    }
}