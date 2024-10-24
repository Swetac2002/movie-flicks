package com.api.movie_flicks.service;

import com.api.movie_flicks.dto.MovieDTO;
import com.api.movie_flicks.model.Movie;
import com.api.movie_flicks.model.Rating;
import com.api.movie_flicks.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final RestTemplate restTemplate;

    @Value("${movie.data.url}")
    private String movieDataUrl;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovieRepository movieRepository;

    MovieService() {
        restTemplate = new RestTemplate();
    }
    public MovieDTO getSearchMovie(String keyword) {
        MovieDTO movieDTO = restTemplate.getForEntity(movieDataUrl+"t="+keyword, MovieDTO.class).getBody();
        if(movieDTO.getResponse() && movieDTO.getError()==null) {
            Movie movie = toEntity(movieDTO);
            if(!movieRepository.findByTitle(movieDTO.getTitle()).isPresent()) {
                movieRepository.save(movie);
            }
        }
        return movieDTO;
    }

    public List<MovieDTO> getMovies() {
        List<Movie> movies = movieRepository.findAll();
        System.out.println(movies);
        return toDTOList(movies);
    }

    public Movie toEntity(MovieDTO movieDTO) {
        Movie movie = objectMapper.convertValue(movieDTO, Movie.class);

        if (movieDTO.getRatings() != null) {
            List<Rating> ratings = movieDTO.getRatings().stream()
                    .map(ratingDTO -> {
                        Rating rating = objectMapper.convertValue(ratingDTO, Rating.class);
                        rating.setMovie(movie);
                        return rating;
                    })
                    .collect(Collectors.toList());
            movie.setRatings(ratings);
        }
        return movie;
    }

    public MovieDTO toDTO(Movie movie) {
        MovieDTO movieDTO = objectMapper.convertValue(movie, MovieDTO.class);
        movieDTO.setRatings(objectMapper.convertValue(movie.getRatings(), List.class));
        return movieDTO;
    }

    public List<MovieDTO> toDTOList(List<Movie> movies) {
        return movies.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}