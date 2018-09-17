package com.example.sanzarouth.moviefinder;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie {

    String movieTitle;
    String year;
    String director;
    String genre;
    String plot;
    String cast;
    String boxOffice;
    String awards;
    String poster;
    static int ID = 0;

    public Movie(String movieTitle){
        this.movieTitle = movieTitle;
        ID += 1;
    }

    //Getters

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlot() {
        return plot;
    }

    public String getCast() {
        return cast;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public String getPoster() {
        return poster;
    }

    public String getAwards() {
        return awards;
    }

    public int getID() {
        return ID;
    }

    //Setters

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    //toString

    public String toString(){
        return movieTitle; // + "\n" + year + "\n" + director + "\n" + genre + "\n" + plot + "\n" + cast + "\n" + boxOffice + "\n" + awards + "\n" + poster;
    }


}
