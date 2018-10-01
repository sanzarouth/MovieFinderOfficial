package com.example.sanzarouth.moviefinder.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sanzarouth.moviefinder.Adapter.SearchMovieAdapter;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.ViewModel.SearchResultsViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity {

    @BindView(R.id.moviesList)
    RecyclerView rv;

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @BindView(R.id.noResult)
    TextView noResult;

    private SearchMovieAdapter adapter;
    private String query;
    private LinearLayoutManager layoutManager;
    ArrayList<SearchedMovie> searchedMovies = new ArrayList<SearchedMovie>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        query = getIntent().getExtras().getString("movieTitle");

        setupRecyclerView();

        final SearchResultsViewModel viewModel = ViewModelProviders.of(this).get(SearchResultsViewModel.class);

        observeViewModel(viewModel);

    }

    private void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        if (adapter == null) {
            adapter = new SearchMovieAdapter(this, searchedMovies);
            rv.setLayoutManager(layoutManager);
            rv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void observeViewModel(SearchResultsViewModel viewModel) {

        final Observer<MovieList> nameObserver = new Observer<MovieList>() {
            @Override
            public void onChanged(@Nullable final MovieList movie) {
                if (movie != null) {
                    List<SearchedMovie> movies = movie.getMovieList();
                    searchedMovies.addAll(movies);
                    adapter.notifyDataSetChanged();
                }
            }
        };

        viewModel.getMovieResponseObservable().observe(this, nameObserver);
    }

}

