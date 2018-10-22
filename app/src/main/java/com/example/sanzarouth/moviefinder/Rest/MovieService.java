package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;

import io.reactivex.Observable;

public interface MovieService {

    Observable<MovieList> getMovieList(String title, String plot, String key);


    Observable<Movie> getMovieDetail(String title, String plot, String key);

}
