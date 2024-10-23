package com.api.movie_flicks.service;

import com.api.movie_flicks.dto.MovieResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    private RestTemplate restTemplate;

    String restAPI = "http://www.omdbapi.com/?apikey=75fe1ae4";

    MovieService() {
        restTemplate = new RestTemplate();
    }

    public MovieResponseDTO getSearchedMovie(String keyword) {
        restAPI += "&s="+keyword;
        return restTemplate.getForEntity(restAPI, MovieResponseDTO.class).getBody();
    }
}
