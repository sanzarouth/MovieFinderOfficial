package com.example.sanzarouth.moviefinder.View;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.Rest.GetMovieDataService;
import com.example.sanzarouth.moviefinder.Rest.RetrofitInstance;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ImageView imageViewFull = (ImageView) findViewById(R.id.imageViewFull);
        final TextView movieTitleFull = (TextView) findViewById(R.id.movieTitleFull);
        final TextView movieGenreFull = (TextView) findViewById(R.id.movieGenreFull);
        final TextView movieYearFull = (TextView) findViewById(R.id.movieYearFull);
        final TextView directorFull = (TextView) findViewById(R.id.directorFull);
        final TextView fullCast = (TextView) findViewById(R.id.fullCast);
        final TextView fullBoxOffice = (TextView) findViewById(R.id.fullBoxOffice);
        final TextView fullAwards = (TextView) findViewById(R.id.fullAwards);
        final TextView fullSummary = (TextView) findViewById(R.id.fullSummary);

        GetMovieDataService service = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);

        Call<Movie> call = service.getMovie(getIntent().getStringExtra("movieTitle"), "full", MovieFinderActivity.KEY);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie toShow = response.body();
                if(toShow.getPoster().equals("N/A")){
                    imageViewFull.setImageResource(R.drawable.logo);
                } else {
                    Context c = getApplicationContext();
                    Glide.with(FullMovie.this).load(toShow.getPoster()).into(imageViewFull);
                }
                movieTitleFull.setText(toShow.getMovieTitle());
                movieGenreFull.setText(toShow.getGenre());
                movieYearFull.setText(toShow.getYear());
                directorFull.setText(toShow.getDirector());
                fullCast.setText(toShow.getCast());
                fullBoxOffice.setText(toShow.getBoxOffice());
                fullAwards.setText(toShow.getAwards());
                fullSummary.setText(toShow.getPlot());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(FullMovie.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

