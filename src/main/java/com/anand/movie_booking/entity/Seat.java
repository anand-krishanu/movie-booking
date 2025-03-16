package com.anand.movie_booking.entity;

public class Seat {
    private int seatId;
    private int movieId;
    private int seatNumber;
    private boolean isBooked;

    public Seat() {
    }

    public Seat(int seatId, int movieId, int seatNumber, boolean isBooked) {
        this.seatId = seatId;
        this.movieId = movieId;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", movieId=" + movieId +
                ", seatNumber=" + seatNumber +
                ", isBooked=" + isBooked +
                '}';
    }
}
