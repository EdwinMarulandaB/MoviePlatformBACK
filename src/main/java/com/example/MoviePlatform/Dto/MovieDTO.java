package com.example.MoviePlatform.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private int movieid;
    private String name;
    private String image;
    private String description;
    private int score;
    private int status;
}