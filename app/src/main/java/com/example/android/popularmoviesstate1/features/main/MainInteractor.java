package com.example.android.popularmoviesstate1.features.main;

import com.example.android.popularmoviesstate1.data.remote.MovieListTask;
import com.example.android.popularmoviesstate1.data.remote.models.Movie;
import com.example.android.popularmoviesstate1.enums.MovieEnum;

import java.util.List;

public class MainInteractor implements MainNavigator.Interactor, MovieListTask.OnMovieListTaskListener {

    //region Fields

    private final MainNavigator.InteractorOutput interactorOutput;

    //endregion

    //region Constructors

    MainInteractor(MainNavigator.InteractorOutput interactorOutput){
        this.interactorOutput = interactorOutput;
    }

    //endregion

    //region Override Methods & Callbacks


    @Override
    public void initMovieList(MovieEnum movieType) {
        interactorOutput.showProgressBar();

        new MovieListTask(this).execute(movieType);
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