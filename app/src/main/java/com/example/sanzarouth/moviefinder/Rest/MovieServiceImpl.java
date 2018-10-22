package com.example.sanzarouth.moviefinder.Rest;

import android.util.Log;

import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;

import java.util.ArrayList;
import java.util.Comparator;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class MovieServiceImpl implements MovieService {

    private MovieAPIInterface movieAPIInterface;

    public MovieServiceImpl(MovieAPIInterface movieAPIInterface) {
        this.movieAPIInterface = movieAPIInterface;
    }

    @Override
    public Observable<MovieList> getMovieList(String title, String plot, String key) {
        return movieAPIInterface.getMovies(title, plot, key).map(new Function<MovieList, MovieList>() {
            @Override
            public MovieList apply(MovieList movieList) throws Exception {
                Log.e("TEST", "" + movieList.toString());
                movieList.getMovieList().sort(new Comparator<SearchedMovie>() {
                    @Override
                    public int compare(SearchedMovie searchedMovie, SearchedMovie t1) {
                        Log.e("TEST", "MOVIE: " + searchedMovie);
                        return 0;
                    }
                });

                Log.e("TEST", "" + movieList.toString());
                return movieList;
            }
        });
    }

    @Override
    public Observable<Movie> getMovieDetail(String title, String plot, String key) {
        return movieAPIInterface.getMovie(title, plot, key);
    }
}
