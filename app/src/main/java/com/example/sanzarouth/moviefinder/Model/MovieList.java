package com.example.sanzarouth.moviefinder.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieList {

    @SerializedName("Search")
    private ArrayList<SearchedMovie> movieList;

    public ArrayList<SearchedMovie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<SearchedMovie> movieList) {
        this.movieList = movieList;
    }

}
