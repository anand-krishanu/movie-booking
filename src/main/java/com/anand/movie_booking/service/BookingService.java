package com.anand.movie_booking.service;

import com.anand.movie_booking.entity.Booking;
import com.anand.movie_booking.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public int bookTicket(Booking booking) {
        return bookingRepository.addBooking(booking);
    }

    @Transactional
    public int cancelTicket(int bookingId) {
        return bookingRepository.deleteBooking(bookingId);
    }

    public List<Booking> getAvailableSeats(int movieId) {
        return bookingRepository.getAllBookings(movieId);
    }
}
