package com.anand.movie_booking.service;

import com.anand.movie_booking.entity.Booking;
import com.anand.movie_booking.repository.BookingRepository;
import com.anand.movie_booking.repository.MovieRepository;
import com.anand.movie_booking.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final MovieRepository movieRepository;
    private final SeatRepository seatRepository;

    public BookingService(BookingRepository bookingRepository, MovieRepository movieRepository, SeatRepository seatRepository) {
        this.bookingRepository = bookingRepository;
        this.movieRepository = movieRepository;
        this.seatRepository = seatRepository;
    }

    @Transactional
    public String bookTicket(Booking booking) {

        List<Integer> availableSeats = seatRepository.getAvailableSeats(booking.getMovieId());
        if (!availableSeats.contains(booking.getSeatId())) {
            return "Seat already booked!";
        }

        int totalSeats = seatRepository.getTotalSeatsForMovie(booking.getMovieId());
        int availableSeatsCount = availableSeats.size();
        double price = calculatePrice(booking.getMovieId(), booking.getSeatId(), availableSeatsCount, totalSeats);

        return bookingRepository.addBooking(booking, price);
    }


    @Transactional
    public int cancelTicket(int bookingId) {
        int rowsDeleted = bookingRepository.deleteBooking(bookingId);
        if (rowsDeleted > 0) {
            seatRepository.cancelBooking(bookingId);
        }
        return rowsDeleted;
    }


    public List<Integer> getAvailableSeats(int movieId) {
        return bookingRepository.getAvailableSeats(movieId);
    }

    public double calculatePrice(int movieId, int seatNumber, int availableSeats, int totalSeats) {
        double basePrice = movieRepository.getBasePrice(movieId);
        Timestamp movieTime = movieRepository.getMovieTime(movieId);
        LocalDateTime movieDateTime = movieTime.toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        long hoursLeft = ChronoUnit.HOURS.between(now, movieDateTime);

        double seatAvailability = (double) availableSeats / totalSeats;
        if (seatAvailability < 0.2) {
            basePrice *= 1.3;
        } else if (seatAvailability > 0.8) {
            basePrice *= 0.9;
        }

        if (hoursLeft < 2) {
            basePrice *= 1.2;
        }

        if (seatNumber <= 5) {
            basePrice += 50;
        }

        return basePrice;
    }
}
