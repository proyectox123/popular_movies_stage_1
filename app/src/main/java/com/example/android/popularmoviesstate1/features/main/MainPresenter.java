package com.example.android.popularmoviesstate1.features.main;

import com.example.android.popularmoviesstate1.data.remote.models.Movie;
import com.example.android.popularmoviesstate1.enums.MovieEnum;

import java.util.List;

public class MainPresenter implements MainNavigator.Presenter, MainNavigator.InteractorOutput{

    //region Fields

    private final MainNavigator.View view;
    private final MainNavigator.Interactor interactor;

    //endregion

    //region Constructors

    public MainPresenter(MainNavigator.View view){
        this.view = view;

        this.interactor = new MainInteractor(this);
    }

    //endregion

    //region Override Methods & Callbacks

    @Override
    public void initMovieList(MovieEnum movieType) {
        interactor.initMovieList(movieType);
    }

    @Override
    public void hideProgressBar() {
        view.hideProgressBar();
    }

    @Override
    public void showErrorMessage() {
        view.showErrorMessage();
    }

    @Override
    public void showProgressBar() {
        view.showProgressBar();
    }

    @Override
    public void updateMovieList(List<Movie> movieList) {
        view.updateMovieList(movieList);
    }

    //endregion
}