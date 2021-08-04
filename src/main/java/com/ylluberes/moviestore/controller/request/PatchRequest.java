/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@AllArgsConstructor
@Getter
@Setter
public class PatchRequest {

    private final String title;
    private final String description;
    @Min(value = 0)
    private final Integer stock;
    @Min(value = 0)
    private final Double rentalPrice;
    @Min(value = 0)
    private final Double salePrice;
    private Boolean available;
}
