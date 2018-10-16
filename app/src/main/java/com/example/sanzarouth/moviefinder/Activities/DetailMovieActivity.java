package com.example.sanzarouth.moviefinder.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sanzarouth.moviefinder.CustomApplication;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.Rest.MovieAPIInterface;
import com.example.sanzarouth.moviefinder.ViewModel.CustomViewModelFactory;
import com.example.sanzarouth.moviefinder.ViewModel.DetailMovieViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {

    private static final String TAG = SearchResultsActivity.class.getSimpleName();

    @Inject
    CustomViewModelFactory customViewModelFactory;

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

    MovieAPIInterface mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_profile);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        String query = getIntent().getExtras().getString("movieTitle");

        ((CustomApplication)getApplication()).getNetworkComponent().injectDetail(DetailMovieActivity.this);

        DetailMovieViewModel detailMovieViewModel = ViewModelProviders
                .of(this, customViewModelFactory).get(DetailMovieViewModel.class);

        detailMovieViewModel.getMovieLiveData().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie != null) {
                    if (movie.getPoster().equals("N/A")) {
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
            }
        });

        detailMovieViewModel.getMovieErrorLiveData().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {
                Log.e(TAG, "Failed to get movies " + throwable.getMessage());
            }
        });
        detailMovieViewModel.getMovieDetail(query);


    }

}

