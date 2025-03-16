package com.anand.movie_booking.entity;

public class Booking {
    private int bookingId;
    private int movieId;
    private int seatId;
    private String userName;

    public Booking() {
    }

    public Booking(int bookingId, int movieId, int seatId, String userName) {
        this.bookingId = bookingId;
        this.movieId = movieId;
        this.seatId = seatId;
        this.userName = userName;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", movieId=" + movieId +
                ", seatId=" + seatId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
