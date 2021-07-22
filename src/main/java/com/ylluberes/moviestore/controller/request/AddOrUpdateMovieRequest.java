/**
 *
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class AddOrUpdateMovieRequest {

    @NotNull
    @NotBlank
    private final String title;
    private final String description;
    @NotNull
    @Min(value = 0)
    private final Integer stock;
    @NotNull
    @Min(value = 0)
    private final Double rentalPrice;
    @NotNull
    @Min(value = 0)
    private final Double salePrice;
    private final Boolean available;
}
