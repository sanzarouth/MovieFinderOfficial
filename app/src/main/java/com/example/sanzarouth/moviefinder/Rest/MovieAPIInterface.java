package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPIInterface {

    @GET("/")
    Observable<MovieList> getMovies(@Query("s") String title, @Query("plot") String plot, @Query("apikey") String key);

    @GET("/")
    Observable<Movie> getMovie(@Query("t") String title, @Query("plot") String plot, @Query("apikey") String key);


}
