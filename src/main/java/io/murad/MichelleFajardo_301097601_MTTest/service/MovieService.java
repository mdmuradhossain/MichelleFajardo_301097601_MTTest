package io.murad.MichelleFajardo_301097601_MTTest.service;


import io.murad.MichelleFajardo_301097601_MTTest.model.Movie;
import io.murad.MichelleFajardo_301097601_MTTest.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

//    public Movie updateMovie(Long id, Movie movie) {
//        Movie updateMovie = movieRepository.findById(id).get();
//        updateMovie.setMovieName(movie.getMovieName());
//        updateMovie.setCountry(movie.getCountry());
//        updateMovie.setDuration(movie.getDuration());
//        updateMovie.setGenre(movie.getGenre());
//        updateMovie.setYear(movie.getYear());
//        return movieRepository.save(updateMovie);
//    }
//
//    public void deleteMovie(Long id) {
//        movieRepository.deleteById(id);
//    }

}
