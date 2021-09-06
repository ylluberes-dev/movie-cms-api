/**
 *
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.controller.response;

import com.ylluberes.moviestore.domain.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class MovieSearchResponse {

    private List<Movie> content;
    private int size;
    private int numberOfElements;
    private int totalElements;
    private int totalPages;
    private int number;
}
