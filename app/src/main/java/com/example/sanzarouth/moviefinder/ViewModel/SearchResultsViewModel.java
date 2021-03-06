package com.example.sanzarouth.moviefinder.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.sanzarouth.moviefinder.Activities.MovieFinderActivity;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Rest.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsViewModel extends AndroidViewModel {

    private MutableLiveData<MovieList> movieListLiveData;

    private MutableLiveData<Throwable> movieListErrorLiveData;

    MovieService movieService;

    public SearchResultsViewModel(Application application, MovieService movieService) {
        super(application);
        this.movieService = movieService;
        movieListLiveData = new MutableLiveData<>();
        movieListErrorLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<MovieList> getMovieListLiveData() {
        return movieListLiveData;
    }

    public MutableLiveData<Throwable> getMovieListErrorLiveData() {
        return movieListErrorLiveData;
    }

    public void getMovies(String query) {
        movieService.getMovieList(query, "full", MovieFinderActivity.KEY)
                .enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        if (response.isSuccessful()) {
                            movieListLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {
                        movieListErrorLiveData.setValue(t);
                    }
                });

    }
}
