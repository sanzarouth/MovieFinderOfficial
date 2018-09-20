package com.example.sanzarouth.moviefinder.View;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.List;

import butterknife.BindFloat;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    public static final int RESULT_REQUEST = 1;

    @BindView(R.id.progressBar1)
    ProgressBar spinner;

    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @BindView(R.id.selectedSearchImage)
    ImageView selectedSearchImage;

    @BindView(R.id.selectedSearchText)
    TextView selectedSearchText;

    @BindView(R.id.searchButton)
    Button searchButton;

    @BindViews({R.id.spinnerHolder, R.id.genreSpinner})
    List<View> genreSpinner;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        if(getIntent().hasExtra("searchType")){
            String chosen = getIntent().getExtras().getString("searchType");

            if(chosen.equals("Genre")) {
                searchView.setVisibility(View.GONE);
                ButterKnife.apply(genreSpinner, VISIBILITY, View.VISIBLE);
            } else {
                searchView.setVisibility(View.VISIBLE);
                ButterKnife.apply(genreSpinner, VISIBILITY, View.GONE);
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


    }

    @OnClick(R.id.searchButton)
    public void onClick() {
        spinner.setVisibility(View.VISIBLE);

        String query = searchView.getQuery().toString();

        Intent resultsIntent = new Intent(getApplicationContext(), SearchResults.class);

        resultsIntent.putExtra("query", query);

        startActivityForResult(resultsIntent, RESULT_REQUEST);
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

    public static final ButterKnife.Setter<View, Integer> VISIBILITY = new ButterKnife.Setter<View, Integer>() {
        @Override
        public void set(@NonNull View view, Integer value, int index) {
            view.setVisibility(value);
        }
    };
}