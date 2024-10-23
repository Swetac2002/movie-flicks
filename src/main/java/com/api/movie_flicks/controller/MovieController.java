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
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @GetMapping("/greetings")
    public String Greetings(){
        return "Hello World";
    }

    @GetMapping("/movies/search/{keyword}")
    public ResponseEntity searchMovie(@PathVariable String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getSearchedMovie(keyword));
    }
    /*
    @GetMapping("/get-books")
    public ResponseEntity<List<Book>> getBooks(){
        try{
            List<Book> books = new ArrayList<>();
            bookRepo.findAll().forEach(books::add);
            if(books.isEmpty()){
                return new ResponseEntity<>(books,HttpStatus.NO_CONTENT);
            }
            return (ResponseEntity<List<Book>>) books;
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody Book book){
        Book bookObj = bookRepo.save(book);
        return new ResponseEntity<>(bookObj,HttpStatus.CREATED);
    } */
}
