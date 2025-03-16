package com.anand.movie_booking.repository;

import com.anand.movie_booking.entity.Seat;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SeatRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SeatRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public String addSeat(Seat seat) {
        String sql = "INSERT INTO seats (movie_id, seat_number, is_booked) VALUES (:movieId, :seatNumber, :isBooked)";

        Map<String, Object> params = new HashMap<>();
        params.put("movieId", seat.getMovieId());
        params.put("seatNumber", seat.getSeatNumber());
        params.put("isBooked", seat.isBooked());

        jdbcTemplate.update(sql, params);
        return "Seat Added!";
    }


    public List<Seat> getSeatsByMovie(int movieId) {
        String sql = "SELECT seat_id, movie_id, seat_number, is_booked FROM seats WHERE movie_id = :movieId ORDER BY seat_number ASC";

        Map<String, Object> params = new HashMap<>();
        params.put("movieId", movieId);

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> mapSeat(rs));
    }


    public String bookSeat(int seatId) {
        String sql = "UPDATE seats SET is_booked = true WHERE seat_id = :seatId AND is_booked = false";

        Map<String, Object> params = new HashMap<>();
        params.put("seatId", seatId);

        int rowsAffected = jdbcTemplate.update(sql, params);
        return rowsAffected > 0 ? "Seat Booked!" : "Seat Already Booked or Not Found!";
    }

    public String cancelBooking(int seatId) {
        String sql = "UPDATE seats SET is_booked = false WHERE seat_id = :seatId";

        Map<String, Object> params = new HashMap<>();
        params.put("seatId", seatId);

        int rowsAffected = jdbcTemplate.update(sql, params);
        return rowsAffected > 0 ? "Booking Canceled!" : "Seat Not Found!";
    }

    public List<Integer> getAvailableSeats(int movieId) {
        String sql = "SELECT seat_number FROM seats WHERE movie_id = :movieId AND seat_id NOT IN (SELECT seat_id FROM bookings WHERE movie_id = :movieId)";
        return jdbcTemplate.queryForList(sql, Map.of("movieId", movieId), Integer.class);
    }

    public int getTotalSeatsForMovie(int movieId) {
        String sql = "SELECT COUNT(*) FROM seats WHERE movie_id = :movieId";
        return jdbcTemplate.queryForObject(sql, Map.of("movieId", movieId), Integer.class);
    }

    private Seat mapSeat(ResultSet rs) throws SQLException {
        return new Seat(
                rs.getInt("seat_id"),
                rs.getInt("movie_id"),
                rs.getInt("seat_number"),
                rs.getBoolean("is_booked")
        );
    }
}
