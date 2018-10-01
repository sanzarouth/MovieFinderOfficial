package com.example.sanzarouth.moviefinder.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.Rest.MovieAPI;
import com.example.sanzarouth.moviefinder.Rest.RetrofitInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity {

    @BindView(R.id.imageViewFull)
    ImageView imageViewFull;

    @BindView(R.id.movieTitleFull)
    TextView movieTitleFull;

    @BindView(R.id.movieGenreFull)
    TextView movieGenreFull;

    @BindView(R.id.movieYearFull)
    TextView movieYearFull;

    @BindView(R.id.directorFull)
    TextView directorFull;

    @BindView(R.id.fullCast)
    TextView fullCast;

    @BindView(R.id.fullBoxOffice)
    TextView fullBoxOffice;

    @BindView(R.id.fullAwards)
    TextView fullAwards;

    @BindView(R.id.fullSummary)
    TextView fullSummary;

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_profile);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        MovieAPI service = RetrofitInstance.getRetrofitInstance().create(MovieAPI.class);

        Call<Movie> call = service.getMovie(getIntent().getStringExtra("movieTitle"), "full", MovieFinderActivity.KEY);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie toShow = response.body();

                if(toShow.getPoster().equals("N/A")){
                    imageViewFull.setImageResource(R.drawable.logo);
                } else {
                    Glide.with(DetailMovieActivity.this).load(toShow.getPoster()).into(imageViewFull);
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
                Toast.makeText(DetailMovieActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

