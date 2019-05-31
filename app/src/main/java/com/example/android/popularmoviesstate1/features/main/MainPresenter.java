package com.example.android.popularmoviesstate1.features.main;

import android.content.Context;

import com.example.android.popularmoviesstate1.data.remote.models.Movie;

import java.util.List;

public class MainPresenter implements MainNavigator.Presenter, MainNavigator.InteractorOutput{

    //region Fields

    private MainNavigator.View view;
    private MainNavigator.Interactor interactor;

    //endregion

    //region Constructors

    public MainPresenter(Context context, MainNavigator.View view){
        this.view = view;

        this.interactor = new MainInteractor(context, this);
    }

    //endregion

    //region Override Methods & Callbacks

    @Override
    public void initMovieList() {
        interactor.initMovieList();
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