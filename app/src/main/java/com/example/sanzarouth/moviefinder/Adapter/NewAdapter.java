package com.example.sanzarouth.moviefinder.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.sanzarouth.moviefinder.Model.SearchedMovie;
import com.example.sanzarouth.moviefinder.R;
import com.example.sanzarouth.moviefinder.Model.Movie;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.EmployeeViewHolder> {

    private ArrayList<SearchedMovie> dataList;

    public NewAdapter(ArrayList<SearchedMovie> dataList) {
        this.dataList = dataList;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_detail, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        holder.movieTitle.setText(dataList.get(position).getMovieTitle());
        holder.imageView.setImageDrawable(loadImageFromWeb(dataList.get(position).getPoster()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        ImageView imageView;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public static Drawable loadImageFromWeb(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}
