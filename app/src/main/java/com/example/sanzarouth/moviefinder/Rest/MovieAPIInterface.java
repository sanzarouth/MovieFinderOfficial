package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPIInterface {

    @GET("/")
    Call<MovieList> getMovies(@Query("s") String title, @Query("plot") String plot, @Query("apikey") String key);

    @GET("/")
    Call<Movie> getMovie(@Query("t") String title, @Query("plot") String plot, @Query("apikey") String key);


}
