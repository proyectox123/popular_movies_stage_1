package com.example.android.popularmoviesstate1.data.remote.parser;

import com.example.android.popularmoviesstate1.data.remote.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class MovieListJsonUtils {

    private final static String URL_IMAGE_TBMD = "http://image.tmdb.org/t/p/w185/";

    public static List<Movie> getMovieListFromJson(String movieListJsonStr)
            throws JSONException {

        JSONObject movieListJson = new JSONObject(movieListJsonStr);
        JSONArray resultJsonArray = movieListJson.getJSONArray("results");

        List<Movie> movieList = new ArrayList<>();

        for(int i=0; i<resultJsonArray.length(); i++){
            JSONObject movieFromJsonObject = resultJsonArray.getJSONObject(i);
            movieList.add(getMovieFromJsonObject(movieFromJsonObject));
        }

        return movieList;
    }

    private static Movie getMovieFromJsonObject(JSONObject movieJsonObject) throws JSONException{
        int id = movieJsonObject.getInt("id");
        String title = movieJsonObject.getString("title");
        String releaseDate = movieJsonObject.getString("release_date");
        String posterPath = URL_IMAGE_TBMD + movieJsonObject.getString("poster_path");
        double voteAverage = movieJsonObject.getDouble("vote_average");
        String plotSynopsis = movieJsonObject.getString("overview");

        return new Movie(id, title, releaseDate, posterPath, voteAverage, plotSynopsis);
    }
}