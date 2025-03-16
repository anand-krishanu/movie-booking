package com.anand.movie_booking.repository;

import com.anand.movie_booking.entity.Booking;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class BookingRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BookingRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Booking> getAllBookings(int movieId) {
        String sql = "SELECT booking_id, movie_id, seat_id, user_name FROM bookings";
        return jdbcTemplate.query(sql, new BookingRowMapper());
    }

    public Booking getBookingById(int bookingId) {
        String sql = "SELECT booking_id, movie_id, seat_id, user_name FROM bookings WHERE booking_id = :bookingId";
        return jdbcTemplate.queryForObject(sql, Map.of("bookingId", bookingId), new BookingRowMapper());
    }

    public String addBooking(Booking booking, double price) {
        String sql = "INSERT INTO bookings (movie_id, seat_id, user_name, price) VALUES (:movieId, :seatId, :userName, :price)";
        int rows = jdbcTemplate.update(sql, Map.of(
                "movieId", booking.getMovieId(),
                "seatId", booking.getSeatId(),
                "userName", booking.getUserName(),
                "price", price
        ));
        return rows > 0 ? "Booking Confirmed!" : "Booking Failed!";
    }

    public int deleteBooking(int bookingId) {
        String sql = "DELETE FROM bookings WHERE booking_id = :bookingId";
        return jdbcTemplate.update(sql, Map.of("bookingId", bookingId));
    }

    public List<Integer> getAvailableSeats(int movieId) {
        String sql = "SELECT seat_id FROM seats WHERE movie_id = :movieId AND seat_id NOT IN (SELECT seat_id FROM bookings WHERE movie_id = :movieId)";
        return jdbcTemplate.queryForList(sql, Map.of("movieId", movieId), Integer.class);
    }

    private static class BookingRowMapper implements RowMapper<Booking> {
        @Override
        public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("movie_id"),
                    rs.getInt("seat_id"),
                    rs.getString("user_name")
            );
        }
    }
}