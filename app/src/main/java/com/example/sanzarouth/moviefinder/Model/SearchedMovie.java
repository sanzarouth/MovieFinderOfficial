package com.example.sanzarouth.moviefinder.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchedMovie {

    @SerializedName("Title")
    String movieTitle;

    @SerializedName("Year")
    String year;

    @SerializedName("Poster")
    String poster;

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

    public int getID() {
        return ID;
    }

    //Setters

    public void setPoster(String poster) {
        this.poster = poster;
    }

    //toString

    public String toString(){
        return movieTitle; // + "\n" + year + "\n" + director + "\n" + genre + "\n" + plot + "\n" + cast + "\n" + boxOffice + "\n" + awards + "\n" + poster;
    }


}
