package com.example.sanzarouth.moviefinder.Activities;

import android.os.Bundle;
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
import com.example.sanzarouth.moviefinder.Rest.GetMovieAPI;
import com.example.sanzarouth.moviefinder.Rest.RetrofitInstance;

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
    private ArrayList<SearchedMovie> searchedMovies;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        searchedMovies = new ArrayList<SearchedMovie>();
        lv.setLayoutManager(new LinearLayoutManager(this));

        String query = getIntent().getExtras().getString("query");

        GetMovieAPI service = RetrofitInstance.getRetrofitInstance().create(GetMovieAPI.class);
        Call<MovieList> call = service.getMovies(query, "full", MovieFinderActivity.KEY);

        adapter = new SearchMovieAdapter(SearchResultsActivity.this, searchedMovies);
        lv.setAdapter(adapter);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response.body().getMovieList() == null) {
                    noResult.setText("Sorry, no results! :(");
                    return;
                }
                noResult.setText("");
                searchedMovies.addAll(response.body().getMovieList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(SearchResultsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
