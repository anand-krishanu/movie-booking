package com.anand.movie_booking.repository;

import com.anand.movie_booking.entity.Movie;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MovieRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> getMovies(int page, int size) {
        String sql = "SELECT * FROM movies ORDER BY movie_time ASC LIMIT :size OFFSET :offset";

        Map<String, Object> params = new HashMap<>();
        params.put("size", size);
        params.put("offset", (page - 1) * size);

        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
                new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getTimestamp("movie_time").toLocalDateTime()
                )
        );
    }

    public String addMovie(Movie movie) {
        String sql = "INSERT INTO movies (title, movie_time) VALUES (:movieName, :movieTime)";

        Map<String, Object> params = new HashMap<>();
        params.put("movieName", movie.getMovieName());
        params.put("movieTime", Timestamp.valueOf(movie.getMovieTime()));

        jdbcTemplate.update(sql, params);
        return "Movie Added Successfully!";
    }

    public String deleteMovie(int movieId) {
        String sql = "DELETE FROM movies WHERE movie_id = :movieId";

        Map<String, Object> params = new HashMap<>();
        params.put("movieId", movieId);

        int rowsAffected = jdbcTemplate.update(sql, params);
        return rowsAffected > 0 ? "Movie Deleted!" : "Movie Not Found!";
    }

    public double getBasePrice(int movieId) {
        String sql = "SELECT base_price FROM movies WHERE movie_id = :movieId";
        return jdbcTemplate.queryForObject(sql, Map.of("movieId", movieId), Double.class);
    }

    public Timestamp getMovieTime(int movieId) {
        String sql = "SELECT movie_time FROM movies WHERE movie_id = :movieId";
        return jdbcTemplate.queryForObject(sql, Map.of("movieId", movieId), Timestamp.class);
    }
}
