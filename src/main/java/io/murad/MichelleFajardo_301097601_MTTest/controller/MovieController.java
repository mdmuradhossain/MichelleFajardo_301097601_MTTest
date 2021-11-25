package io.murad.MichelleFajardo_301097601_MTTest.controller;

import io.murad.MichelleFajardo_301097601_MTTest.model.Movie;
import io.murad.MichelleFajardo_301097601_MTTest.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/saveMovie")
    public String addMovie(@ModelAttribute("movie") Movie movie){
        movieService.saveMovie(movie);
        return  "redirect:/movies/showMovie/"+movie.getId();
//        return "movie/displayAll";
    }

    @GetMapping(path = "/showMovie/{id}")
    public String displayMovie(@PathVariable("id") Long id,Model model){
       Movie movie = movieService.getMovie(id);
        model.addAttribute("movie",movie);
        return  "movie/display";
    }

    @GetMapping(path = "/displayAllMovies")
    public String displayAll(Model model){
        List<Movie> movieList = movieService.getAllMovies();
        model.addAttribute("movies",movieList);
        return "movie/displayAll";
    }

}
