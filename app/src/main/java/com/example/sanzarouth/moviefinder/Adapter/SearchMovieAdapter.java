package com.example.sanzarouth.moviefinder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.MovieList;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.Rest.GetMovieDataService;
import com.example.sanzarouth.moviefinder.Rest.RetrofitInstance;
import com.example.sanzarouth.moviefinder.View.FullMovie;
import com.example.sanzarouth.moviefinder.View.SearchActivity;
import com.example.sanzarouth.moviefinder.View.SearchResults;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieAdapter extends BaseAdapter {

    LayoutInflater mInflator;
    ArrayList<SearchedMovie> movies;
    Context context;

    public SearchMovieAdapter(Context c, ArrayList<SearchedMovie> movies) {
        this.movies = movies;
        this.context = c;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return movies.get(i).getID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflator.inflate(R.layout.movie_detail, null);

        ImageView moviePoster = (ImageView) v.findViewById(R.id.imageView);
        final TextView movieTitle = (TextView) v.findViewById(R.id.movieTitle);
        TextView movieYear = (TextView) v.findViewById(R.id.movieYear);

        final SearchedMovie movie = movies.get(i);

        movieTitle.setText(movie.getMovieTitle());
        movieYear.setText(movie.getYear());
        if(movie.getPoster().equals("N/A")){
            moviePoster.setImageResource(R.drawable.logo);
        } else {
            Glide.with(v.getContext()).load(movie.getPoster()).into(moviePoster);
        }

        // Click on single movie for more info on that movie

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent fullMovieIntent = new Intent(context, FullMovie.class);
                fullMovieIntent.putExtra("movieTitle", movie.getMovieTitle());
                context.startActivity(fullMovieIntent);
            }
        });

        return v;
    }

    private Drawable loadImageFromWeb(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
