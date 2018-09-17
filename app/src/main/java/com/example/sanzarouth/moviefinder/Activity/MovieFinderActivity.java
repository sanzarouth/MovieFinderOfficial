package com.example.sanzarouth.moviefinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sanzarouth.moviefinder.R;

public class MovieFinderActivity extends AppCompatActivity implements View.OnClickListener {

    //TODO: Use Butterknife for view injection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_finder);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        LinearLayout titleSelect = (LinearLayout) findViewById(R.id.titleSelection);
        titleSelect.setOnClickListener(this);
        LinearLayout actorSelect = (LinearLayout) findViewById(R.id.actorSelection);
        actorSelect.setOnClickListener(this);
        LinearLayout genreSelect = (LinearLayout) findViewById(R.id.genreSelection);
        genreSelect.setOnClickListener(this);
        LinearLayout yearSelect = (LinearLayout) findViewById(R.id.yearSelection);
        yearSelect.setOnClickListener(this);
        LinearLayout directorSelect = (LinearLayout) findViewById(R.id.directorSelection);
        directorSelect.setOnClickListener(this);
        LinearLayout boxOfficeSelect = (LinearLayout) findViewById(R.id.boxOfficeSelection);
        boxOfficeSelect.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        ImageView selectedSearchImage = (ImageView) findViewById(R.id.selectedSearchImage);
        TextView selectedSearchText = (TextView) findViewById(R.id.selectedSearchText);

        Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);

        switch(view.getId()) {

            case R.id.titleSelection :
                searchIntent.putExtra("searchType", "Title");
                startActivity(searchIntent);
                break;
            case R.id.actorSelection :
                searchIntent.putExtra("searchType", "Actor");
                startActivity(searchIntent);
                break;
            case R.id.genreSelection :
                searchIntent.putExtra("searchType", "Genre");
                startActivity(searchIntent);
                break;
            case R.id.yearSelection :
                searchIntent.putExtra("searchType", "Year");
                startActivity(searchIntent);
                break;
            case R.id.directorSelection :
                searchIntent.putExtra("searchType", "Director");
                startActivity(searchIntent);
                break;
            case R.id.boxOfficeSelection :
                searchIntent.putExtra("searchType", "BoxOffice");
                startActivity(searchIntent);
                break;

        }
    }
}
