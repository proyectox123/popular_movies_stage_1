package com.example.android.popularmoviesstate1.features.main;

import android.content.Context;
import android.os.Handler;

import com.example.android.popularmoviesstate1.data.remote.MovieListTask;
import com.example.android.popularmoviesstate1.data.remote.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainInteractor implements MainNavigator.Interactor, MovieListTask.OnMovieListTaskListener {

    private Context context;

    private MainNavigator.InteractorOutput interactorOutput;

    public MainInteractor(Context context, MainNavigator.InteractorOutput interactorOutput){
        this.context = context;
        this.interactorOutput = interactorOutput;
    }

    @Override
    public void initMovieList() {
        interactorOutput.showProgressBar();

        new MovieListTask(context, this).execute();
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
}