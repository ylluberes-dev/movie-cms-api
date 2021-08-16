/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.service;

import com.ylluberes.moviestore.controller.request.AddOrUpdateMovieRequest;
import com.ylluberes.moviestore.controller.request.FindMovieRequest;
import com.ylluberes.moviestore.controller.request.PatchRequest;
import com.ylluberes.moviestore.controller.response.MovieResponse;
import com.ylluberes.moviestore.domain.Movie;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import org.springframework.data.domain.Page;

public interface MovieService {

    MovieResponse save (AddOrUpdateMovieRequest request);
    MovieResponse updateMovie (int movieId,
                  AddOrUpdateMovieRequest request) throws MovieNotFoundException;
    MovieResponse patch (int movieId, PatchRequest request);
    void delete (int movieId) throws MovieNotFoundException;
    Page<Movie> getMovies (FindMovieRequest request);

}
