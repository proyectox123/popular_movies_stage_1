package com.example.android.popularmoviesstate1.features.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.popularmoviesstate1.R;
import com.example.android.popularmoviesstate1.decorations.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.OnMovieListAdapterListener{

    //region Fields

    private RecyclerView movieListView;

    private MovieListAdapter movieListAdapter;

    //endregion

    //region Override Methods & Callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListView = findViewById(R.id.movieListView);

        movieListAdapter = new MovieListAdapter(this);

        movieListView.setHasFixedSize(true);
        movieListView.setLayoutManager(new GridLayoutManager(this, 2));
        movieListView.addItemDecoration(new SpaceItemDecoration(8));
        movieListView.setAdapter(movieListAdapter);

        initData();
    }

    @Override
    public void onClickedMovieItem(String movie) {
        Toast.makeText(this, "Movie: " + movie, Toast.LENGTH_LONG).show();
    }

    //region Private Methods

    private void initData(){
        List<String> movieList = new ArrayList<>();
        for(int i = 1; i <= 20; i++){
            movieList.add(Integer.toString(i));
        }

        movieListAdapter.setMovieList(movieList);
    }

    //endregion
}
