package com.example.sanzarouth.moviefinder.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchedMovie {

    @SerializedName("Title")
    private String movieTitle;

    @SerializedName("Year")
    private String year;

    @SerializedName("Poster")
    private String poster;

    static int ID = 0;

    public SearchedMovie(String movieTitle){
        this.movieTitle = movieTitle;
        ID += 1;
    }

    //Getters

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getPoster() {
        return poster;
    }

    public String getYear() {
        return year;
    }

    public int getID() {
        return ID;
    }

    //toString

    public String toString(){
        return movieTitle;
    }


}
