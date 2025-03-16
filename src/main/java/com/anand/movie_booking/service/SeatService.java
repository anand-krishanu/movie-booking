package com.anand.movie_booking.service;

import com.anand.movie_booking.entity.Seat;
import com.anand.movie_booking.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public String addSeat(Seat seat) {
        return seatRepository.addSeat(seat);
    }

    public List<Seat> getSeatsForMovie(int movieId) {
        return seatRepository.getSeatsByMovie(movieId);
    }
}
