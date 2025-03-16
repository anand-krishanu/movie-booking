package com.anand.movie_booking.controller;

import com.anand.movie_booking.entity.Booking;
import com.anand.movie_booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.bookTicket(booking));
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<Integer> cancelTicket(@PathVariable int bookingId) {
        return ResponseEntity.ok(bookingService.cancelTicket(bookingId));
    }

    @GetMapping("/available-seats/{movieId}")
    public ResponseEntity<List<Integer>> getAvailableSeats(@PathVariable int movieId) {
        return ResponseEntity.ok(bookingService.getAvailableSeats(movieId));
    }
}
