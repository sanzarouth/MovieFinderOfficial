package com.example.sanzarouth.moviefinder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sanzarouth.moviefinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieFinderActivity extends AppCompatActivity {

    protected static String KEY = "893bb7ef";

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_finder);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);
    }


    @OnClick({R.id.titleSelection, R.id.actorSelection, R.id.genreSelection, R.id.yearSelection, R.id.directorSelection, R.id.boxOfficeSelection})
    public void onClick(View view) {

        Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);

        String selectionType = "";

        switch(view.getId()) {

            case R.id.titleSelection :
                selectionType = "Title";
                break;
            case R.id.actorSelection :
                selectionType = "Actor";
                break;
            case R.id.genreSelection :
                selectionType = "Genre";
                break;
            case R.id.yearSelection :
                selectionType = "Year";
                break;
            case R.id.directorSelection :
                selectionType = "Director";
                break;
            case R.id.boxOfficeSelection :
                selectionType = "BoxOffice";
                break;

        }
        searchIntent.putExtra("searchType", selectionType);
        startActivity(searchIntent);
    }

}
