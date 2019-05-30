package com.example.android.popularmoviesstate1.features.main;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmoviesstate1.R;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>{

    //region Fields

    private List<String> movieList;

    private OnMovieListAdapterListener onMovieListAdapterListener;

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
        final String movie = movieList.get(position);
        movieListViewHolder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    //endregion

    //region Public Methods

    public void setMovieList(List<String> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    //endregion

    //region Private Methods

    private View createItemView(@NonNull ViewGroup viewGroup){
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        //noinspection ConstantConditions
        return inflater.inflate(getLayoutIdForListItem(), viewGroup, shouldAttachToParentImmediately);
    }

    @LayoutRes
    private int getLayoutIdForListItem(){
        return R.layout.layout_item_movie_poster;
    }

    //endregion

    //region Inner classes & Interfaces

    class MovieListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //region Fields

        private ImageView moviePosterImageView;

        private String movie;

        //endregion

        //region Constructors

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);

            moviePosterImageView = itemView.findViewById(R.id.moviePosterImageView);
            moviePosterImageView.setOnClickListener(this);
        }

        //endregion

        //region Override Methods & Callbacks

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.moviePosterImageView:
                    onMovieListAdapterListener.onClickedMovieItem(movie);
                    break;
            }
        }

        //endregion

        //Public Methods

        public void bind(String movie){
            this.movie = movie;
        }

        //endregion
    }

    public interface OnMovieListAdapterListener {
        public void onClickedMovieItem(String movie);
    }

    //endregion
}