package com.example.sanzarouth.moviefinder.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sanzarouth.moviefinder.OMDbAPICall;
import com.example.sanzarouth.moviefinder.R;

public class SearchActivity extends AppCompatActivity {

    public static final int RESULT_REQUEST = 1;
    ProgressBar spinner;
    SearchView searchView;
    Spinner genreSpinner;
    RelativeLayout spinnerHolder;

    //TODO: Use Butterknife for view injection

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        searchView = (SearchView) findViewById(R.id.searchView);
        spinner = (ProgressBar) findViewById(R.id.progressBar1);
        genreSpinner = (Spinner) findViewById(R.id.genreSpinner);
        spinnerHolder = (RelativeLayout) findViewById(R.id.spinnerHolder);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if(getIntent().hasExtra("searchType")){
            String chosen = getIntent().getExtras().getString("searchType");

            ImageView selectedSearchImage = (ImageView) findViewById(R.id.selectedSearchImage);
            TextView selectedSearchText = (TextView) findViewById(R.id.selectedSearchText);

            if(chosen.equals("Genre")) {
                genreSpinner.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.GONE);
                spinnerHolder.setVisibility(View.VISIBLE);
            } else {
                genreSpinner.setVisibility(View.GONE);
                searchView.setVisibility(View.VISIBLE);
                spinnerHolder.setVisibility(View.GONE);
            }

            if(chosen.equals("Title")) {
                selectedSearchImage.setImageResource(R.drawable.title_background);
                selectedSearchText.setText(R.string.title);
            } else if (chosen.equals("Actor")) {
                selectedSearchImage.setImageResource(R.drawable.actor_background);
                selectedSearchText.setText(R.string.actor);
            } else if (chosen.equals("Genre")) {
                selectedSearchImage.setImageResource(R.drawable.genre_background);
                selectedSearchText.setText(R.string.genre);
            } else if (chosen.equals("Year")) {
                selectedSearchImage.setImageResource(R.drawable.year_background);
                selectedSearchText.setText(R.string.year);
            } else if (chosen.equals("Director")) {
                selectedSearchImage.setImageResource(R.drawable.director_background);
                selectedSearchText.setText(R.string.director);
            } else {
                selectedSearchImage.setImageResource(R.drawable.box_office_background);
                selectedSearchText.setText(R.string.box_office);
            }
        }

        Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);

                String query = searchView.getQuery().toString();

                String response = OMDbAPICall.sendGet("?s=", query);

                Intent resultsIntent = new Intent(getApplicationContext(), ResultsActivity.class);

                resultsIntent.putExtra("queryResponse", response);

                startActivityForResult(resultsIntent, RESULT_REQUEST);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == RESULT_REQUEST) {
            spinner.setVisibility(View.GONE);
            searchView.setQuery("", false);
            searchView.clearFocus();
        }
    }
}