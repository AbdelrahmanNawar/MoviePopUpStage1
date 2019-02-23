package com.example.android.moviepopup;

public class MovieStructure {
    public MovieStructure(String titleMovie, String imageMovie, String overviewMovie, String releaseDateMovie, double voteAverage, double popularityOfMovie) {
        this.titleMovie = titleMovie;
        this.imageMovie = imageMovie;
        this.overviewMovie = overviewMovie;
        this.releaseDateMovie = releaseDateMovie;
        this.voteAverage = voteAverage;
        this.popularityOfMovie = popularityOfMovie;
    }

    public String titleMovie;
    public String imageMovie;
    public String overviewMovie;
    public String releaseDateMovie;
    public double voteAverage;
    public double popularityOfMovie;
}
