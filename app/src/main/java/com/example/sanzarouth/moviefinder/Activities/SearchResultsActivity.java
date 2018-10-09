package com.example.sanzarouth.moviefinder.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.sanzarouth.moviefinder.Adapter.SearchMovieAdapter;
import com.example.sanzarouth.moviefinder.CustomApplication;
import com.example.sanzarouth.moviefinder.ViewModel.CustomViewModelFactory;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.Rest.MovieAPIInterface;
import com.example.sanzarouth.moviefinder.ViewModel.SearchResultsViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class SearchResultsActivity extends AppCompatActivity {

    private static final String TAG = SearchResultsActivity.class.getSimpleName();

    @Inject
    Retrofit retrofit;

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
    MovieAPIInterface mService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        ((CustomApplication)getApplication()).getNetworkComponent().inject(SearchResultsActivity.this);
        mService = retrofit.create(MovieAPIInterface.class);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        query = getIntent().getExtras().getString("query");

        setupRecyclerView();

        SearchResultsViewModel viewModel = ViewModelProviders
                .of(this, new CustomViewModelFactory(this.getApplication(), mService)).get(SearchResultsViewModel.class);

        viewModel.getMovieListLiveData().observe(this, new Observer<MovieList>() {
            @Override
            public void onChanged(@Nullable MovieList movie) {
                if (movie != null && movie.getMovieList() != null && !movie.getMovieList().isEmpty()) {
                    searchedMovies.clear();
                    searchedMovies.addAll(movie.getMovieList());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        viewModel.getMovieListErrorLiveData().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {
                Log.e(TAG, "Failed to get movies " + throwable.getMessage());
            }
        });
        viewModel.getMovies(query);
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

