package com.example.sanzarouth.moviefinder.View;

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

import com.example.sanzarouth.moviefinder.OldCode.ResultsActivity;
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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        searchView = (SearchView) findViewById(R.id.searchView);
        spinner = (ProgressBar) findViewById(R.id.progressBar1);
        genreSpinner = (Spinner) findViewById(R.id.genreSpinner);
        spinnerHolder = (RelativeLayout) findViewById(R.id.spinnerHolder);

        if(getIntent().hasExtra("searchType")){
            String chosen = getIntent().getExtras().getString("searchType");

            ImageView selectedSearchImage = (ImageView) findViewById(R.id.selectedSearchImage);
            TextView selectedSearchText = (TextView) findViewById(R.id.selectedSearchText);

            genreSpinner.setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);
            spinnerHolder.setVisibility(View.GONE);

            if(chosen.equals("Title")) {
                selectedSearchImage.setImageResource(R.drawable.title_background);
                selectedSearchText.setText(R.string.title);
            }
        }

        Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);

                String query = searchView.getQuery().toString();

                Intent resultsIntent = new Intent(getApplicationContext(), SearchResults.class);

                resultsIntent.putExtra("query", query);

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