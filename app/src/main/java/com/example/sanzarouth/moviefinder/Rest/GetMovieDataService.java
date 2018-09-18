package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetMovieDataService {

    @GET("?s=dex&plot=full&apikey=893bb7ef")
    Call<MovieList> getMovies();

    @GET("?t=/{title}&plot=full{key}")
    Call<Movie> getMovie(String title, String key);

}
