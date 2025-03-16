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

    public int addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (movie_id, seat_id, user_name) VALUES (:movieId, :seatId, :userName)";
        return jdbcTemplate.update(sql, Map.of(
                "movieId", booking.getMovieId(),
                "seatId", booking.getSeatId(), // ✅ Fix field name
                "userName", booking.getUserName() // ✅ Fix field name
        ));
    }

    public int deleteBooking(int bookingId) {
        String sql = "DELETE FROM bookings WHERE booking_id = :bookingId";
        return jdbcTemplate.update(sql, Map.of("bookingId", bookingId));
    }

    private static class BookingRowMapper implements RowMapper<Booking> {
        @Override
        public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("movie_id"),
                    rs.getInt("seat_id"), // ✅ Fix column name
                    rs.getString("user_name") // ✅ Fix column name
            );
        }
    }
}