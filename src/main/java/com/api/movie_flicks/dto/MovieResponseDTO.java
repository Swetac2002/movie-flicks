package com.api.movie_flicks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class MovieResponseDTO {
    @JsonProperty("Search")
    private List<MovieDTO> search;
    @JsonProperty("totalResults")
    private String totalResults;
    @JsonProperty("Response")
    private Boolean response;
}

