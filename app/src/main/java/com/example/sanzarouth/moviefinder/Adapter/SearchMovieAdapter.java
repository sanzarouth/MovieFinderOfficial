package com.example.sanzarouth.moviefinder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.View.FullMovieActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.MovieItemViewHolder> {

    LayoutInflater inflater;
    ArrayList<SearchedMovie> movies;
    Context context;

    public SearchMovieAdapter(Context c, ArrayList<SearchedMovie> movies) {
        this.movies = movies;
        this.context = c;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.movie_detail, null);
        return new MovieItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        SearchedMovie movie = movies.get(position);
        holder.setData(movie);
    }

    @Override
    public long getItemId(int i) {
        return movies.get(i).getID();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
    

    public static class MovieItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView moviePoster;

        @BindView(R.id.movieTitle)
        TextView movieTitle;

        @BindView(R.id.movieYear)
        TextView movieYear;

        View view;

        Context context;

        public MovieItemViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);

        }

        public void setData(final SearchedMovie movie) {
            movieTitle.setText(movie.getMovieTitle());
            movieYear.setText(movie.getYear());

            if(movie.getPoster().equals("N/A")){
                moviePoster.setImageResource(R.drawable.logo);
            } else {
                Glide.with(view.getContext()).load(movie.getPoster()).into(moviePoster);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    Intent fullMovieIntent = new Intent(context, FullMovieActivity.class);
                    fullMovieIntent.putExtra("movieTitle", movie.getMovieTitle());
                    context.startActivity(fullMovieIntent);
                }
            });
        }


    }

}
