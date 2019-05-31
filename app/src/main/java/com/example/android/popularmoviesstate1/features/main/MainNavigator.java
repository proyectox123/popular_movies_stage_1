package com.example.android.popularmoviesstate1.features.main;

import com.example.android.popularmoviesstate1.data.remote.models.Movie;

import java.util.List;

public interface MainNavigator {

    interface View {
        void hideProgressBar();
        void showErrorMessage();
        void showProgressBar();
        void updateMovieList(List<Movie> movieList);
    }

    interface Presenter {
        void initMovieList();
        void showMovieListByHighestRated();
        void showMovieListByMostPopular();
    }

    interface Interactor {
        void initMovieList();
        void showMovieListByHighestRated();
        void showMovieListByMostPopular();
    }

    interface InteractorOutput {
        void hideProgressBar();
        void showErrorMessage();
        void showProgressBar();
        void updateMovieList(List<Movie> movieList);
    }
}