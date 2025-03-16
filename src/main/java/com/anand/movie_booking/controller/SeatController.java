package com.anand.movie_booking.controller;

import com.anand.movie_booking.entity.Seat;
import com.anand.movie_booking.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSeat(@RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.addSeat(seat));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<Seat>> getSeatsByMovie(@PathVariable int movieId) {
        return ResponseEntity.ok(seatService.getSeatsForMovie(movieId));
    }
}
