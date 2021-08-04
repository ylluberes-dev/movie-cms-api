package com.ylluberes.moviestore.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MovieResponse {
    private int movieId;
    private String title;
    private String description;
    private Integer stock;
    private Double rentalPrice;
    private Double salePrice;
    private Boolean available;

}
