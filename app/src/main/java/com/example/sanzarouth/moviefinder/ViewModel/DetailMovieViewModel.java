package com.example.sanzarouth.moviefinder.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.sanzarouth.moviefinder.Activities.DetailMovieActivity;
import com.example.sanzarouth.moviefinder.Activities.MovieFinderActivity;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.Rest.MovieAPI;
import com.example.sanzarouth.moviefinder.Rest.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieViewModel extends ViewModel {

    private MutableLiveData<Movie> mMovie;

    String queryTitle;

    public MutableLiveData<Movie> getCurrentMovie(String queryTitle) {
        this.queryTitle = queryTitle;
        if (mMovie == null) {
            getMovie();
        }
        return mMovie;
    }

    public void getMovie() {

        MovieAPI service = RetrofitInstance.getRetrofitInstance().create(MovieAPI.class);

        Call<Movie> call = service.getMovie(queryTitle, "full", MovieFinderActivity.KEY);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie toShow = response.body();
                mMovie.setValue(toShow);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
//                Toast.makeText(DetailMovieActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }



}



