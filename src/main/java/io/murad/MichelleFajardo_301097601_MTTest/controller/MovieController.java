package io.murad.MichelleFajardo_301097601_MTTest.controller;

import io.murad.MichelleFajardo_301097601_MTTest.model.Movie;
import io.murad.MichelleFajardo_301097601_MTTest.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;


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
    public String addMovie(@ModelAttribute("movie") Movie movie, @RequestParam("file") MultipartFile file) throws IOException {
        movieService.saveMovie(movie);
        String imageName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        movie.setThumbnailName(imageName);
        movieService.saveMovie(movie);

        Path uploadPath = Paths.get("uploads/");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path imagePath = uploadPath.resolve(file.getOriginalFilename());
            System.out.println(imagePath.toFile().getAbsolutePath());
            Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("could not save");
        }
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


    @RequestMapping(value = "/showImage/{file}",method = RequestMethod.GET,produces = MediaType.ALL_VALUE)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable("file") String file) {
        if (!file.equals("") || file != null) {
            try {
                Path fileName = Paths.get("uploads", file);
                byte[] buffer = Files.readAllBytes(fileName);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok().body(byteArrayResource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.badRequest().build();
    }

}
