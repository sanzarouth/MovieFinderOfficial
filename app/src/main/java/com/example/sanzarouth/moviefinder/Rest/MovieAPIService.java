package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPIService {

    @GET("?s=/{query}&plot=full{key}")
    Call<Movie> getAllMovies(String query, String key);

    @GET("?t=/{title}&plot=full{key}")
    Call<Movie> getMovie(String title, String key);

}
