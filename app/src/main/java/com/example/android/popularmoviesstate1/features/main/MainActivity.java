package com.example.android.popularmoviesstate1.features.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmoviesstate1.R;
import com.example.android.popularmoviesstate1.data.remote.models.Movie;
import com.example.android.popularmoviesstate1.decorations.SpaceItemDecoration;
import com.example.android.popularmoviesstate1.features.movie.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainNavigator.View,
        MovieListAdapter.OnMovieListAdapterListener{

    //region Constants

    private final static int MOVIE_GRID_SPAN_COUNT = 2;
    private final static int MOVIE_GRID_PADDING = 8;

    //endregion

    //region Fields

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
        movieListView.addItemDecoration(new SpaceItemDecoration(MOVIE_GRID_PADDING));
        movieListView.setAdapter(movieListAdapter);

        movieListSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.initMovieList();
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
                presenter.showMovieListByMostPopular();
                return true;
            case R.id.menu_movie_highest_rated:
                presenter.showMovieListByHighestRated();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClickedMovieItem(Movie movie) {
        Toast.makeText(this, "Movie: " + movie.getPosterPath(), Toast.LENGTH_LONG).show();
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
        presenter = new MainPresenter(this, this);
        presenter.initMovieList();
    }

    //endregion
}
