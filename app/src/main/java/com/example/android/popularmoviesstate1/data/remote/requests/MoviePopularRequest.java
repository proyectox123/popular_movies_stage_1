package com.example.android.popularmoviesstate1.data.remote.requests;

import android.net.Uri;

import com.example.android.popularmoviesstate1.BuildConfig;

public class MoviePopularRequest extends BaseMovieRequest {

    //region Constants

    private final static String BASE_MOVIE_DB_URL = "https://api.themoviedb.org/3/";
    private final static String MOVIE_POPULAR_URL = BASE_MOVIE_DB_URL + "movie/popular";

    private final static String QUERY_PARAMETER_API_KEY = "api_key";

    //endregion

    //region Override Methods & Callbacks

    @Override
    public Uri createBuiltUri() {
        return Uri.parse(MOVIE_POPULAR_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_API_KEY, BuildConfig.MOVIE_DB_API_KEY)
                .build();
    }

    //endregion
}