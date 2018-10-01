package com.example.sanzarouth.moviefinder.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sanzarouth.moviefinder.Adapter.SearchMovieAdapter;
import com.example.sanzarouth.moviefinder.Model.Movie;
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

//    private DetailMovieViewModel detailMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_profile);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        String query = getIntent().getExtras().getString("query");

//        detailMovieViewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);

        final Observer<Movie> nameObserver = new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable final Movie movie) {
                // do some UI changes
                if(movie.getPoster().equals("N/A")){
                    imageViewFull.setImageResource(R.drawable.logo);
                } else {
                    Glide.with(DetailMovieActivity.this).load(movie.getPoster()).into(imageViewFull);
                }
                movieTitleFull.setText(movie.getMovieTitle());
                movieGenreFull.setText(movie.getGenre());
                movieYearFull.setText(movie.getYear());
                directorFull.setText(movie.getDirector());
                fullCast.setText(movie.getCast());
                fullBoxOffice.setText(movie.getBoxOffice());
                fullAwards.setText(movie.getAwards());
                fullSummary.setText(movie.getPlot());
            }
        };

//        detailMovieViewModel.getCurrentMovie(query).observe(this, nameObserver);

    }

}

