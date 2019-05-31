package com.example.android.popularmoviesstate1.features.moviedetail;

import android.content.Intent;

public interface MovieDetailNavigator {

    interface View {
        void closeOnError();
        void loadMovieDetailPoster(String posterPath);
        void loadMovieDetailTitle(String title);
        void loadMovieDetailReleaseDate(String releaseDate);
        void loadMovieDetailVoteAverage(String voteAverage);
        void loadMovieDetailDescription(String plotSynopsis);
    }

    interface Presenter {
        void validateMovieDetailExtraData(Intent intent);
    }

    interface Interactor {
        void validateMovieDetailExtraData(Intent intent);
    }

    interface InteractorOutput {
        void closeOnError();
        void loadMovieDetailPoster(String posterPath);
        void loadMovieDetailTitle(String title);
        void loadMovieDetailReleaseDate(String releaseDate);
        void loadMovieDetailVoteAverage(String voteAverage);
        void loadMovieDetailDescription(String plotSynopsis);
    }
}