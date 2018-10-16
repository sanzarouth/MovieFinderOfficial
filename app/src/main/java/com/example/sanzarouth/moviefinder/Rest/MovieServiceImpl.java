package com.example.sanzarouth.moviefinder.Rest;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;

import retrofit2.Call;

public class MovieServiceImpl implements MovieService {

    private MovieAPIInterface movieAPIInterface;

    public MovieServiceImpl(MovieAPIInterface movieAPIInterface) {
        this.movieAPIInterface = movieAPIInterface;
    }

    @Override
    public Call<MovieList> getMovieList(String title, String plot, String key) {
        return movieAPIInterface.getMovies(title, plot, key);
    }

    @Override
    public Call<Movie> getMovieDetail(String title, String plot, String key) {
        return movieAPIInterface.getMovie(title, plot,key);
    }
}
