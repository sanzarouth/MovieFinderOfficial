package com.example.sanzarouth.moviefinder.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanzarouth.moviefinder.Adapter.SearchMovieAdapter;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.Rest.MovieAPI;
import com.example.sanzarouth.moviefinder.Rest.RetrofitInstance;
import com.example.sanzarouth.moviefinder.ViewModel.SearchResultsViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsActivity extends AppCompatActivity {

    @BindView(R.id.moviesList)
    RecyclerView lv;

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @BindView(R.id.noResult)
    TextView noResult;

    private SearchMovieAdapter adapter;
    private SearchResultsViewModel searchResultsViewModel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        lv.setLayoutManager(new LinearLayoutManager(this));

        String query = getIntent().getExtras().getString("query");

        searchResultsViewModel = ViewModelProviders.of(this).get(SearchResultsViewModel.class);
        final Observer<ArrayList<SearchedMovie>> nameObserver = new Observer<ArrayList<SearchedMovie>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<SearchedMovie> searchedMovies) {
                // do some UI changes
                adapter = new SearchMovieAdapter(SearchResultsActivity.this, searchedMovies);
                lv.setAdapter(adapter);
            }
        };
        searchResultsViewModel.getCurrentMovies(query).observe(this, nameObserver);

    }

}
