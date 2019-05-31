package com.example.android.popularmoviesstate1.data.remote.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    //region Constants

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    //endregion

    //region Fields

    private final int id;
    private final String title;
    private final String releaseDate;
    private final String posterPath;
    private final double voteAverage;
    private final String plotSynopsis;

    //endregion

    //region Constructors

    public Movie(int id, String title, String releaseDate, String posterPath, double voteAverage, String plotSynopsis) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
    }

    private Movie(Parcel in){
        this.id = in.readInt();
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.posterPath = in.readString();
        this.voteAverage = in.readDouble();
        this.plotSynopsis = in.readString();
    }

    //endregion

    //region Override Methods & Callbacks

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.releaseDate);
        dest.writeString(this.posterPath);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.plotSynopsis);
    }

    //endregion

    //region Public Methods

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    //endregion
}