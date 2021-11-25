package io.murad.MichelleFajardo_301097601_MTTest.controller;

import io.murad.MichelleFajardo_301097601_MTTest.model.Movie;
import io.murad.MichelleFajardo_301097601_MTTest.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping(path = "/movieRecordForm")
    public String showMovieRecordForm(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie",movie);
        return "movie/movieRecordForm";
    }

    public String addMovie(@ModelAttribute("movie") Movie movie){
        movieService.saveMovie(movie);
        return "movie/displayAll";
    }

    public String displayMovie(@PathVariable("id") Long id,Model model){
       Movie movie = movieService.getMovie(id);
        model.addAttribute("movie",movie);
        return  "movie/display";
    }

    public String displayAll(Model model){
        List<Movie> movieList = movieService.getAllMovies();
        model.addAttribute("movies",movieList);
        return "movie/displayAll";
    }
}
