package com.api.movie_flicks.controller;

import com.api.movie_flicks.repository.MovieRepository;
import com.api.movie_flicks.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies/search/{keyword}")
    public ResponseEntity searchMovie(@PathVariable String keyword) {
        return new ResponseEntity(movieService.getSearchMovie(keyword), HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity getMovies() {
        return new ResponseEntity(movieService.getMovies(), HttpStatus.OK);
    }
}
