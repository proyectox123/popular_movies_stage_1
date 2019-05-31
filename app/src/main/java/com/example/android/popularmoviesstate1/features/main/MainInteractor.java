package com.example.android.popularmoviesstate1.features.main;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.popularmoviesstate1.data.remote.MovieListTask;
import com.example.android.popularmoviesstate1.data.remote.models.Movie;

import java.util.List;

public class MainInteractor implements MainNavigator.Interactor, MovieListTask.OnMovieListTaskListener {

    private MovieListTask movieListTask;

    private MainNavigator.InteractorOutput interactorOutput;

    public MainInteractor(Context context, MainNavigator.InteractorOutput interactorOutput){
        this.interactorOutput = interactorOutput;

        this.movieListTask = new MovieListTask(context, this);
    }

    //region Override Methods & Callbacks

    @Override
    public void initMovieList() {
        interactorOutput.showProgressBar();

        movieListTask.execute(MovieListTask.MOVIE_TOP_RATED);
    }

    @Override
    public void showMovieListByHighestRated() {
        interactorOutput.showProgressBar();

        movieListTask.execute(MovieListTask.MOVIE_TOP_RATED);
    }

    @Override
    public void showMovieListByMostPopular() {
        interactorOutput.showProgressBar();

        movieListTask.execute(MovieListTask.MOVIE_POPULAR);
    }

    @Override
    public void updateMovieList(List<Movie> movieList) {
        interactorOutput.hideProgressBar();
        interactorOutput.updateMovieList(movieList);
    }

    @Override
    public void showErrorTask() {
        interactorOutput.hideProgressBar();
        interactorOutput.showErrorMessage();
    }

    //endregion
}