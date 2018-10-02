package com.example.sanzarouth.moviefinder.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.sanzarouth.moviefinder.Adapter.SearchMovieAdapter;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.ViewModel.SearchResultsViewModel;

import java.util.ArrayList;

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

        query = getIntent().getExtras().getString("query");

        setupRecyclerView();

        SearchResultsViewModel viewModel = ViewModelProviders.of(this).get(SearchResultsViewModel.class);

        viewModel.getMovieResponseObservable(query).observe(this, new Observer<MovieList>() {
            @Override
            public void onChanged(MovieList movie) {
                if (movie != null && movie.getMovieList() != null && !movie.getMovieList().isEmpty()) {
                    searchedMovies.clear();
                    searchedMovies.addAll(movie.getMovieList());
                    adapter.notifyDataSetChanged();
                }
            }
        });
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

}

