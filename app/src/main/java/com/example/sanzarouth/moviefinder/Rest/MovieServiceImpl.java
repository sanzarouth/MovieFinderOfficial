package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;

import io.reactivex.Observable;


public class MovieServiceImpl implements MovieService {

    private MovieAPIInterface movieAPIInterface;

    public MovieServiceImpl(MovieAPIInterface movieAPIInterface) {
        this.movieAPIInterface = movieAPIInterface;
    }

    @Override
    public Observable<MovieList> getMovieList(String title, String plot, String key) {
        return movieAPIInterface.getMovies(title, plot, key);
    }

    @Override
    public Observable<Movie> getMovieDetail(String title, String plot, String key) {
        return movieAPIInterface.getMovie(title, plot,key);
    }
}
