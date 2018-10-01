package com.example.sanzarouth.moviefinder.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.sanzarouth.moviefinder.Activities.MovieFinderActivity;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.Rest.MovieAPI;
import com.example.sanzarouth.moviefinder.Rest.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsViewModel extends ViewModel {

    private String query;
    private MutableLiveData<ArrayList<SearchedMovie>> mSearchedMovies;

    public MutableLiveData<ArrayList<SearchedMovie>> getCurrentMovies(String query) {
        this.query = query;
        if (mSearchedMovies == null) {
            getMovies();
        }
        return mSearchedMovies;
    }

    public void getMovies() {

        final ArrayList<SearchedMovie> searchedMovies = new ArrayList<SearchedMovie>();
        mSearchedMovies = new MutableLiveData<ArrayList<SearchedMovie>>();

        MovieAPI service = RetrofitInstance.getRetrofitInstance().create(MovieAPI.class);
        Call<MovieList> call = service.getMovies(query, "full", MovieFinderActivity.KEY);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.body().getMovieList() == null) {
                    return;
                }
                searchedMovies.addAll(response.body().getMovieList());
                mSearchedMovies.setValue(searchedMovies);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
//                Toast.makeText(SearchResultsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
