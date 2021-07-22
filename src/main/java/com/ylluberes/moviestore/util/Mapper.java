/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.util;

import com.ylluberes.moviestore.controller.request.AddOrUpdateMovieRequest;
import com.ylluberes.moviestore.controller.request.PatchRequest;
import com.ylluberes.moviestore.domain.Movie;

/**
 * Util dto-domain-definition mapper.
 * TODO: Make a generic fromDto or inherit from a baseRequest class to avoid code duplication
 */

public final class Mapper {

    /**
     *
     * @param request add or update incoming request.
     * @return Movie domain-definition object
     */
    public static Movie fromDto (final AddOrUpdateMovieRequest request) {
        final Movie movie = new Movie();
        movie.setAvailable(request.getAvailable() == null ? true : request.getAvailable());
        movie.setRentalPrice(request.getRentalPrice());
        movie.setStock(request.getStock());
        movie.setSalePrice(request.getSalePrice());
        movie.setDescription(request.getDescription());
        movie.setTitle(request.getTitle());
        return movie;
    }

    /**
     *
     * @param request patch incoming request
     * @return Movie domain-definition object after patch
     */
    public static Movie fromDto (final PatchRequest request, final Movie movie) {
        movie.setAvailable(request.getAvailable() != null ?  request.getAvailable() : movie.getAvailable());
        movie.setRentalPrice(request.getRentalPrice() != null ? request.getRentalPrice() : movie.getRentalPrice());
        movie.setStock(request.getStock() != null ? request.getStock(): movie.getStock());
        movie.setSalePrice(request.getSalePrice() != null ? request.getSalePrice() : movie.getSalePrice());
        movie.setDescription(request.getDescription() != null ? request.getDescription() : movie.getDescription());
        movie.setTitle(request.getTitle() != null ? request.getTitle() : movie.getTitle());
        return movie;
    }

    /**
     * Hidden constructor.
     */
    private Mapper() { }
}
