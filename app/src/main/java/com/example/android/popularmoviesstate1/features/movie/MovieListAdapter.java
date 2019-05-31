package com.example.android.popularmoviesstate1.features.movie;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmoviesstate1.R;
import com.example.android.popularmoviesstate1.data.remote.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>{

    //region Fields

    private List<Movie> movieList;

    private final OnMovieListAdapterListener onMovieListAdapterListener;

    //endregion

    //region Constructors

    public MovieListAdapter(OnMovieListAdapterListener onMovieListAdapterListener){
        this.onMovieListAdapterListener = onMovieListAdapterListener;
    }

    //endregion

    //region Override Methods & Callbacks

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieListViewHolder(createItemView(viewGroup));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder movieListViewHolder, int position) {
        final Movie movie = movieList.get(position);
        movieListViewHolder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    //endregion

    //region Public Methods

    public void setMovieList(List<Movie> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    //endregion

    //region Private Methods

    private View createItemView(@NonNull ViewGroup viewGroup){
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return inflater.inflate(getLayoutIdForListItem(), viewGroup, false);
    }

    //I think that this could be useful if I implement a BaseRecyclerView class
    @LayoutRes
    private int getLayoutIdForListItem(){
        return R.layout.layout_item_movie_poster;
    }

    //endregion

    //region Inner classes & Interfaces

    class MovieListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //region Fields

        private final ImageView moviePosterImageView;

        private Movie movie;

        //endregion

        //region Constructors

        MovieListViewHolder(@NonNull View itemView) {
            super(itemView);

            moviePosterImageView = itemView.findViewById(R.id.moviePosterImageView);
            moviePosterImageView.setOnClickListener(this);
        }

        //endregion

        //region Override Methods & Callbacks

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.moviePosterImageView) {
                onMovieListAdapterListener.onClickedMovieItem(movie);
            }
        }

        //endregion

        //Public Methods

        void bind(Movie movie){
            this.movie = movie;

            initMoviePoster(movie.getPosterPath());
        }

        //endregion

        //Private Methods

        private void initMoviePoster(String moviePosterPath){
            Picasso.get()
                    .load(moviePosterPath)
                    .placeholder(R.drawable.ic_movie_black_48dp)
                    .error(R.drawable.ic_movie_black_48dp)
                    .into(moviePosterImageView);
        }

        //endregion
    }

    public interface OnMovieListAdapterListener {
        void onClickedMovieItem(Movie movie);
    }

    //endregion
}