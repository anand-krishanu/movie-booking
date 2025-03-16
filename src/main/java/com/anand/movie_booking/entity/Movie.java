package com.anand.movie_booking.entity;

import java.time.LocalDateTime;

public class Movie {
    private int movieId;
    private String movieName;
    private LocalDateTime movieTime;

    public Movie() {
    }

    public Movie(int movieId, String movieName, LocalDateTime movieTime) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieTime = movieTime;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDateTime getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(LocalDateTime movieTime) {
        this.movieTime = movieTime;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieTime=" + movieTime +
                '}';
    }
}
