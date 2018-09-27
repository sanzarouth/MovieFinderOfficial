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
//        final MutableLiveData<MovieList> data = new MutableLiveData<>();
//        movieAPI.getMovies(query, "full", MovieFinderActivity.KEY)
//                .enqueue(new Callback<MovieList>() {
//                    @Override
//                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
//                        if (response.isSuccessful()) {
//                            data.setValue(response.body());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<MovieList> call, Throwable t) {
//                        data.setValue(null);
//                    }
//                });
//        return data;
    }

    public LiveData<Movie> getMovie(String query) {
        final MutableLiveData<Movie> data = new MutableLiveData<>();
        movieAPI.getMovie(query, "full", MovieFinderActivity.KEY)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}