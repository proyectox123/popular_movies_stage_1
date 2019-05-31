package com.example.android.popularmoviesstate1.features.moviedetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmoviesstate1.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailNavigator.View {

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

    @Override
    public void closeOnError() {
        finish();
        Toast.makeText(this, R.string.error_movie_detail, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMovieDetailPoster(String posterPath) {
        Picasso.get()
                .load(posterPath)
                .placeholder(R.drawable.ic_movie_black_48dp)
                .error(R.drawable.ic_movie_black_48dp)
                .into(movieDetailPosterImageView);
    }

    @Override
    public void loadMovieDetailTitle(String title) {
        movieDetailTitleTextView.setText(title);
    }

    @Override
    public void loadMovieDetailReleaseDate(String releaseDate) {
        movieDetailReleaseDateTextView.setText(releaseDate);
    }

    @Override
    public void loadMovieDetailVoteAverage(String voteAverage) {
        movieDetailVoteAverageTextView.setText(voteAverage);
    }

    @Override
    public void loadMovieDetailDescription(String plotSynopsis) {
        movieDetailDescriptionTextView.setText(plotSynopsis);
    }

    //endregion

    //region Private Methods

    private void initData(){
        MovieDetailNavigator.Presenter presenter = new MovieDetailPresenter(this, this);
        presenter.validateMovieDetailExtraData(getIntent());
    }

    //endregion
}
