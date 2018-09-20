package com.example.sanzarouth.moviefinder.View;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

            switch (chosen) {
                case "Title":
                    setImageAndText(R.drawable.title_background, R.string.title);
                    break;
                case "Actor":
                    setImageAndText(R.drawable.actor_background, R.string.actor);
                    break;
                case "Genre":
                    setImageAndText(R.drawable.genre_background, R.string.genre);
                    break;
                case "Year":
                    setImageAndText(R.drawable.year_background, R.string.year);
                    break;
                case "Director":
                    setImageAndText(R.drawable.director_background, R.string.director);
                    break;
                default:
                    setImageAndText(R.drawable.box_office_background, R.string.box_office);
                    break;

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

    private static final ButterKnife.Setter<View, Integer> VISIBILITY = new ButterKnife.Setter<View, Integer>() {
        @Override
        public void set(@NonNull View view, Integer value, int index) {
            view.setVisibility(value);
        }
    };

    private void setImageAndText(int drawableId, int title) {
        selectedSearchImage.setImageResource(drawableId);
        selectedSearchText.setText(title);
    }
}