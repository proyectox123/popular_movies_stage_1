package com.example.android.popularmoviesstate1.features.moviedetail;

import android.content.Context;
import android.content.Intent;

import com.example.android.popularmoviesstate1.R;
import com.example.android.popularmoviesstate1.data.remote.models.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.android.popularmoviesstate1.features.moviedetail.MovieDetailActivity.EXTRA_MOVIE;

public class MovieDetailInteractor implements MovieDetailNavigator.Interactor {

    //region Fields

    private final Context context;

    private final MovieDetailNavigator.InteractorOutput interactorOutput;

    //endregion

    //region Constructors

    public MovieDetailInteractor(Context context,
                                 MovieDetailNavigator.InteractorOutput interactorOutput){
        this.context = context;
        this.interactorOutput = interactorOutput;
    }

    //endregion

    //region Public Methods

    public void validateMovieDetailExtraData(Intent intent){
        if (intent == null) {
            interactorOutput.closeOnError();
            return;
        }

        Movie movie = intent.getParcelableExtra(EXTRA_MOVIE);
        if (movie == null) {
            interactorOutput.closeOnError();
            return;
        }

        validateMovieDetailData(movie);
    }

    //endregion

    //region Private Methods

    private void validateMovieDetailData(Movie movie){
        interactorOutput.loadMovieDetailPoster(movie.getPosterPath());
        interactorOutput.loadMovieDetailTitle(movie.getTitle());
        interactorOutput.loadMovieDetailReleaseDate(getReleaseDate(movie.getReleaseDate()));
        interactorOutput.loadMovieDetailVoteAverage(getVoteAverageLabel(movie.getVoteAverage()));
        interactorOutput.loadMovieDetailDescription(movie.getPlotSynopsis());
    }

    private String getReleaseDate(String releaseDate){
        SimpleDateFormat inFormatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date date = inFormatDate.parse(releaseDate);
            SimpleDateFormat outFormatDate = new SimpleDateFormat("yyyy", Locale.US);
            return outFormatDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return releaseDate;
    }

    private String getVoteAverageLabel(double voteAverage){
        String voteAverageLabel = voteAverage + "/10";
        return context.getString(R.string.text_movie_detail_vote_average, voteAverageLabel);
    }

    //endregion
}