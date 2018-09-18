package com.example.sanzarouth.moviefinder.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanzarouth.moviefinder.Adapter.NewAdapter;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.Rest.GetMovieDataService;
import com.example.sanzarouth.moviefinder.Rest.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                Intent searchIntent = new Intent(getApplicationContext(), SearchResults.class);
                startActivity(searchIntent);

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