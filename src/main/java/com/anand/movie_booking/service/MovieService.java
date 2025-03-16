package com.anand.movie_booking.service;


import com.anand.movie_booking.entity.Movie;
import com.anand.movie_booking.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies(int page, int size) {
        return movieRepository.getMovies(page, size);
    }

    public String addNewMovie(Movie movie) {
        if (movie.getMovieTime().isBefore(LocalDateTime.now())) {
            return "Cannot add a movie with a past screening time!";
        }
        return movieRepository.addMovie(movie);
    }


    public String removeMovie(int movieId) {
        return movieRepository.deleteMovie(movieId);
    }
}
