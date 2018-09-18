package com.example.sanzarouth.moviefinder.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sanzarouth.moviefinder.Adapter.SearchMovieAdapter;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.Rest.GetMovieDataService;
import com.example.sanzarouth.moviefinder.Rest.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResults extends AppCompatActivity {

    private SearchMovieAdapter adapter;
    private ListView lv;
    private ArrayList<SearchedMovie> searchedMovies;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        searchedMovies = new ArrayList<SearchedMovie>();

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        String query = getIntent().getExtras().getString("query");

        System.out.println(query);

        GetMovieDataService service = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);

        Call<MovieList> call = service.getMovies(query, "full", MovieFinderActivity.KEY);

        System.out.println(call.request().url() + "    JDJADJS D AJIDJASIDJASD ");

        lv = (ListView) findViewById(R.id.moviesList);

        adapter = new SearchMovieAdapter(SearchResults.this, searchedMovies);

        lv.setAdapter(adapter);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                searchedMovies.addAll(response.body().getMovieList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(SearchResults.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
