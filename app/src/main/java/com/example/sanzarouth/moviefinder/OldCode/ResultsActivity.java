package com.example.sanzarouth.moviefinder.OldCode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.sanzarouth.moviefinder.R;

public class ResultsActivity extends AppCompatActivity {

    private ListView moviesList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        //TODO: Use Butterknife for view injection

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        if(getIntent().hasExtra("queryResponse")) {
//            String response = getIntent().getExtras().getString("queryResponse");
//            System.out.println(response);
//            ArrayList<Movie> result = OMDbAPICall.getMovies(OMDbAPICall.getObject(response, "Search"));
//
//            if(result == null) {
//                TextView noResult = (TextView) findViewById(R.id.noResult);
//                noResult.setText("SORRY, NO RESULTS :(");
//                return;
//            } else {
//                TextView noResult = (TextView) findViewById(R.id.noResult);
//                noResult.setText("");
//            }
//
//            System.out.println(result);
//
//            MoviesAdapter movieAdapter = new MoviesAdapter(this, result);
//
//            moviesList = (ListView) findViewById(R.id.moviesList);
//
//            moviesList.setAdapter(movieAdapter);
//
//            moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Intent showFullMovie = new Intent(getApplicationContext(), FullMovieInfoActivity.class);
//
//                    Movie movie = (Movie) moviesList.getItemAtPosition(i);
//
//                    Bundle extras = new Bundle();
//                    extras.putString("title", movie.getMovieTitle());
//                    extras.putString("genre", movie.getGenre());
//                    extras.putString("year", movie.getYear());
//                    extras.putString("director", movie.getDirector());
//                    extras.putString("cast", movie.getCast());
//                    extras.putString("boxOffice", movie.getBoxOffice());
//                    extras.putString("awards", movie.getAwards());
//                    extras.putString("summary", movie.getPlot());
//                    extras.putString("poster", movie.getPoster());
//
//                    showFullMovie.putExtras(extras);
//
//                    startActivity(showFullMovie);
//                }
//            });
//        }
    }

}
