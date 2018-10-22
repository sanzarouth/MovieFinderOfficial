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
                movieList.getMovieList().sort(new Comparator<SearchedMovie>() {
                    @Override
                    public int compare(SearchedMovie movie1, SearchedMovie movie2) {
                        String year1 = movie1.getYear();
                        String year2 = movie2.getYear();

                        if(movie1.getYear().contains("–")){
                            year1 = year1.substring(0, year1.length() - 1);
                        }

                        if(movie2.getYear().contains("–")){
                            year2 = year2.substring(0, year2.length() - 1);
                        }

                        int year1Int = Integer.parseInt(year1);
                        int year2Int = Integer.parseInt(year2);

                        if(year1Int < year2Int) {
                            return 1;
                        } else if (year1Int == year2Int) {
                            return 0;
                        } else {
                            return -1;
                        }

                    }
                });
                return movieList;
            }
        });
    }

    @Override
    public Observable<Movie> getMovieDetail(String title, String plot, String key) {
        return movieAPIInterface.getMovie(title, plot, key);
    }
}
