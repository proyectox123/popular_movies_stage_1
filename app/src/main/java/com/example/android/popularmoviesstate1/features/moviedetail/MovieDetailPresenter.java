package com.example.android.popularmoviesstate1.features.moviedetail;

import android.content.Context;
import android.content.Intent;

public class MovieDetailPresenter implements MovieDetailNavigator.Presenter,
        MovieDetailNavigator.InteractorOutput {

    //region Fields

    private final MovieDetailNavigator.View view;
    private final MovieDetailNavigator.Interactor interactor;

    //endregion

    //region Constructors

    public MovieDetailPresenter(Context context, MovieDetailNavigator.View view){
        this.view = view;

        this.interactor = new MovieDetailInteractor(context, this);
    }

    //endregion

    //region Override Methods & Callbacks

    @Override
    public void validateMovieDetailExtraData(Intent intent) {
        interactor.validateMovieDetailExtraData(intent);
    }

    @Override
    public void closeOnError() {
        view.closeOnError();
    }

    @Override
    public void loadMovieDetailPoster(String posterPath) {
        view.loadMovieDetailPoster(posterPath);
    }

    @Override
    public void loadMovieDetailTitle(String title) {
        view.loadMovieDetailTitle(title);
    }

    @Override
    public void loadMovieDetailReleaseDate(String releaseDate) {
        view.loadMovieDetailReleaseDate(releaseDate);
    }

    @Override
    public void loadMovieDetailVoteAverage(String voteAverage) {
        view.loadMovieDetailVoteAverage(voteAverage);
    }

    @Override
    public void loadMovieDetailDescription(String plotSynopsis) {
        view.loadMovieDetailDescription(plotSynopsis);
    }

    //endregion
}