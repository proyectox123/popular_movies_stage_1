package com.example.android.popularmoviesstate1.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmoviesstate1.R;
import com.example.android.popularmoviesstate1.data.remote.models.Movie;
import com.example.android.popularmoviesstate1.enums.MovieEnum;
import com.example.android.popularmoviesstate1.features.movie.MovieListAdapter;
import com.example.android.popularmoviesstate1.features.moviedetail.MovieDetailActivity;

import java.util.List;

import static com.example.android.popularmoviesstate1.features.moviedetail.MovieDetailActivity.EXTRA_MOVIE;

public class MainActivity extends AppCompatActivity implements MainNavigator.View,
        MovieListAdapter.OnMovieListAdapterListener{

    //region Constants

    private final static int MOVIE_GRID_SPAN_COUNT = 2;

    //endregion

    //region Fields

    private MovieEnum movieEnum = MovieEnum.TOP_RATED;

    private ProgressBar movieListProgressBar;
    private RecyclerView movieListView;
    private SwipeRefreshLayout movieListSwipeRefresh;
    private TextView noDataLabel;

    private MainNavigator.Presenter presenter;

    private MovieListAdapter movieListAdapter;

    //endregion

    //region Override Methods & Callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListSwipeRefresh = findViewById(R.id.movieListSwipeRefresh);
        movieListProgressBar = findViewById(R.id.movieListProgressBar);
        movieListView = findViewById(R.id.movieListView);
        noDataLabel = findViewById(R.id.noDataLabel);

        movieListAdapter = new MovieListAdapter(this);

        movieListView.setHasFixedSize(true);
        movieListView.setLayoutManager(new GridLayoutManager(this, MOVIE_GRID_SPAN_COUNT));
        movieListView.setAdapter(movieListAdapter);

        movieListSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.initMovieList(movieEnum);
            }
        });

        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_movie_most_popular:
                selectMovieMostPopularOption();
                return true;
            case R.id.menu_movie_highest_rated:
                selectMovieHighestRatedOption();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClickedMovieItem(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void hideProgressBar() {
        movieListProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        movieListSwipeRefresh.setRefreshing(false);
        movieListView.setVisibility(View.GONE);
        noDataLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        movieListProgressBar.setVisibility(View.VISIBLE);
        movieListView.setVisibility(View.GONE);
        noDataLabel.setVisibility(View.GONE);
    }

    @Override
    public void updateMovieList(List<Movie> movieList) {
        movieListSwipeRefresh.setRefreshing(false);
        movieListView.setVisibility(View.VISIBLE);
        movieListAdapter.setMovieList(movieList);
    }

    //region Private Methods

    private void initData(){
        presenter = new MainPresenter(this);
        presenter.initMovieList(movieEnum);
    }

    private void selectMovieMostPopularOption(){
        movieEnum = MovieEnum.POPULAR;
        presenter.initMovieList(movieEnum);
    }

    private void selectMovieHighestRatedOption(){
        movieEnum = MovieEnum.TOP_RATED;
        presenter.initMovieList(movieEnum);
    }

    //endregion
}
