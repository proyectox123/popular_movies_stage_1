package com.example.android.popularmoviesstate1.data.remote;

import android.net.Uri;
import android.util.Log;

import com.example.android.popularmoviesstate1.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private final static String TAG = NetworkUtils.class.getSimpleName();

    private final static String BASE_MOVIE_DB_URL = "https://api.themoviedb.org/3/";
    private final static String MOVIE_TOP_RATED_URL = BASE_MOVIE_DB_URL + "movie/top_rated";

    private final static String QUERY_PARAMENTER_API_KEY = "api_key";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(MOVIE_TOP_RATED_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMENTER_API_KEY, "")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}