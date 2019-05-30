package com.example.android.popularmoviesstate1.data.remote.models;

public class Movie {

    private int id;
    private String title;
    private String releaseDate;
    private String posterPath;
    private double voteAverage;
    private String plotSynopsis;

    public Movie(int id, String title, String releaseDate, String posterPath, double voteAverage, String plotSynopsis) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
    }

    public int getId() {
        return id;
    }

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
}