/**
 * @author Yasser Lluberes
 * @version 1.0
 */
package com.ylluberes.moviestore.service.impl;

import com.ylluberes.moviestore.controller.request.ActionableRequest;
import com.ylluberes.moviestore.controller.response.OnLikeResponse;
import com.ylluberes.moviestore.dao.LikeRepository;
import com.ylluberes.moviestore.dao.MovieRepository;
import com.ylluberes.moviestore.domain.Likes;
import com.ylluberes.moviestore.domain.Movie;
import com.ylluberes.moviestore.exceptions.MovieAlreadyLikedException;
import com.ylluberes.moviestore.exceptions.MovieNotAvailableException;
import com.ylluberes.moviestore.exceptions.MovieNotFoundException;
import com.ylluberes.moviestore.service.LikeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final MovieRepository movieRepository;

    private final LikeRepository likeRepository;

    private final ModelMapper modelMapper;

    /**
     * @param request Incoming like request
     * @return OnLikeResponse
     * @throws MovieNotFoundException     when movie not found
     * @throws MovieNotAvailableException when the movie is not available
     * @throws MovieAlreadyLikedException when the movie is already liked by the same customer
     */
    @Override
    public OnLikeResponse like(final ActionableRequest request)
            throws MovieNotFoundException, MovieNotAvailableException, MovieAlreadyLikedException {
        final Movie movie = movieRepository
                .findById(request.getMovieId())
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        if (movie.getAvailable()) {
            if (likeRepository.existsLikesByEmailAndMovie(request.getCustomerEmail(), movie))
                throw new MovieAlreadyLikedException("Movie is already liked by this customer");

            likeRepository.save(modelMapper.map(request, Likes.class));
            movie.updateLikes();
            final OnLikeResponse response = modelMapper.map(movie, OnLikeResponse.class);
            return response;
        } else {
            throw new MovieNotAvailableException("The movie with id " + request.getMovieId() + " is not available");
        }
    }

}
