package com.example.android.popularmoviesstate1.features.main;

import com.example.android.popularmoviesstate1.data.remote.models.Movie;

import java.util.List;

public interface MainNavigator {

    interface View {
        void hideProgressBar();
        void showProgressBar();
        void updateMovieList(List<Movie> movieList);
    }

    interface Presenter {
        void initMovieList();
    }

    interface Interactor {
        void initMovieList();
    }

    interface InteractorOutput {
        void hideProgressBar();
        void showProgressBar();
        void updateMovieList(List<Movie> movieList);
    }
}