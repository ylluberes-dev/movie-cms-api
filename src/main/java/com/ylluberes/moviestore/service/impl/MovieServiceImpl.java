/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.service.impl;

import com.ylluberes.moviestore.controller.request.AddOrUpdateMovieRequest;
import com.ylluberes.moviestore.controller.request.FindMovieRequest;
import com.ylluberes.moviestore.controller.request.PatchRequest;
import com.ylluberes.moviestore.dao.MovieRepository;
import com.ylluberes.moviestore.domain.Movie;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import com.ylluberes.moviestore.service.MovieService;
import com.ylluberes.moviestore.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * @param request containing the details of the new movie
     * @return saved Movie object
     */
    @Override
    public Movie save(final AddOrUpdateMovieRequest request) {
        final Movie movie = Mapper.fromDto(request);
        return movieRepository.save(movie);
    }

    /**
     * @param movieId id the of the movie to update.
     * @param request new movie details payload.
     * @return Updated Movie object
     * @throws MovieNotFoundException when movie not found.
     * This method is called when PUT request is executed.
     */
    @Override
    public Movie update(final int movieId,
                        final AddOrUpdateMovieRequest request) throws MovieNotFoundException {
        final Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isPresent()) {
            final Movie movie = Mapper.fromDto(request);
            movie.setMovieId(movieId);
            return movieRepository.save(movie);
        } else {
            throw new MovieNotFoundException("There is no movie with id " + movieId);
        }
    }

    /**
     *
     * @param movieId id the of the movie to update.
     * @param request new movie details payload.
     * @return Updated Movie object when movie not found.
     * @throws MovieNotFoundException
     * This method is called when PATCH request is executed.
     */
    @Override
    public Movie update(final int movieId,
                        final PatchRequest request) throws MovieNotFoundException {
        final Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isPresent()) {
            final Movie movie = Mapper.fromDto(request, optionalMovie.get());
            return movieRepository.save(movie);
        } else {
            throw new MovieNotFoundException("There is no movie with id " + movieId);
        }
    }

    /**
     * @param movieId of movie to delete.
     * @throws MovieNotFoundException when movie not found.
     */
    @Override
    public void delete(final int movieId) throws MovieNotFoundException {
        final Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isPresent()) {
            movieRepository.delete(optionalMovie.get());
        } else {
            throw new MovieNotFoundException("There is no movie with id " + movieId);
        }
    }

    /**
     * @param request object with the details restrictions to fetch movies data
     * @return Page - pagination object
     */
    @Override
    public Page<Movie> getMovies(final FindMovieRequest request) {
        final String[] sortDetails = request.getSort().split(",");
        final String field = sortDetails[0];
        final String orderDirection =
                sortDetails.length > 1 ?
                        sortDetails[1] : "asc";
        Sort sortItem =
                "asc".equals(orderDirection) ?
                        Sort.by(field).ascending() :
                        "desc".equals(orderDirection)
                                ? Sort.by(field).descending() : null;

        final Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sortItem);

        Page<Movie> moviePage;
        if (request.getTitle() != null) {
            moviePage =
                    request.isUnavailable() ?
                            movieRepository.findAllByTitle(request.getTitle(), pageable) :
                            movieRepository.findAllByTitleAndAvailable(request.getTitle(), pageable);
        } else {
            moviePage =
                    request.isUnavailable() ?
                            movieRepository.findAll(pageable) :
                            movieRepository.findAllByAvailable(true,pageable);
        }
        return moviePage;
    }
}
