package com.example.sanzarouth.moviefinder;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class FullMovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

            ImageView imageViewFull = (ImageView) findViewById(R.id.imageViewFull);
            TextView movieTitleFull = (TextView) findViewById(R.id.movieTitleFull);
            TextView movieGenreFull = (TextView) findViewById(R.id.movieGenreFull);
            TextView movieYearFull = (TextView) findViewById(R.id.movieYearFull);
            TextView directorFull = (TextView) findViewById(R.id.directorFull);
            TextView fullCast = (TextView) findViewById(R.id.fullCast);
            TextView fullBoxOffice = (TextView) findViewById(R.id.fullBoxOffice);
            TextView fullAwards = (TextView) findViewById(R.id.fullAwards);
            TextView fullSummary = (TextView) findViewById(R.id.fullSummary);

            String poster = getIntent().getExtras().getString("poster");
            String title = getIntent().getExtras().getString("title");
            String genre = getIntent().getExtras().getString("genre");
            String year = getIntent().getExtras().getString("year");
            String director = getIntent().getExtras().getString("director");
            String cast = getIntent().getExtras().getString("cast");
            String boxOffice = getIntent().getExtras().getString("boxOffice");
            String awards = getIntent().getExtras().getString("awards");
            String summary = getIntent().getExtras().getString("summary");

            if(poster.equals("N/A")){
                imageViewFull.setImageResource(R.drawable.logo);
            } else {
                imageViewFull.setImageDrawable(loadImageFromWeb(poster));
            }
            movieTitleFull.setText(title);
            movieGenreFull.setText(genre);
            movieYearFull.setText(year);
            directorFull.setText(director);
            fullCast.setText(cast);
            fullBoxOffice.setText(boxOffice);
            fullAwards.setText(awards);
            fullSummary.setText(summary);

    }

    public static Drawable loadImageFromWeb(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
