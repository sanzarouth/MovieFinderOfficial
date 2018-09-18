package com.example.sanzarouth.moviefinder.OldCode;

import android.os.StrictMode;

import com.example.sanzarouth.moviefinder.Model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OMDbAPICall {

    //TODO: create another class OMDbRetrofitAPICall and make the same network call using Retrofit

    protected static String URL = "http://www.omdbapi.com/";
    protected static String KEY = "&apikey=893bb7ef";


    public static ArrayList<Movie> getMovies(JSONArray response){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        if(response == null){
            return null;
        }
        try {
            for(int i = 0; i < response.length(); i++) {
                String movieTitle = response.getJSONObject(i).getString("Title");

                String movieInfo = sendGet("?t=", movieTitle);

                JSONObject obj = new JSONObject(movieInfo);

                String year = "N/A";
                String director = "N/A";
                String genre = "N/A";
                String plot = "N/A";
                String cast = "N/A";
                String boxOffice = "N/A";
                String awards = "N/A";
                String poster = "N/A";

                if(obj.has("Year")){
                    year = obj.getString("Year");
                }
                if(obj.has("Director")){
                    director = obj.getString("Director");
                }
                if(obj.has("Genre")) {
                    genre = obj.getString("Genre");
                }
                if(obj.has("Plot")) {
                    plot = obj.getString("Plot");
                }
                if(obj.has("Actors")) {
                    cast = obj.getString("Actors");
                }
                if(obj.has("BoxOffice")){
                    boxOffice = obj.getString("BoxOffice");
                }
                if(obj.has("Awards")) {
                    awards = obj.getString("Awards");
                }
                if(obj.has("Poster")) {
                    poster = obj.getString("Poster");
                }

                Movie movie = new Movie(movieTitle);
                movie.setYear(year);
                movie.setDirector(director);
                movie.setGenre(genre);
                movie.setPlot(plot);
                movie.setCast(cast);
                movie.setBoxOffice(boxOffice);
                movie.setAwards(awards);
                movie.setPoster(poster);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(movies);
        return movies;
    }

    public static JSONArray getObject(String response, String fieldName) {
        JSONArray arr = null;
        if(response.equals("{\"Response\":\"False\",\"Error\":\"Movie not found!\"}")){
            return null;
        }
        try {
            JSONObject object = new JSONObject(response);
            arr = object.getJSONArray(fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    public static String sendGet(String searchType, String field) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL link;
        String response = "";
        try {
            link = new URL(URL + searchType + field + "&plot=full" + KEY);
            System.out.println(link);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer res = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                res.append(inputLine);
            }
            in.close();
            response = res.toString();

        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }


}
