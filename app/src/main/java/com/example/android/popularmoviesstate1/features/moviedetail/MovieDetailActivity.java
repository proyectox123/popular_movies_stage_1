package com.example.android.popularmoviesstate1.features.moviedetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmoviesstate1.R;
import com.example.android.popularmoviesstate1.data.remote.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    //region Constants

    public static final String EXTRA_MOVIE = "com.example.android.popularmoviesstate1.EXTRA_MOVIE";

    //endregion

    //region Fields

    private ImageView movieDetailPosterImageView;
    private TextView movieDetailTitleTextView;
    private TextView movieDetailReleaseDateTextView;
    private TextView movieDetailVoteAverageTextView;
    private TextView movieDetailDescriptionTextView;

    //endregion

    //region Override Methods & Callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieDetailPosterImageView = findViewById(R.id.movieDetailPosterImageView);
        movieDetailTitleTextView = findViewById(R.id.movieDetailTitleTextView);
        movieDetailReleaseDateTextView = findViewById(R.id.movieDetailReleaseDateTextView);
        movieDetailVoteAverageTextView = findViewById(R.id.movieDetailVoteAverageTextView);
        movieDetailDescriptionTextView = findViewById(R.id.movieDetailDescriptionTextView);

        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //endregion

    //region Private Methods

    private void initData(){
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
            return;
        }

        Movie movie = intent.getParcelableExtra(EXTRA_MOVIE);
        if (movie == null) {
            closeOnError();
            return;
        }

        initMovieDetailData(movie);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.error_movie_detail, Toast.LENGTH_SHORT).show();
    }

    private void initMovieDetailData(Movie movie){
        Picasso.get()
                .load(movie.getPosterPath())
                .placeholder(R.drawable.ic_movie_black_48dp)
                .error(R.drawable.ic_movie_black_48dp)
                .into(movieDetailPosterImageView);
        movieDetailTitleTextView.setText(movie.getTitle());
        movieDetailReleaseDateTextView.setText(movie.getReleaseDate());
        movieDetailVoteAverageTextView.setText(getVoteAverageLabel(movie.getVoteAverage()));
        movieDetailDescriptionTextView.setText(movie.getPlotSynopsis());
    }

    private String getVoteAverageLabel(double voteAverage){
        String voteAverageLabel = voteAverage + "/10";
        return getString(R.string.text_movie_detail_vote_average, voteAverageLabel);
    }

    //endregion
}
