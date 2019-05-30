package com.example.android.popularmoviesstate1.data.remote;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.popularmoviesstate1.data.remote.models.Movie;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

public class MovieListTask extends AsyncTask<String, Void, List<Movie>> {

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
    protected List<Movie> doInBackground(String... strings) {
        URL weatherRequestUrl = NetworkUtils.buildUrl();

        if(contextRef != null && contextRef.get() != null){
            try {
                String jsonMovieListResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);

                return MovieListJsonUtils.getMovieListFromJson(contextRef.get(), jsonMovieListResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    //region Inner Classes & Interfaces

    public interface OnMovieListTaskListener {
        public void updateMovieList(List<Movie> movieList);
        public void showErrorTask();
    }

    //endregion
}
