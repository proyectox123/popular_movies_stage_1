package com.example.android.popularmoviesstate1.data.remote;

import android.os.AsyncTask;

import com.example.android.popularmoviesstate1.data.remote.models.Movie;
import com.example.android.popularmoviesstate1.data.remote.parser.MovieListJsonUtils;
import com.example.android.popularmoviesstate1.data.remote.requests.MoviePopularRequest;
import com.example.android.popularmoviesstate1.data.remote.requests.MovieTopRatedRequest;
import com.example.android.popularmoviesstate1.enums.MovieEnum;

import java.net.URL;
import java.util.List;

public class MovieListTask extends AsyncTask<MovieEnum, Void, List<Movie>> {

    //region Fields

    private final OnMovieListTaskListener onMovieListTaskListener;

    //endregion

    //region Constructors

    public MovieListTask(OnMovieListTaskListener onMovieListTaskListener){
        this.onMovieListTaskListener = onMovieListTaskListener;
    }

    //endregion

    //region Override Methods & Callbacks

    @Override
    protected List<Movie> doInBackground(MovieEnum... params) {
        if (params.length == 0) {
            return null;
        }

        switch (params[0]){
            case POPULAR:
                return getMoviePopularList();
            case TOP_RATED:
                return getMovieTopRatedList();
            default:
                return null;
        }
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

            return MovieListJsonUtils.getMovieListFromJson(jsonMovieListResponse);
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

            return MovieListJsonUtils.getMovieListFromJson(jsonMovieListResponse);
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
