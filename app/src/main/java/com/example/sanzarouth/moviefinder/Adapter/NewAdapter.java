package com.example.sanzarouth.moviefinder.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sanzarouth.moviefinder.Model.Movie;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class NewAdapter extends BaseAdapter {

    LayoutInflater mInflator;
    ArrayList<SearchedMovie> movies;

    public NewAdapter(Context c, ArrayList<SearchedMovie> movies) {
        this.movies = movies;
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
        TextView movieTitle = (TextView) v.findViewById(R.id.movieTitle);
        TextView movieYear = (TextView) v.findViewById(R.id.movieYear);

        SearchedMovie movie = movies.get(i);

        movieTitle.setText(movie.getMovieTitle());
        movieYear.setText(movie.getYear());
        if(movie.getPoster().equals("N/A")){
            moviePoster.setImageResource(R.drawable.logo);
        } else {
            Glide.with(v.getContext()).load(movie.getPoster()).into(moviePoster);
        }
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
