package com.api.movie_flicks.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="BookDetails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;
    private String name;
    private String author;
}