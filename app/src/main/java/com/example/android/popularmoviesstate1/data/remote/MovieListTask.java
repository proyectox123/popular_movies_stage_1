package com.example.android.popularmoviesstate1.data.remote;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.popularmoviesstate1.data.remote.models.Movie;
import com.example.android.popularmoviesstate1.data.remote.parser.MovieListJsonUtils;
import com.example.android.popularmoviesstate1.data.remote.requests.MoviePopularRequest;
import com.example.android.popularmoviesstate1.data.remote.requests.MovieTopRatedRequest;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

public class MovieListTask extends AsyncTask<String, Void, List<Movie>> {

    //region Constants

    public final static String MOVIE_POPULAR = "MOVIE_POPULAR";
    public final static String MOVIE_TOP_RATED = "MOVIE_TOP_RATED";

    //endregion

    //region Fields

    private WeakReference<Context> contextRef;

    private OnMovieListTaskListener onMovieListTaskListener;

    //endregion

    //region Constructors

    public MovieListTask(Context context, OnMovieListTaskListener onMovieListTaskListener){
        this.contextRef = new WeakReference<>(context);
        this.onMovieListTaskListener = onMovieListTaskListener;
    }

    //endregion

    //region Override Methods & Callbacks

    @Override
    protected List<Movie> doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }

        if(contextRef == null || contextRef.get() == null){
            return null;
        }

        switch (params[0]){
            case MOVIE_POPULAR:
                return getMoviePopularList();
            case MOVIE_TOP_RATED:
                return getMovieTopRatedList();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movieList) {
        super.onPostExecute(movieList);
        if(movieList != null && movieList.size() > 0){
            onMovieListTaskListener.updateMovieList(movieList);
        }else{
            onMovieListTaskListener.showErrorTask();
        }
    }

    //endregion

    //region Private Methods

    private List<Movie> getMoviePopularList(){
        MoviePopularRequest moviePopular = new MoviePopularRequest();
        URL movieListRequestUrl = moviePopular.buildUrl();
        try {
            String jsonMovieListResponse = moviePopular.getResponseFromHttpUrl(movieListRequestUrl);

            return MovieListJsonUtils.getMovieListFromJson(contextRef.get(), jsonMovieListResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Movie> getMovieTopRatedList(){
        MovieTopRatedRequest moviePopular = new MovieTopRatedRequest();
        URL movieListRequestUrl = moviePopular.buildUrl();
        try {
            String jsonMovieListResponse = moviePopular.getResponseFromHttpUrl(movieListRequestUrl);

            return MovieListJsonUtils.getMovieListFromJson(contextRef.get(), jsonMovieListResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //endregion

    //region Inner Classes & Interfaces

    public interface OnMovieListTaskListener {
        void updateMovieList(List<Movie> movieList);
        void showErrorTask();
    }

    //endregion
}
