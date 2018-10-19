package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;

import retrofit2.Call;

public interface MovieService {

    Call<MovieList> getMovieList(String title, String plot, String key);


    Call<Movie> getMovieDetail(String title, String plot, String key);

}
