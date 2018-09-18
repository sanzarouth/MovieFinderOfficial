package com.example.sanzarouth.moviefinder.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sanzarouth.moviefinder.Adapter.NewAdapter;
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

    private NewAdapter adapter;
    private RecyclerView lv;
    private ArrayList<SearchedMovie> searchedMovies;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        searchedMovies = new ArrayList<SearchedMovie>();

        GetMovieDataService service = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<MovieList> call = service.getMovies();

        lv = (RecyclerView) findViewById(R.id.moviesList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchResults.this);

        lv.setLayoutManager(layoutManager);

        adapter = new NewAdapter(searchedMovies);

        lv.setAdapter(adapter);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                searchedMovies.addAll(response.body().getEmployeeArrayList());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(SearchResults.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
